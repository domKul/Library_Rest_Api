package com.libraryapp.library.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record PublicationsDto

        (@NotBlank(message = "Title cannot by empty")
         String title,
         @NotBlank(message = "Author cannot by empty")
         String author,
         @NotBlank(message = "Year of publication cannot by empty")
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
