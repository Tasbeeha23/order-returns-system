package com.retail.returns.dto;

import com.retail.returns.entity.ItemCondition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Inbound payload for creating a return request.
 * Kept separate from the entity so the API contract doesn't leak persistence details
 * (id, status, decisionReason are server-assigned, not client-supplied).
 */
public class ReturnRequestDTO {

    @NotBlank(message = "orderId is required")
    private String orderId;

    @NotBlank(message = "productName is required")
    private String productName;

    @NotNull(message = "purchaseDate is required")
    @PastOrPresent(message = "purchaseDate cannot be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotBlank(message = "returnReason is required")
    private String returnReason;

    @NotNull(message = "itemCondition is required")
    private ItemCondition itemCondition;

    public ReturnRequestDTO() {
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
}
