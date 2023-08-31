package com.libraryapp.library.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publications")
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationId;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private int publicationYear;
    @OneToMany(mappedBy = "publications",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookCopies> bookCopies;




    public Publications() {
    }

    public Publications(Long publicationId, String title, String author, int publicationYear) {
        this.publicationId = publicationId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<BookCopies> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopies> bookCopies) {
        this.bookCopies = bookCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publications that = (Publications) o;
        return Objects.equals(publicationId, that.publicationId) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(publicationYear, that.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, title, author, publicationYear);
    }

    @Override
    public String toString() {
        return "Publications{" +
                "publicationId=" + publicationId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                '}';
    }
}
