package com.libraryapp.library.service;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Borrow;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.BorrowDto;
import com.libraryapp.library.exception.BookCopyAlreadyTakenException;
import com.libraryapp.library.exception.BorrowNotFoundException;
import com.libraryapp.library.exception.ReaderNotFoundException;
import com.libraryapp.library.mapper.BorrowMapper;
import com.libraryapp.library.repository.BookCopiesRepository;
import com.libraryapp.library.repository.BorrowRepository;
import com.libraryapp.library.repository.ReadersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

   private final BorrowRepository borrowRepository;
   private final BorrowMapper borrowMapper;
   private final BookCopiesRepository bookCopiesRepository;
   private final ReadersRepository readersRepository;

   private static final Logger LOGGER = LoggerFactory.getLogger(BorrowService.class);

    public BorrowService(BorrowRepository borrowRepository, BorrowMapper borrowMapper, BookCopiesRepository bookCopiesRepository, ReadersRepository readersRepository) {
        this.borrowRepository = borrowRepository;
        this.borrowMapper = borrowMapper;
        this.bookCopiesRepository = bookCopiesRepository;
        this.readersRepository = readersRepository;
    }


    @Transactional
    public void createBorrow(BorrowDto borrowDto) {
        Long bookCopiesId = borrowDto.bookId();
        Optional<BookCopies> bookCopiesOptional = bookCopiesRepository.findById(bookCopiesId);
        Optional<Reader> readerOptional = readersRepository.findById(borrowDto.readerId());

        if (bookCopiesOptional.isEmpty() || readerOptional.isEmpty()) {
            LOGGER.error("Problem with book copy ID or reader ID");
            throw new ReaderNotFoundException("Wrong book copy ID or reader ID");
        }

        BookCopies bookCopy = bookCopiesOptional.get();
        if (bookCopy.getStatus().equals("Borrowed")) {
            LOGGER.error("Book copy already borrowed");
            throw new BookCopyAlreadyTakenException("Book copy already borrowed");
        }

        createBorrowTakenStatus(borrowDto, bookCopy);
    }

    private void createBorrowTakenStatus(BorrowDto borrowDto, BookCopies bookCopy) {
        Borrow mappingFromDto = borrowMapper.mapToBorrow(borrowDto);
        bookCopy.setStatus("Borrowed");
        borrowRepository.save(mappingFromDto);
        LOGGER.info("New book borrow has been created with ID " + mappingFromDto.getBorrowId());
    }

    @Transactional
    public void endOfBorrow(final long borrowId){
        Optional<Borrow> borrowFinded = borrowRepository.findById(borrowId);
        if(borrowFinded.isPresent()){
        Optional<BookCopies> getBookCopy = bookCopiesRepository.findById(borrowFinded.get().getBookCopiesId());
        getBookCopy.ifPresent(bookCopies -> bookCopies.setStatus("Not Borrowed"));
        LOGGER.info("borrow with id " + borrowId + " ended");
        }else {
            LOGGER.error("Borrow with given id " + borrowId + " not found");
            throw new BorrowNotFoundException("Borrow with given id " + borrowId + " not found");

        }
        borrowRepository.delete(borrowFinded.get());
    }

    public List<BorrowDto> findBorrowsOfUser(long userId){
        try{
            Optional<Reader> reader = Optional.ofNullable(readersRepository.findById(userId)
                    .orElseThrow(() -> new ReaderNotFoundException("Reader with id " + userId + " are not existed")));

            if(reader.isPresent()){
            List<Borrow> allByReader = borrowRepository.findAllByReader(reader.get().getReaderId());
            return borrowMapper.mapToBorrowsListForUser(allByReader);
            }
        }catch (ReaderNotFoundException e){
            LOGGER.error("Wrong reader id");
            throw e;
        }
        return new ArrayList<>();
    }



}
