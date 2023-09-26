package com.libraryapp.library.exception;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(String message) {
        super(message);
    }

}
