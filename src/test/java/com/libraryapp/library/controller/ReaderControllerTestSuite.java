package com.libraryapp.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryapp.library.domain.Reader;
import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.service.ReaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ReaderController.class)
@SpringJUnitWebConfig
public class ReaderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void shouldCreateAndSaveReader() throws Exception {
        //Given
        ReaderDto reader = new ReaderDto("firstName","lastName");
        Reader savedReader = new Reader(0L,"firstName","lastName");

        given(readerService.addReader(any(ReaderDto.class))).willReturn(savedReader);


        //When && Then
        mockMvc.perform(post("/api/v1/readers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reader)))
                .andExpect(status().isCreated());

    }

    @Test
    void shouldFindReaderById() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto("test1","test1");
        Reader reader = new Reader(0L,"test1", "test1");
        Long readerId = reader.getReaderId();

        given(readerService.addReader(readerDto)).willReturn(reader);
        given(readerService.findReaderById(reader.getReaderId())).willReturn(readerDto);

        //When && Then
        mockMvc.perform(get("/api/v1/readers/get/" + readerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(reader.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(reader.getLastName()));



    }

    @Test
    void shouldFindAllReadersInDB() throws Exception {
        //Given

        List<ReaderDto> readerList = List.of(new ReaderDto("firstName1","lastName1"),
                new ReaderDto("firstName2","lastName2"));
        given(readerService.showAllReaders()).willReturn(readerList);

        //When && Then

        mockMvc.perform(get("/api/v1/readers/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("firstName1"))
                .andExpect(jsonPath("$[1].firstName").value("firstName2"))
                .andExpect(status().isOk());

    }


    @Test
    void shouldDeleteReaderFromDB()throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto("test1","test1");
        Reader reader = new Reader(0L,"test1", "test1");
        given(readerService.addReader(readerDto)).willReturn(reader);

        //When
        mockMvc.perform(delete("/api/v1/readers/delete/" + reader.getReaderId()))
                .andExpect(status().isOk());

        //Then
        verify(readerService).deleteReaderById(reader.getReaderId());

    }

    @Test
    void shouldUpdateReaderFromDB()throws Exception{
        //Given
        Reader reader = new Reader(0L,"reader1","reader1");
        ReaderDto readerDto = new ReaderDto("dto1","dto1");
        ReaderDto readerDtoUpdate = new ReaderDto("dto1update","dto1");
        given(readerService.addReader(readerDto)).willReturn(reader);
        given(readerService.updateReader(reader.getReaderId(),readerDto)).willReturn(readerDtoUpdate);

        //When
        mockMvc.perform(put("/api/v1/readers/update/"+ reader.getReaderId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(readerDto)))
                .andExpect(jsonPath("$.firstName").value("dto1update"));

    }

}
