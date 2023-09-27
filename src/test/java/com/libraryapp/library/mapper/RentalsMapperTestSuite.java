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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        bookCopies = new BookCopies(0L, publications.getPublicationId(), "testbook1");
        rentals = new Rentals(0L, reader, bookCopies, LocalDate.now(), LocalDate.now().plusDays(1));
        rentalsDto = new RentalsDto(reader.getReaderId(), bookCopies.getBookId(), LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    void shouldMapToRentalsDto() {
        //Given


        //When
        Optional<RentalsDto> rentalsDto = rentalsMapper.mapToRentalsDto(rentals);

        //Then
        assertTrue(rentalsDto.isPresent());
        assertEquals(RentalsDto.class, rentalsDto.get().getClass());
    }

    @Test
    void shouldHandleNullRental() {
        // Given

        // When
        Optional<RentalsDto> rentalsDto = rentalsMapper.mapToRentalsDto(null);

        // Then
        assertEquals(Optional.empty(),rentalsDto);
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


        List<Rentals> readersList = List.of(new Rentals(reader,bookCopies,LocalDate.now(),LocalDate.now().plusDays(1)),
                new Rentals(reader,bookCopies,LocalDate.now(),LocalDate.now().plusDays(1)),
                new Rentals(reader,bookCopies,LocalDate.now(),LocalDate.now().plusDays(1)));

        //When
        List<Optional<RentalsDto>> rentalsDtos = rentalsMapper.mapToRentalsDtoList(readersList);


        //Then
        assertEquals(3, rentalsDtos.size());

        for (Optional<RentalsDto> optionalDto : rentalsDtos){
            assertTrue(optionalDto.isPresent());

            RentalsDto dto = optionalDto.get();
            assertNotNull(dto);
        }


    }

}
