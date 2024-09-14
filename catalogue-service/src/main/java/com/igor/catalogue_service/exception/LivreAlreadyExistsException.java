package com.igor.catalogue_service.exception;

public class LivreAlreadyExistsException extends RuntimeException {

    public LivreAlreadyExistsException(String titre) {
        super("Un livre avec le titre '" + titre + "' existe déjà.");
    }
}