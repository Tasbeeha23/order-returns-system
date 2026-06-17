package com.retail.returns.exception;

/**
 * Thrown when a path variable status value doesn't map to a valid ReturnStatus.
 */
public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
