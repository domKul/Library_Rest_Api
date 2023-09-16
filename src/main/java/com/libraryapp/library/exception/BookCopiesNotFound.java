package com.libraryapp.library.exception;

public class BookCopiesNotFound extends RuntimeException {
    public BookCopiesNotFound(String message) {
        super(message);
    }
}
