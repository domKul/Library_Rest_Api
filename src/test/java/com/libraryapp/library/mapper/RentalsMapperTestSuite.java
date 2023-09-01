package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.Rentals;
import com.libraryapp.library.domain.dto.RentalsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

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
    RentalsDto rentalsDto;

    @BeforeEach
    void testData() {
        reader = new Reader(0L, "testreader1", "testreader1");
        publications = new Publications(0L, "testpublication1", "testpublication1", 2000);
        bookCopies = new BookCopies(0L, publications, "testbook1");
        rentals = new Rentals(0L, reader, bookCopies, LocalDate.now(), LocalDate.now().plusDays(1));
        rentalsDto = new RentalsDto(reader.getReaderId(), bookCopies.getBookId(), LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    void shouldMapToRentalsDto() {
        //Given


        //When
        RentalsDto rentalsDto = rentalsMapper.mapToRentalsDto(rentals);

        //Then
        assertEquals(RentalsDto.class, rentalsDto.getClass());
    }

    @Test
    void shouldHandleNullRental() {
        // Given

        // When
        RentalsDto rentalsDto = rentalsMapper.mapToRentalsDto(null);

        // Then
        assertNull(rentalsDto);
    }

    @Test
    void shouldMapToRentals() {
        //Given

        //When
        Rentals rentals1 = rentalsMapper.mapToRentals(rentalsDto, reader, bookCopies);

        //Then
        assertEquals(Rentals.class, rentals1.getClass());
    }

    @Test
    void shouldMapToList() {
        //Given
        List<Rentals> readersList = List.of(new Rentals(), new Rentals(), new Rentals());

        //When
        List<RentalsDto> rentalsDtos = rentalsMapper.mapToRentalsDtoList(readersList);


        //Then
        assertEquals(RentalsDto.class, rentalsDtos.get(0).getClass());
        assertEquals(3, rentalsDtos.size());
    }

}
