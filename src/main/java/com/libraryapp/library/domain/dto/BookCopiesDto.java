package com.libraryapp.library.domain.dto;

public record BookCopiesDto
        (Long bookId,
         Long publicationId,
         String status) {
}
