package com.libraryapp.library.exception;

public class DuplicatedPublicationException extends RuntimeException {
    public DuplicatedPublicationException(String message) {
        super(message);
    }
}
