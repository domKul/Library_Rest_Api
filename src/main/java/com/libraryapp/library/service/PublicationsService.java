package com.libraryapp.library.service;

import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.exception.DuplicatedPublicationException;
import com.libraryapp.library.exception.ExceptionMessage;
import com.libraryapp.library.exception.PublicationNotFoundException;
import com.libraryapp.library.mapper.PublicationsMapper;
import com.libraryapp.library.repository.PublicationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationsService.class);

    private final PublicationsRepository publicationsRepository;
    private final PublicationsMapper publicationsMapper;

    public PublicationsService(PublicationsRepository publicationsRepository, PublicationsMapper publicationsMapper) {
        this.publicationsRepository = publicationsRepository;
        this.publicationsMapper = publicationsMapper;
    }

    @Transactional
    public Publications addPublication(final PublicationsDto publicationsDto) {
        String title = publicationsDto.title();
        if (publicationsRepository.existsByTitle(title)) {
            throw new DuplicatedPublicationException("Publication " + title + " already exist");
        }
        try{
            Publications createdPublication = publicationsMapper.mapToPublications(publicationsDto);
            LOGGER.info("Publication saved");
            return publicationsRepository.save(createdPublication);
        }catch (RuntimeException e){
            LOGGER.error("An Error has occurred"+ e.getMessage());
            throw new RuntimeException("An error occurred while adding the reader: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deletePublicationById(long publicationId) {
        Publications findPublication = publicationsRepository.findById(publicationId)
                .orElseThrow(() -> new PublicationNotFoundException(ExceptionMessage.WRONG_PUBLICATION_ID.getMessage()));
        publicationsRepository.delete(findPublication);
        LOGGER.info("Publication with id " + findPublication.getPublicationId());
    }

    public List<PublicationsDto> findAllPublications() {
        List<Publications> allPublications = publicationsRepository.findAll();
        LOGGER.info("all publications are found");
        return allPublications.stream()
                .map(publicationsMapper::mapToPublicationsDto)
                .collect(Collectors.toList());
    }

    public PublicationsDto findPublicationByTitle(String title) {
        Publications publication = publicationsRepository.findByTitle(title)
                .orElseThrow(() -> new PublicationNotFoundException("Publication not found"));
        LOGGER.info("publication with title " + title + " found");
        return publicationsMapper.mapToPublicationsDto(publication);
    }

    public void deletePublicationByTitle(String title) {
        Publications publication = publicationsRepository.findByTitle(title)
                .orElseThrow(() -> new PublicationNotFoundException("Publication with title " + title + " not found"));
        publicationsRepository.delete(publication);
        LOGGER.info("Publication" + title + "deleted");
    }
}
