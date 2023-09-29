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
                borrowDto.borrowStart(),
                borrowDto.borrowEnd());
    }

    public Optional<BorrowDto> mapToBorrowDto(Borrow borrow) {
        return Optional.ofNullable(borrow)
                .map(rental -> new BorrowDto(
                        Optional.of(rental.getBookCopiesId())
                                .orElseThrow(() ->new BookCopyNotFound("Book are null")),
                        Optional.of(rental.getReader())
                                .orElseThrow(()->new ReaderNotFoundException("Reader are null")),
                        rental.getBorrowStart(),
                        rental.getBorrowEnd()

                ));
    }

    public BorrowDto mapToBorrowDtoForUserBorrows(Borrow borrow) {
        return new BorrowDto(borrow.getReader(),
                borrow.getBookCopiesId(),
                borrow.getBorrowStart(),
                borrow.getBorrowEnd());
    }
    public List<BorrowDto> mapToBorrowsListForUser(final List<Borrow> borrow){
        return borrow.stream().map(this::mapToBorrowDtoForUserBorrows).toList();
    }


    public List<Optional<BorrowDto>> mapToBorrowDtoList(final List<Borrow> borrows) {
        return borrows.stream().map(this::mapToBorrowDto).toList();
    }
}
