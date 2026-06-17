package com.retail.returns.controller;

import com.retail.returns.dto.ReturnRequestDTO;
import com.retail.returns.dto.ReturnResponseDTO;
import com.retail.returns.service.ReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for the Order Returns System.
 * Thin layer: validates input shape (via @Valid) and delegates all logic to the service.
 */
@RestController
@RequestMapping("/returns")
@CrossOrigin(origins = "*") // open for local frontend dev; restrict in a real deployment
public class ReturnController {

    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    /** Create a new return request. The system evaluates eligibility synchronously. */
    @PostMapping
    public ResponseEntity<ReturnResponseDTO> createReturn(@Valid @RequestBody ReturnRequestDTO requestDTO) {
        ReturnResponseDTO response = returnService.createReturn(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /** List all return requests. */
    @GetMapping
    public ResponseEntity<List<ReturnResponseDTO>> getAllReturns() {
        return ResponseEntity.ok(returnService.getAllReturns());
    }

    /** Fetch a single return request by id. */
    @GetMapping("/{id}")
    public ResponseEntity<ReturnResponseDTO> getReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnService.getReturnById(id));
    }

    /** Filter return requests by status (SUBMITTED, APPROVED, PENDING_REVIEW, REJECTED). */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReturnResponseDTO>> getReturnsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(returnService.getReturnsByStatus(status));
    }
}
