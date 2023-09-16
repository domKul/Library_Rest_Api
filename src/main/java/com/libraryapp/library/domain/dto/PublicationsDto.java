package com.libraryapp.library.domain.dto;

import com.libraryapp.library.domain.Publications;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PublicationsDto

        (@NotBlank(message = "Title cannot be empty")
         String title,
         @NotBlank(message = "Author cannot be empty")
         String author,
         @Min(value = 0, message = "Year of publication cannot by lower then 0")
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
