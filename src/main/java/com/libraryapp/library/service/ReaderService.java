package com.libraryapp.library.service;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.exception.ExceptionMessage;
import com.libraryapp.library.exception.ReaderNotFoundException;
import com.libraryapp.library.mapper.ReaderMapper;
import com.libraryapp.library.repository.ReadersRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderService {

    private final ReadersRepository readersRepository;
    private final ReaderMapper readerMapper;


    public ReaderService(ReadersRepository readersRepository, ReaderMapper readerMapper) {
        this.readersRepository = readersRepository;
        this.readerMapper = readerMapper;
    }

    @Transactional
    public Reader addReader(final ReaderDto readerDto) {
        try{
            Reader reader = readerMapper.mapToReader(readerDto);
            return readersRepository.save(reader);
        }catch (RuntimeException e){
            throw new RuntimeException("An error occurred while adding the reader: " + e.getMessage(), e);

        }
    }

    public List<ReaderDto> showAllReaders() {
        try{
            List<Reader> all = readersRepository.findAll();
            return readerMapper.mapToReaderDtoList(all);
        }catch (RuntimeException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ReaderDto findReaderById(long readerId) {
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        return readerMapper.mapToReaderDto(reader);
    }

    @Transactional
    public ReaderDto updateReader(long readerId, final ReaderDto readerDto) {
        return readersRepository.findById(readerId)
                .map(existingReader->{
                            Reader mapReader = readerMapper.mapToReaderForUpdate(readerId, readerDto);
                            Reader savedReader = readersRepository.save(mapReader);
                            return readerMapper.mapToReaderDto(savedReader);
                        })
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));

    }

    @Transactional
    public void deleteReaderById(long readerId) {
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        readersRepository.delete(reader);
    }


}