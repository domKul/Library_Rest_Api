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

    @JoinColumn(name = "reader")
    @NonNull
    private Long reader;

    @JoinColumn(name = "book_Copies_Id")
    @NonNull
    private Long bookCopiesId;
    private LocalDate borrowStart;
    private LocalDate borrowEnd;

    public Borrow() {
    }

    public Borrow(Long borrowId, @NonNull Long readerId, @NonNull Long bookCopiesId, LocalDate borrowStart, LocalDate borrowEnd) {
        this.borrowId = borrowId;
        this.reader = readerId;
        this.bookCopiesId = bookCopiesId;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
    }

    public Borrow(@NonNull Long readerId, @NonNull Long bookCopiesId, LocalDate borrowStart, LocalDate borrowEnd) {
        this.reader = readerId;
        this.bookCopiesId = bookCopiesId;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
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

    public LocalDate getBorrowStart() {
        return borrowStart;
    }

    public void setBorrowStart(LocalDate borrowStart) {
        this.borrowStart = borrowStart;
    }

    public LocalDate getBorrowEnd() {
        return borrowEnd;
    }

    public void setBorrowEnd(LocalDate borrowEnd) {
        this.borrowEnd = borrowEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(borrowId, borrow.borrowId) && Objects.equals(borrowStart, borrow.borrowStart) && Objects.equals(borrowEnd, borrow.borrowEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowId, borrowStart, borrowEnd);
    }
}
