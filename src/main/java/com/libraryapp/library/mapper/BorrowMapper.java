package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.Borrow;
import com.libraryapp.library.domain.dto.BorrowDto;
import com.libraryapp.library.exception.BookCopyNotFound;
import com.libraryapp.library.exception.ReaderNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BorrowMapper {


    public Borrow mapToBorrow(BorrowDto borrowDto) {

        return new Borrow(
                borrowDto.readerId(),
                borrowDto.bookId(),
                borrowDto.rentalStart(),
                borrowDto.rentalEnd());
    }

    public Optional<BorrowDto> mapToBorrowDto(Borrow borrow) {
        return Optional.ofNullable(borrow)
                .map(rental -> new BorrowDto(
                        Optional.of(rental.getBookCopiesId())
                                .orElseThrow(() ->new BookCopyNotFound("Book are null")),
                        Optional.of(rental.getReader())
                                .orElseThrow(()->new ReaderNotFoundException("Reader are null")),
                        rental.getRentalStart(),
                        rental.getRentalEnd()

                ));
    }


    public List<Optional<BorrowDto>> mapToBorrowDtoList(final List<Borrow> rentals) {
        return rentals.stream().map(this::mapToBorrowDto).toList();
    }
}
