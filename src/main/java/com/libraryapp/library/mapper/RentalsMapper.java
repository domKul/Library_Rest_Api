package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.Rentals;
import com.libraryapp.library.domain.dto.RentalsDto;
import com.libraryapp.library.exception.BookCopiesNotFound;
import com.libraryapp.library.exception.ReaderNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RentalsMapper {


    public Rentals mapToRentals(RentalsDto rentalsDto, Reader reader, BookCopies bookCopies) {

        return new Rentals(
                reader,
                bookCopies,
                rentalsDto.rentalStart(),
                rentalsDto.rentalEnd());
    }

    public Optional<RentalsDto> mapToRentalsDto(Rentals rentals) {
        return Optional.ofNullable(rentals)
                .map(rental -> new RentalsDto(
                        Optional.ofNullable(rental.getBookCopies()).map(BookCopies::getBookId)
                                .orElseThrow(() ->new BookCopiesNotFound("Book are null")),
                        Optional.ofNullable(rental.getReader()).map(Reader::getReaderId)
                                .orElseThrow(()->new ReaderNotFoundException("Reader are null")),
                        rental.getRentalStart(),
                        rental.getRentalEnd()

                ));
    }


    public List<Optional<RentalsDto>> mapToRentalsDtoList(final List<Rentals> rentals) {
        return rentals.stream().map(this::mapToRentalsDto).toList();
    }
}
