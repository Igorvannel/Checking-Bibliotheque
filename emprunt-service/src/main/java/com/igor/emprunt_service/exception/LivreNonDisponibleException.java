package com.igor.emprunt_service.exception;

public class LivreNonDisponibleException extends RuntimeException {
    public LivreNonDisponibleException(String message) {
        super(message);
    }
}