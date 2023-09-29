package com.libraryapp.library.service;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.BookCopiesDto;
import com.libraryapp.library.exception.BookCopyNotFound;
import com.libraryapp.library.exception.DataOperationException;
import com.libraryapp.library.exception.PublicationNotFoundException;
import com.libraryapp.library.mapper.BookCopiesMapper;
import com.libraryapp.library.repository.BookCopiesRepository;
import com.libraryapp.library.repository.PublicationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BookCopiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCopiesService.class);

    private final BookCopiesMapper bookCopiesMapper;
    private final BookCopiesRepository bookCopiesRepository;
    private final PublicationsRepository publicationsRepository;

    public BookCopiesService(BookCopiesMapper bookCopiesMapper
            , BookCopiesRepository bookCopiesRepository
            , PublicationsRepository publicationsRepository) {
        this.bookCopiesMapper = bookCopiesMapper;
        this.bookCopiesRepository = bookCopiesRepository;
        this.publicationsRepository = publicationsRepository;
    }

    @Transactional
    public void addCopyOfBook(final BookCopiesDto bookCopiesDto) {
        Objects.requireNonNull(bookCopiesDto, "information cannot be null");
        Long publicationId = bookCopiesDto.publicationId();
        publicationsRepository.findById(publicationId).orElseThrow(
                () -> new PublicationNotFoundException("publication id " + publicationId + " doesn't exist"));
        try {

            BookCopies bookCopies = bookCopiesMapper.mapToBookCopies(bookCopiesDto);
            BookCopies save = bookCopiesRepository.save(bookCopies);
            LOGGER.info("Copy of book have been saved successfully with id " + save.getBookId());
        } catch (DataAccessException e) {
            LOGGER.error("Error while saving");
            throw new DataOperationException("Something went wrong during save" + e.getMessage());
        }
    }

    @Transactional
    public void deleteBookCopy(final long bookCopyId) {
        try {
            BookCopies bookCopies = bookCopiesRepository.findById(bookCopyId)
                    .orElseThrow(() -> new BookCopyNotFound("Wrong id" + bookCopyId));
            bookCopiesRepository.delete(bookCopies);
            LOGGER.info("copy of book with id" + bookCopyId + " deleted");
        } catch (DataAccessException e) {
            LOGGER.error("Error while saving");
            throw new DataOperationException("Something went wrong when deleting" + e.getMessage());
        }
    }

    @Transactional
    public void statusChangeOfBookCopy(final long bookCopyId, String status) {
        if (status == null || status.isEmpty()) {
            LOGGER.error("Status cannot be empty or null.");
            throw new IllegalArgumentException("Status cannot be empty or null.");
        }

        BookCopies bookCopyFindedById = bookCopiesRepository.findById(bookCopyId)
                .orElseThrow(() -> new BookCopyNotFound("Wrong id " + bookCopyId));
        try {
            bookCopyFindedById.setStatus(status);
            bookCopiesRepository.save(bookCopyFindedById);
            LOGGER.info("Status of book copy with id " + bookCopyId + " updated to " + status);
        } catch (DataAccessException e) {
            LOGGER.error("Error while saving");
            throw new DataOperationException("Something went wrong when updating the status" + e.getMessage());
        }
    }

    public List<BookCopiesDto> getListOfAllBookCopies() {
        try {
            List<BookCopies> allBooks = bookCopiesRepository.findAll();
            LOGGER.info("list are loaded");
            return bookCopiesMapper.mapToListBookCopiesDto(allBooks);
        } catch (DataAccessException e) {
            LOGGER.error("loading of books copy list fail");
            throw new DataOperationException("Data access fail " + e.getMessage());
        }
    }


    public long findNumbersOfCopyWithAvailableStatus(String title) {
        Publications publication = publicationsRepository.findByTitle(title)
                .orElseThrow(() -> new PublicationNotFoundException("No publication with title " + title));
        LOGGER.info("success finding for Available status for title " + title);
        return bookCopiesRepository.countAvailableCopiesByTitle(publication.getPublicationId());
    }

}
