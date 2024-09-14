package com.igor.emprunt_service.exception;


public class EtudiantNonEligibleException extends RuntimeException {
    public EtudiantNonEligibleException(String message) {
        super(message);
    }
}