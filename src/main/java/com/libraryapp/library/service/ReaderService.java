package com.libraryapp.library.service;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
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
        return readerMapper.mapToReaderDtoList((List<Reader>) readersRepository.findAll());
    }



}