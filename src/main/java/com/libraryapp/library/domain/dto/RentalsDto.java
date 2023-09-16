package com.libraryapp.library.domain.dto;

import java.time.LocalDate;

public record RentalsDto
        (Long readerId,
         Long bookId,
         LocalDate rentalStart,
         LocalDate rentalEnd
        ) {
}
