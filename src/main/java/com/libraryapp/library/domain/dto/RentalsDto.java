package com.libraryapp.library.domain.dto;

import java.time.LocalDate;

public class RentalsDto {
    private Long readerId;
    private Long bookId;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;

    public RentalsDto() {
    }

    public RentalsDto(Long readerId, Long bookId, LocalDate rentalStart, LocalDate rentalEnd) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public LocalDate getRentalStart() {
        return rentalStart;
    }

    public LocalDate getRentalEnd() {
        return rentalEnd;
    }
}
