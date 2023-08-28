package com.libraryapp.library.domain.dto;

public class PublicationsDto {
    private String title;
    private String author;
    private int publicationYear;

    public PublicationsDto() {
    }

    public PublicationsDto(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
