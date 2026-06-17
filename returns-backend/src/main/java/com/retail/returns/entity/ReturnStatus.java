package com.retail.returns.entity;

/**
 * Lifecycle status of a return request.
 * SUBMITTED is a transient state - the system resolves it to one of the
 * other three statuses synchronously as soon as the request is evaluated.
 */
public enum ReturnStatus {
    SUBMITTED,
    APPROVED,
    PENDING_REVIEW,
    REJECTED
}
