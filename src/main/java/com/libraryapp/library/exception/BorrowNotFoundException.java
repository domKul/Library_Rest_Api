package com.libraryapp.library.exception;

public class BorrowNotFoundException extends RuntimeException{
    public BorrowNotFoundException(String message) {
        super(message);
    }
}
