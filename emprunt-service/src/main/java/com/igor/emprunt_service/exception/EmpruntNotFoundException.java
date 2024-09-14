package com.igor.emprunt_service.exception;

public class EmpruntNotFoundException extends RuntimeException {
    public EmpruntNotFoundException(String message) {
        super(message);
    }
}