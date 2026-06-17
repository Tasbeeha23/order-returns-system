package com.retail.returns.service;

import com.retail.returns.dto.ReturnRequestDTO;
import com.retail.returns.dto.ReturnResponseDTO;
import com.retail.returns.entity.ReturnRequest;
import com.retail.returns.entity.ReturnStatus;
import com.retail.returns.exception.InvalidStatusException;
import com.retail.returns.exception.ResourceNotFoundException;
import com.retail.returns.repository.ReturnRequestRepository;
import com.retail.returns.util.ReturnEligibilityEvaluator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Core business logic for return requests.
 * Transactional boundaries live here, not in the controller, so the
 * "evaluate then persist" sequence is atomic.
 */
@Service
public class ReturnServiceImpl implements ReturnService {

    private final ReturnRequestRepository repository;
    private final ReturnEligibilityEvaluator eligibilityEvaluator;

    public ReturnServiceImpl(ReturnRequestRepository repository,
                              ReturnEligibilityEvaluator eligibilityEvaluator) {
        this.repository = repository;
        this.eligibilityEvaluator = eligibilityEvaluator;
    }

    @Override
    @Transactional
    public ReturnResponseDTO createReturn(ReturnRequestDTO requestDTO) {
        ReturnRequest entity = new ReturnRequest(
                requestDTO.getOrderId(),
                requestDTO.getProductName(),
                requestDTO.getPurchaseDate(),
                requestDTO.getReturnReason(),
                requestDTO.getItemCondition()
        );

        // Apply business rule immediately - no async workflow needed for this scope.
        ReturnEligibilityEvaluator.Decision decision = eligibilityEvaluator.evaluate(
                requestDTO.getPurchaseDate(),
                requestDTO.getItemCondition()
        );
        entity.setStatus(decision.getStatus());
        entity.setDecisionReason(decision.getReason());

        ReturnRequest saved = repository.save(entity);
        return ReturnResponseDTO.fromEntity(saved);
    }

    @Override
    public List<ReturnResponseDTO> getAllReturns() {
        return repository.findAll().stream()
                .map(ReturnResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReturnResponseDTO getReturnById(Long id) {
        ReturnRequest entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Return request not found with id: " + id));
        return ReturnResponseDTO.fromEntity(entity);
    }

    @Override
    public List<ReturnResponseDTO> getReturnsByStatus(String status) {
        ReturnStatus statusEnum;
        try {
            statusEnum = ReturnStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException(
                    "Invalid status: '" + status + "'. Valid values are: SUBMITTED, APPROVED, PENDING_REVIEW, REJECTED"
            );
        }

        return repository.findByStatus(statusEnum).stream()
                .map(ReturnResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
