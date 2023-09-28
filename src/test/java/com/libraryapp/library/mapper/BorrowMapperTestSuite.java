package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.Borrow;
import com.libraryapp.library.domain.dto.BorrowDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BorrowMapperTestSuite {

    @Autowired
    private BorrowMapper borrowMapper;

    Reader reader;
    BookCopies bookCopies;
    Publications publications;
    Borrow borrow;
    BorrowDto borrowDto;

    @BeforeEach
    void testData() {
        reader = new Reader(0L, "testreader1", "testreader1");
        publications = new Publications(0L, "testpublication1", "testpublication1", 2000);
        bookCopies = new BookCopies(0L, publications.getPublicationId(), "testbook1");
        borrow = new Borrow(0L, 0L, 0L, LocalDate.now(), LocalDate.now().plusDays(1));
        borrowDto = new BorrowDto(reader.getReaderId(), bookCopies.getBookId(), LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    void shouldMapToRentalsDto() {
        //Given


        //When
        Optional<BorrowDto> rentalsDto = borrowMapper.mapToBorrowDto(borrow);

        //Then
        assertTrue(rentalsDto.isPresent());
        assertEquals(BorrowDto.class, rentalsDto.get().getClass());
    }

    @Test
    void shouldHandleNullRental() {
        // Given

        // When
        Optional<BorrowDto> rentalsDto = borrowMapper.mapToBorrowDto(null);

        // Then
        assertEquals(Optional.empty(),rentalsDto);
    }

    @Test
    void shouldMapToRentals() {
        //Given

        //When
        Borrow borrow1 = borrowMapper.mapToBorrow(borrowDto);

        //Then
        assertEquals(Borrow.class, borrow1.getClass());
    }

    @Test
    void shouldMapToList() {
        //Given


        List<Borrow> readersList = List.of(new Borrow(1L,1L,LocalDate.now(),LocalDate.now().plusDays(1)),
                new Borrow(1L,1L,LocalDate.now(),LocalDate.now().plusDays(1)),
                new Borrow(1L,1L,LocalDate.now(),LocalDate.now().plusDays(1)));

        //When
        List<Optional<BorrowDto>> rentalsDtos = borrowMapper.mapToBorrowDtoList(readersList);


        //Then
        assertEquals(3, rentalsDtos.size());

        for (Optional<BorrowDto> optionalDto : rentalsDtos){
            assertTrue(optionalDto.isPresent());

            BorrowDto dto = optionalDto.get();
            assertNotNull(dto);
        }


    }

}
