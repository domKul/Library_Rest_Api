package com.libraryapp.library.exception;

import java.time.LocalDate;

record ApiErrorResponse(String errorMessage, String errorCode, LocalDate localDate) {
}
