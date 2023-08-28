package com.libraryapp.library.domain.dto;

public class ReaderDto {
    private String firstName;
    private String lastName;

    public ReaderDto() {
    }

    public ReaderDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
