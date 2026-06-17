package com.retail.returns.exception;

/**
 * Thrown when a requested ReturnRequest does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
