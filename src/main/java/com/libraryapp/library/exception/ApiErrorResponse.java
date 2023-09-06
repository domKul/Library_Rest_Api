package com.libraryapp.library.exception;

import java.time.LocalDate;

class ApiErrorResponse {
    private String errorMessage;
    private String errorCode;
    private LocalDate localDate;

    public ApiErrorResponse(String errorMessage, String errorCode, LocalDate localDate) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.localDate = localDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
