package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.Rentals;
import com.libraryapp.library.domain.dto.RentalsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RentalsMapperTestSuite {
    
    @Autowired
    private RentalsMapper rentalsMapper;
    
    Reader reader;
    BookCopies bookCopies;
    Publications publications;
    Rentals rentals;
    
    @BeforeEach
    void testData(){
        reader = new Reader(0L,"testreader1","testreader1");
        publications = new Publications(0L,"testpublication1", "testpublication1",2000);
        bookCopies = new BookCopies(0L,publications,"testbook1");
        rentals = new Rentals(0L,reader,bookCopies, LocalDate.now(),LocalDate.now().plusDays(1));
    }
    
    @Test
    void  shouldMapToRentalsDto(){
        //Given
        
        
        //When
        RentalsDto rentalsDto = rentalsMapper.mapToRentalsDto(rentals);

        //Then
        assertEquals(RentalsDto.class,rentalsDto.getClass());
    }
    @Test
    void shouldHandleNullRental() {
        // Given

        // When
        RentalsDto rentalsDto = rentalsMapper.mapToRentalsDto(null);

        // Then
        assertNull(rentalsDto);
    }

}
