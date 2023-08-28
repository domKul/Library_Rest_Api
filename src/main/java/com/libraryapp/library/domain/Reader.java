package com.libraryapp.library.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readerId;
    private String firstName;
    private String lastName;
    private LocalDate creationDate;


    public Reader() {
    }

    public Reader(Long readerId, String firstName, String lastName) {
        this.readerId = readerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(readerId, reader.readerId) && Objects.equals(firstName, reader.firstName) && Objects.equals(lastName, reader.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
