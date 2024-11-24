package com.banking_api.Banking_Api.Exceptions_For_Customer;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}