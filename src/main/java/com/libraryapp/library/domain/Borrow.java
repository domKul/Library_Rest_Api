package com.libraryapp.library.domain;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;
    @JoinColumn(name = "reader_Id")
    @NonNull
    private Long reader;

    @JoinColumn(name = "book_Id")
    @NonNull
    private Long bookCopiesId;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;

    public Borrow() {
    }

    public Borrow(Long borrowId, @NonNull Long reader, @NonNull Long bookCopiesId, LocalDate rentalStart, LocalDate rentalEnd) {
        this.borrowId = borrowId;
        this.reader = reader;
        this.bookCopiesId = bookCopiesId;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Borrow(@NonNull Long reader, @NonNull Long bookCopiesId, LocalDate rentalStart, LocalDate rentalEnd) {
        this.reader = reader;
        this.bookCopiesId = bookCopiesId;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    @NonNull
    public Long getReader() {
        return reader;
    }

    public void setReader(@NonNull Long reader) {
        this.reader = reader;
    }

    @NonNull
    public Long getBookCopiesId() {
        return bookCopiesId;
    }

    public void setBookCopiesId(@NonNull Long bookCopiesId) {
        this.bookCopiesId = bookCopiesId;
    }

    public LocalDate getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(LocalDate rentalStart) {
        this.rentalStart = rentalStart;
    }

    public LocalDate getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(LocalDate rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(borrowId, borrow.borrowId) && Objects.equals(rentalStart, borrow.rentalStart) && Objects.equals(rentalEnd, borrow.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowId, rentalStart, rentalEnd);
    }
}
