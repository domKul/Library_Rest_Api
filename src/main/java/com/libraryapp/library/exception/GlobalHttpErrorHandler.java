package com.libraryapp.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ReaderNotFoundException.class})
    public ResponseEntity<Object>handleReaderNotFoundException(ReaderNotFoundException readerNotFoundException){
        return new ResponseEntity<>(readerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
