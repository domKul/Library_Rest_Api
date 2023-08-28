package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.BookCopiesDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCopiesMapper {

    public BookCopies mapToBookCopies(BookCopiesDto bookCopiesDto, Publications publications){
        BookCopies bookCopies = new BookCopies();
        bookCopies.setBookId(bookCopiesDto.getBookId());
        bookCopies.setPublications(publications);
        bookCopies.setStatus(bookCopiesDto.getStatus());
        return bookCopies;
    }
    public BookCopiesDto mapToBookCopiesDto(BookCopies bookCopies){
        Long publicationId = (bookCopies.getPublications() != null) ? bookCopies.getPublications().getPublicationId() : null;
        return new BookCopiesDto(bookCopies.getBookId(),
                publicationId,
                bookCopies.getStatus());
    }

    public List<BookCopiesDto>mapToListBookCopiesDto(final List<BookCopies> bookCopies){
        return bookCopies.stream().map(this::mapToBookCopiesDto).toList();
    }
}
