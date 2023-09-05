package com.libraryapp.library.service;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.exception.ExceptionMessage;
import com.libraryapp.library.exception.ReaderNotFoundException;
import com.libraryapp.library.mapper.ReaderMapper;
import com.libraryapp.library.repository.ReadersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public Reader addReader(final ReaderDto readerDto){
        Reader reader = readerMapper.mapToReader(readerDto);
        return readersRepository.save(reader);
    }

    public List<ReaderDto> showAllReaders(){
        return readerMapper.mapToReaderDtoList(readersRepository.findAll());
    }

    public ReaderDto findReaderById(long readerId){
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        return readerMapper.mapToReaderDto(reader);
    }
    @Transactional
    public ReaderDto updateReader(long readerId,final ReaderDto readerDto) {
        if (readersRepository.existsById(readerId)) {
            Reader getReader = readerMapper.mapToReaderForUpdate(readerId,readerDto);
            Reader savedReader = readersRepository.save(getReader);
            return readerMapper.mapToReaderDto(savedReader);
        } else {
            throw new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage());
        }
    }

    public void deleteReaderById(long readerId) {
        Reader reader = readersRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(ExceptionMessage.WRONG_READER_ID.getMessage()));
        readersRepository.delete(reader);
    }


}