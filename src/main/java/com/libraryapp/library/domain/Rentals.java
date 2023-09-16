package com.libraryapp.library.domain;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rentals")
public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_Id")
    @NonNull
    private Reader reader;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_Id")
    @NonNull
    private BookCopies bookCopies;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;

    public Rentals() {
    }

    public Rentals(Long rentalId, Reader reader, BookCopies bookCopies, LocalDate rentalStart, LocalDate rentalEnd) {
        this.rentalId = rentalId;
        this.reader = reader;
        this.bookCopies = bookCopies;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Rentals(Reader reader, BookCopies bookCopies, LocalDate rentalStart, LocalDate rentalEnd) {
        this.reader = reader;
        this.bookCopies = bookCopies;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public BookCopies getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(BookCopies bookCopies) {
        this.bookCopies = bookCopies;
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
        Rentals rentals = (Rentals) o;
        return Objects.equals(rentalId, rentals.rentalId) && Objects.equals(rentalStart, rentals.rentalStart) && Objects.equals(rentalEnd, rentals.rentalEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, rentalStart, rentalEnd);
    }
}
