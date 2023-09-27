package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.dto.BookCopiesDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCopiesMapper {

    public BookCopies mapToBookCopies(BookCopiesDto bookCopiesDto) {
        return new BookCopies(
                bookCopiesDto.publicationId(),
                bookCopiesDto.status()
        );
    }

    public BookCopiesDto mapToBookCopiesDto(BookCopies bookCopies) {
        Long publicationId = (bookCopies.getPublications() != null) ? bookCopies.getPublications() : null;
        return new BookCopiesDto(
                publicationId,
                bookCopies.getStatus());
    }

    public List<BookCopiesDto> mapToListBookCopiesDto(final List<BookCopies> bookCopies) {
        return bookCopies.stream().map(this::mapToBookCopiesDto).toList();
    }
}
