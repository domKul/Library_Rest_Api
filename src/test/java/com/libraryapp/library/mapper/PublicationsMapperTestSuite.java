package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PublicationsMapperTestSuite {

    @Autowired
    private PublicationsMapper publicationsMapper;

    Publications publications;
    PublicationsDto publicationsDto;

    @BeforeEach
    void testData() {
        publications = new Publications(0L, "test1", "test1", 2000);
        publicationsDto = new PublicationsDto("test2", "test2", 2000);
    }

    @Test
    void shouldMapToPublications() {
        //Gven

        //When
        Publications publications1 = publicationsMapper.mapToPublications(publicationsDto);

        //Then
        assertEquals("test2", publications1.getTitle());
    }

    @Test
    void shouldMapToPublicationsDto() {
        //Given

        //When
        PublicationsDto publicationsDto1 = publicationsMapper.mapToPublicationsDto(publications);

        //Then
        assertEquals("test1", publicationsDto1.getTitle());
    }

    @Test
    void shouldMapToPublicationsDtoList() {
        //Given
        List<Publications> publicationsList = Arrays.asList(
                new Publications(0L, "test1", "test1", 2000),
                new Publications(0L, "test2", "test1", 2000),
                new Publications(0L, "test3", "test1", 2000));

        //When
        List<PublicationsDto> publicationsDtos = publicationsMapper.mapToPublicationsListDto(publicationsList);

        //Then
        assertEquals(PublicationsDto.class, publicationsDtos.get(0).getClass());
        assertEquals(3, publicationsDtos.size());


    }


}
