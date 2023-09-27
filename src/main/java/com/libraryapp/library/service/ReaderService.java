package com.libraryapp.library.service;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.exception.ExceptionMessage;
import com.libraryapp.library.exception.ReaderNotFoundException;
import com.libraryapp.library.mapper.ReaderMapper;
import com.libraryapp.library.repository.ReadersRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderService.class);

    private final ReadersRepository readersRepository;
    private final ReaderMapper readerMapper;


    public ReaderService(ReadersRepository readersRepository, ReaderMapper readerMapper) {
        this.readersRepository = readersRepository;
        this.readerMapper = readerMapper;
    }

    @Transactional
    public Reader addReader(final ReaderDto readerDto) {
         Objects.requireNonNull(readerDto, "cannot be null");
        try{
            Reader reader = readerMapper.mapToReader(readerDto);
            Reader save = readersRepository.save(reader);
            LOGGER.info("Reader saved successfully with ID- " + save.getReaderId());
            return save;
        }catch (RuntimeException e){
            LOGGER.error("An Error has occurred"+ e.getMessage());
            throw new RuntimeException("An error occurred while adding the reader: " + e.getMessage(), e);
        }
    }

    public List<ReaderDto> showAllReaders() {
        List<Reader> allReaders = readersRepository.findAll();
        LOGGER.info("Listing readers");
        return allReaders.stream().map(readerMapper::mapToReaderDto)
                .collect(Collectors.toList());
    }

    public ReaderDto findReaderById(long readerId) {
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        return readerMapper.mapToReaderDto(reader);
    }

    @Transactional
    public ReaderDto updateReader(long readerId, final ReaderDto readerDto) {
        ReaderDto readerUpdate = readersRepository.findById(readerId)
                .map(existingReader -> {
                    Reader mapReader = readerMapper.mapToReaderForUpdate(readerId, readerDto);
                    Reader savedReader = readersRepository.save(mapReader);
                    return readerMapper.mapToReaderDto(savedReader);
                })
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        LOGGER.info("ReaderUpdate succes");
        return readerUpdate;

    }

    public void deleteReaderById(long readerId) {
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
         readersRepository.delete(reader);
         LOGGER.info("Reader with ID " + reader.getReaderId()+ "deleted");
    }


}