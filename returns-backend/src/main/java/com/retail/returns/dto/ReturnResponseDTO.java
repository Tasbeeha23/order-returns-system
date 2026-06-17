package com.retail.returns.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.retail.returns.entity.ItemCondition;
import com.retail.returns.entity.ReturnRequest;
import com.retail.returns.entity.ReturnStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Outbound representation of a return request, including the system's decision and reason.
 */
public class ReturnResponseDTO {

    private Long id;
    private String orderId;
    private String productName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    private String returnReason;
    private ItemCondition itemCondition;
    private ReturnStatus status;
    private String decisionReason;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public ReturnResponseDTO() {
    }

    public static ReturnResponseDTO fromEntity(ReturnRequest entity) {
        ReturnResponseDTO dto = new ReturnResponseDTO();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setProductName(entity.getProductName());
        dto.setPurchaseDate(entity.getPurchaseDate());
        dto.setReturnReason(entity.getReturnReason());
        dto.setItemCondition(entity.getItemCondition());
        dto.setStatus(entity.getStatus());
        dto.setDecisionReason(entity.getDecisionReason());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    // --- Getters and setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public ItemCondition getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
