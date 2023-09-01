package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.Rentals;
import com.libraryapp.library.domain.dto.RentalsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalsMapper {


    public Rentals mapToRentals(RentalsDto rentalsDto, Reader reader, BookCopies bookCopies) {
        return new Rentals(null,
                reader,
                bookCopies,
                rentalsDto.rentalStart(),
                rentalsDto.rentalEnd());
    }

    public RentalsDto mapToRentalsDto(Rentals rentals) {
        if (rentals == null) {
            return null;
        }
        return new RentalsDto(
                rentals.getBookCopies() != null ? rentals.getBookCopies().getBookId() : null,
                rentals.getReader() != null ? rentals.getReader().getReaderId() : null,
                rentals.getRentalStart(),
                rentals.getRentalEnd()
        );
    }


        public List<RentalsDto> mapToRentalsDtoList(final List<Rentals> rentals){
        return rentals.stream().map(this::mapToRentalsDto).toList();
    }
}
