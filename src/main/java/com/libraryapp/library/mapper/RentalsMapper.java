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
        Rentals rentals = new Rentals();
        rentals.setRentalStart(rentalsDto.getRentalStart());
        rentals.setRentalEnd(rentalsDto.getRentalEnd());
        rentals.setReader(reader);
        rentals.setBookCopies(bookCopies);
        return rentals;
    }
    public RentalsDto mapToRentalsDto(Rentals rentals){
        return new RentalsDto(
                rentals.getBookCopies().getBookId(),
                rentals.getReader().getReaderId(),
                rentals.getRentalStart(),
                rentals.getRentalEnd());
    }

    public List<RentalsDto> mapToRentalsDtoList(final List<Rentals> rentals){
        return rentals.stream().map(this::mapToRentalsDto).toList();
    }
}
