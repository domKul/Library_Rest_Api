package com.libraryapp.library.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book_copies")
public class BookCopies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicationId")
    private Publications publications;
    private String status;

    public BookCopies() {
    }

    public BookCopies(Long bookId, Publications publications, String status) {
        this.bookId = bookId;
        this.publications = publications;
        this.status = status;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Publications getPublications() {
        return publications;
    }

    public void setPublications(Publications publications) {
        this.publications = publications;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopies that = (BookCopies) o;
        return Objects.equals(bookId, that.bookId) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, status);
    }

    @Override
    public String toString() {
        return "BookCopies{" +
                "bookId=" + bookId +
                ", publications=" + publications +
                ", status='" + status + '\'' +
                '}';
    }
}
