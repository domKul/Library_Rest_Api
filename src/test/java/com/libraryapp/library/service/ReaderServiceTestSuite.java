package com.libraryapp.library.service;

import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.repository.ReadersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderServiceTestSuite {

    @Autowired
    private ReaderService readerService;
    @Autowired
    private ReadersRepository readersRepository;

    private ReaderDto reader;

    @BeforeEach
    void dataForTest() {
        reader = new ReaderDto("firstName", "lastName");
    }

    @BeforeEach
    void cleanUp() {
        readersRepository.deleteAll();
    }

    @Test
    void shouldSaveReader() {
        //Given @BeforeEach -> dataForTest()

        //When
        Reader saveReader = readerService.addReader(reader);

        //Then
        assertNotNull(saveReader.getReaderId());
        assertEquals(Reader.class, saveReader.getClass());
        assertEquals(1, readersRepository.count());
        assertEquals("firstName", saveReader.getFirstName());
    }

    @Test
    void shouldDeleteReader() {
        //Given @BeforeEach -> dataForTest()
        Reader savedReader = readerService.addReader(reader);

        //When
        readerService.deleteReaderById(savedReader.getReaderId());

        //Then
        assertEquals(0, readersRepository.count());
        assertEquals(Optional.empty(), readersRepository.findById(savedReader.getReaderId()));


    }

    @Test
    void shouldFindReaderById() {
        //Given @BeforeEach -> dataForTest()
        Reader savedReader = readerService.addReader(reader);

        //When
        ReaderDto readerById = readerService.findReaderById(savedReader.getReaderId());

        //Then
        assertNotNull(readerById);
        assertEquals(savedReader.getFirstName(), readerById.firstName());
        assertEquals(savedReader.getLastName(), readerById.lastName());
    }

    @Test
    void shouldModifyReader() {
        //Given @BeforeEach -> dataForTest()
        Reader savedReader = readerService.addReader(reader);
        ReaderDto modification = new ReaderDto("firstNameMod", "lastNameMod");

        //When
        readerService.updateReader(savedReader.getReaderId(), modification);
        Optional<Reader> byId = readersRepository.findById(savedReader.getReaderId());


        //Then
        assertEquals("firstNameMod", byId.get().getFirstName());
        assertEquals("lastNameMod", byId.get().getLastName());

    }

    @Test
    void shouldFindListOfReaders() {
        //Given
        ReaderDto reader1 = new ReaderDto("reader1First", "reader1Last");
        ReaderDto reader2 = new ReaderDto("reader2First", "reader2Last");
        ReaderDto reader3 = new ReaderDto("reader3First", "reader3Last");
        readerService.addReader(reader1);
        readerService.addReader(reader2);
        readerService.addReader(reader3);

        //When
        List<ReaderDto> readerDtos = readerService.showAllReaders();

        //Then
        assertEquals(3, readerDtos.size());
        assertEquals("reader3First", readerDtos.get(2).firstName());
        assertEquals("reader2First", readerDtos.get(1).firstName());
        assertEquals("reader1First", readerDtos.get(0).firstName());
        assertEquals("reader3Last", readerDtos.get(2).lastName());
        assertEquals("reader2Last", readerDtos.get(1).lastName());
        assertEquals("reader1Last", readerDtos.get(0).lastName());

    }

    @Test
    void shouldFindEmptyListWothoutReaders() {
        //Given no data given for this test

        //When
        List<ReaderDto> readerDtos = readerService.showAllReaders();

        //Then
        assertTrue(readerDtos.isEmpty());
    }


}
