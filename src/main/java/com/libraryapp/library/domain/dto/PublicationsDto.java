package com.libraryapp.library.domain.dto;

public record PublicationsDto

        (
                String title,

                String author,

                int publicationYear) {
    @Override
    public String title() {
        return title;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public int publicationYear() {
        return publicationYear;
    }
}
