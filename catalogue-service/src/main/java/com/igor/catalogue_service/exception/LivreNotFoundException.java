package com.igor.catalogue_service.exception;

public class LivreNotFoundException extends RuntimeException {

    public LivreNotFoundException(Long id) {
        super("Livre non trouvé avec l'ID : " + id);
    }
}