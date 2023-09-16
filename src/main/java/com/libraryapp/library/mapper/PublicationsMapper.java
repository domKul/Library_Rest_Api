package com.libraryapp.library.mapper;

import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicationsMapper {

    public PublicationsDto mapToPublicationsDto(Publications publications) {
        return new PublicationsDto(publications.getTitle(),
                publications.getTitle(),
                publications.getPublicationYear());
    }

    public Publications mapToPublications(PublicationsDto publicationsDto) {
        return new Publications(
                publicationsDto.title(),
                publicationsDto.author(),
                publicationsDto.publicationYear());
    }

    public List<PublicationsDto> mapToPublicationsListDto(final List<Publications> publications) {
        return publications.stream()
                .map(this::mapToPublicationsDto)
                .toList();
    }
}
