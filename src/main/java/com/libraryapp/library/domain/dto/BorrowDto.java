package com.libraryapp.library.domain.dto;

import java.time.LocalDate;

public record BorrowDto
        (Long readerId,
         Long bookId,
         LocalDate borrowStart,
         LocalDate borrowEnd
        ) {
}
