package com.libraryapp.library.exception;

public enum ExceptionMessage {

    WRONG_READER_ID("Reader with given ID doesn't exist"),
    WRONG_PUBLICATION_ID("Book with given ID doesn't exist");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
