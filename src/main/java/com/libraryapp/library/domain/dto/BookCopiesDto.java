package com.libraryapp.library.domain.dto;

public class BookCopiesDto {
    private Long bookId;
    private Long publicationId;
    private String status;

    public BookCopiesDto() {
    }

    public BookCopiesDto(Long bookId, Long publicationId, String status) {
        this.bookId = bookId;
        this.publicationId = publicationId;
        this.status = status;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public String getStatus() {
        return status;
    }
}
