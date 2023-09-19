package com.libraryapp.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.exception.PublicationNotFoundException;
import com.libraryapp.library.service.PublicationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PublicationController.class)
@SpringJUnitWebConfig
public class PublicationControllerTestSuite {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PublicationsService publicationsService;

    @Autowired
    private MockMvc mockMvc;

    Publications savedPublication;
    Publications publications2;
    Publications publications3;
    PublicationsDto publicationsdto;
    PublicationsDto publicationsdto2;

    @BeforeEach
    void dataForTests() {
        publications2 = new Publications(0L, "title2", "author2", 1990);
        publications3 = new Publications(1L, "title3", "author3", 1900);
        savedPublication = new Publications(2L, "title1", "title1", 2010);
        publicationsdto = new PublicationsDto("title1", "author1", 2010);
        publicationsdto2 = new PublicationsDto("title1", "title1", 2010);


    }

    @Test
    void shouldSavePublication() throws Exception {
        //Given
        dataForTests();
        given(publicationsService.addPublication(publicationsdto)).willReturn(savedPublication);

        //When
        mockMvc.perform(post("/api/v1/publication/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicationsdto)))
                .andExpect(status().isCreated());

    }

    @Test
    void shouldDeletePublicationFromDbByTitle() throws Exception {
        //Given
        dataForTests();
        given(publicationsService.addPublication(publicationsdto)).willReturn(savedPublication);

        //When
        mockMvc.perform(delete("/api/v1/publication/delete/" + savedPublication.getTitle()))
                .andExpect(status().isAccepted());

        //Then
        verify(publicationsService).deletePublicationByTitle(savedPublication.getTitle());
    }

    @Test
    void shouldHandleFindByName404PublicationNotFound() throws Exception {
        //Given
        dataForTests();
        String title = "nonexistentTitle";
        given(publicationsService.findPublicationByTitle(title))
                .willThrow(new PublicationNotFoundException("Not Found"));

        //When
        mockMvc.perform(get("/api/v1/publication/by-name/{title}", title)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRetrievePublicationByTitle() throws Exception {
        //Give
        dataForTests();
        String title = savedPublication.getTitle();
        given(publicationsService.findPublicationByTitle(title)).willReturn(publicationsdto);

        //When
        mockMvc.perform(get("/api/v1/publication/by-name/title1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.author").value("author1"));

        //Then
        verify(publicationsService).findPublicationByTitle(title);
    }

    @Test
    void shouldGetListOfPublications() throws Exception {
        //Given
        dataForTests();
        List<PublicationsDto> publicationsList = List.of(publicationsdto,publicationsdto2);
        given(publicationsService.findAllPublications()).willReturn(publicationsList);

        //When
        mockMvc.perform(get("/api/v1/publication/all")
                .content(objectMapper.writeValueAsString(publicationsList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.[0].title").value("title1"))
                .andExpect(jsonPath("$.[1].title").value("title1"));

        //Then
        verify(publicationsService).findAllPublications();

    }


}
