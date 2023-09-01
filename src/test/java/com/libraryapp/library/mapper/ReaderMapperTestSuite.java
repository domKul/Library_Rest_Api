package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReaderMapperTestSuite {

    @Autowired
    private ReaderMapper readerMapper;
    Reader reader;
    ReaderDto readerDto;
    ReaderDto expectedDto;

    @BeforeEach
    void testData() {
        reader = new Reader(0L, "test1", "test1");
        readerDto = new ReaderDto("test1", "test1");
        expectedDto = new ReaderDto("test1", "test1");
    }


    @Test
    void shouldMapToDto() {
        //Given


        //When
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);

        //Then
        assertEquals(expectedDto.firstName(), readerDto.firstName());
        assertEquals(expectedDto.lastName(), readerDto.lastName());
    }

    @Test
    void shouldMapFromDto() {
        //Given


        //When
        Reader reader1 = readerMapper.mapToReader(readerDto);

        //Then
        assertEquals(reader.getCreationDate(), reader1.getCreationDate());
        assertEquals(reader.getFirstName(), reader1.getFirstName());
        assertEquals(reader.getLastName(), reader1.getLastName());
    }

    @Test
    void shouldMapToReaderForUpdate() {
        //Given


        //When
        Reader reader1 = readerMapper.mapToReaderForUpdate(0, readerDto);

        //Then
        assertEquals(reader.getReaderId(), reader1.getReaderId());

    }

    @Test
    void shouldMapToReaderDtoList() {
        //Given
        List<Reader> readerList = List.of(new Reader(0L, "text2", "text2"), new Reader(0L, "text3", "text3"), new Reader(0L, "text4", "text4"));

        //When
        List<ReaderDto> readerDtos = readerMapper.mapToReaderDtoList(readerList);

        //Then
        assertEquals(3, readerDtos.size());
        assertEquals(ReaderDto.class, readerDtos.get(0).getClass());
    }
}
