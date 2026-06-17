package com.retail.returns.repository;

import com.retail.returns.entity.ReturnRequest;
import com.retail.returns.entity.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for ReturnRequest.
 * Method-name query derivation covers our needs - no custom JPQL required.
 */
public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {
    List<ReturnRequest> findByStatus(ReturnStatus status);
}
