package com.libraryapp.library.exception;


public class BookCopyAlreadyTakenException extends RuntimeException{
    public BookCopyAlreadyTakenException(String message) {
        super(message);
    }
}
