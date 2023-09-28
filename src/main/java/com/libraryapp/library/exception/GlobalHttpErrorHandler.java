package com.libraryapp.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ReaderNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleReaderNotFoundException(ReaderNotFoundException readerNotFoundException) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(readerNotFoundException.getMessage(),
                "READER_NOT_FOUND", LocalDate.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicatedPublicationException.class})
    public ResponseEntity<ApiErrorResponse> handleDuplicatedPublicationException(DuplicatedPublicationException duplicatedPublicationException) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(duplicatedPublicationException.getMessage(),
                "DUPLICATED_PUBLICATION", LocalDate.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PublicationNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>handlePublicationTitleNotFound(PublicationNotFoundException publicationNotFoundException){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(publicationNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND.toString(),LocalDate.now());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookCopyNotFound.class)
    public ResponseEntity<ApiErrorResponse>handleBookCopyNotFound(BookCopyNotFound bookCopyNotFound){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(bookCopyNotFound.getMessage(),
                HttpStatus.NOT_FOUND.toString(),LocalDate.now());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookCopyAlreadyTakenException.class)
    public ResponseEntity<ApiErrorResponse>handleProblemWithBorrowCreation(BookCopyAlreadyTakenException bookCopyAlreadyTakenException){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(bookCopyAlreadyTakenException.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),LocalDate.now());
        return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }


}
