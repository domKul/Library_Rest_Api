package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderMapper {

    public Reader mapToReader(ReaderDto readerDto){
        return new Reader(null,
                readerDto.getFirstName(),
                readerDto.getLastName());
    }
    public Reader mapToReaderForUpdate(long readerId,ReaderDto readerDto){
        return new Reader(readerId,
                readerDto.getFirstName(),
                readerDto.getLastName());
    }
    public ReaderDto mapToReaderDto(Reader reader){
        return new ReaderDto(reader.getFirstName(),
                reader.getLastName());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader>readers){
        return readers.stream().map(this::mapToReaderDto).toList();
    }
}
