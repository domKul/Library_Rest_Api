package com.libraryapp.library.exception;

public class BookCopyNotFound extends RuntimeException {
    public BookCopyNotFound(String message) {
        super(message);
    }
}
