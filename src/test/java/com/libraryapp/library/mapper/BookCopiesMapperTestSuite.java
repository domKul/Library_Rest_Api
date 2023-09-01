package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.BookCopiesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookCopiesMapperTestSuite {

    @Autowired
    private BookCopiesMapper bookCopiesMapper;

    BookCopies bookCopies;
    BookCopiesDto bookCopiesDto;
    Publications publications;

    @BeforeEach
    void testData(){
        publications = new Publications(0L,"pub1","author1",2012);
        bookCopies = new BookCopies(0L,publications,"status1");
        bookCopiesDto = new BookCopiesDto(0L,publications.getPublicationId(),"dto1");

    }

    @Test
    void shouldMapToBookCopiesDto(){
        //Given

        //When
        BookCopiesDto Dto = bookCopiesMapper.mapToBookCopiesDto(bookCopies);

        //Then
        assertEquals(BookCopiesDto.class, Dto.getClass());
        assertEquals("status1",Dto.status());
        assertEquals(publications.getPublicationId(),Dto.publicationId());
    }
    @Test
    void shouldMapToBookCopies(){
        //Given

        //Whne
        BookCopies bookCopies1 = bookCopiesMapper.mapToBookCopies(bookCopiesDto, publications);

        //Then
        assertEquals(BookCopies.class,bookCopies1.getClass());
        assertEquals(publications.getPublicationId(),bookCopies1.getPublications().getPublicationId());
        assertEquals("pub1",bookCopies1.getPublications().getTitle());
        assertEquals(bookCopiesDto.bookId(),bookCopies1.getBookId());
    }
}
