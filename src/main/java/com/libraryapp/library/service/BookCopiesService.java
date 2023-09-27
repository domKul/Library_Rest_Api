package com.libraryapp.library.service;

import com.libraryapp.library.domain.BookCopies;
import com.libraryapp.library.domain.dto.BookCopiesDto;
import com.libraryapp.library.exception.DataOperationException;
import com.libraryapp.library.exception.PublicationNotFoundException;
import com.libraryapp.library.mapper.BookCopiesMapper;
import com.libraryapp.library.repository.BookCopiesRepository;
import com.libraryapp.library.repository.PublicationsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class BookCopiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCopiesService.class);

    private final BookCopiesMapper bookCopiesMapper;
    private final BookCopiesRepository bookCopiesRepository;
    private final PublicationsRepository publicationsRepository;

    public BookCopiesService(BookCopiesMapper bookCopiesMapper, BookCopiesRepository bookCopiesRepository, PublicationsRepository publicationsRepository) {
        this.bookCopiesMapper = bookCopiesMapper;
        this.bookCopiesRepository = bookCopiesRepository;
        this.publicationsRepository = publicationsRepository;
    }

    @Transactional
    public void addCopyOfBook(final BookCopiesDto bookCopiesDto){
        Objects.requireNonNull(bookCopiesDto,"information cannot be null");
        Long publicationId = bookCopiesDto.publicationId();
        publicationsRepository.findById(publicationId).orElseThrow(
                () -> new PublicationNotFoundException("publication id " + publicationId + " doesn't exist"));
        try{

            BookCopies bookCopies = bookCopiesMapper.mapToBookCopies(bookCopiesDto);
            BookCopies save = bookCopiesRepository.save(bookCopies);
            LOGGER.info("Copy of book have been saved successfully with ID- " + save.getBookId());
        }catch (DataAccessException e){
            LOGGER.error("Error while saving");
            throw new DataOperationException("Something went wrong during save" + e.getMessage());
        }
    }
}
