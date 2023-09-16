package com.libraryapp.library.service;

import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.exception.DuplicatedPublicationException;
import com.libraryapp.library.exception.ExceptionMessage;
import com.libraryapp.library.exception.PublicationNotFoundException;
import com.libraryapp.library.mapper.PublicationsMapper;
import com.libraryapp.library.repository.PublicationsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationsService {

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
        Publications createdPublication = publicationsMapper.mapToPublications(publicationsDto);
        return publicationsRepository.save(createdPublication);
    }

    @Transactional
    public void deletePublicationById(long publicationId) {
        Publications findPublication = publicationsRepository.findById(publicationId)
                .orElseThrow(() -> new PublicationNotFoundException(ExceptionMessage.WRONG_PUBLICATION_ID.getMessage()));
        publicationsRepository.delete(findPublication);
    }

    public List<PublicationsDto> findAllPublications() {
        try {
            List<Publications> all = publicationsRepository.findAll();
            return publicationsMapper.mapToPublicationsListDto(all);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public PublicationsDto findPublicationByTitle(String name) {
        Publications publication = publicationsRepository.findByTitle(name)
                .orElseThrow(() -> new PublicationNotFoundException("Publication not found"));
        return publicationsMapper.mapToPublicationsDto(publication);
    }

    @Transactional
    public void deletePublicationByTitle(String title) {
        Publications publication = publicationsRepository.findByTitle(title)
                .orElseThrow(() -> new PublicationNotFoundException("Publication with title " + title + " not found"));
        publicationsRepository.delete(publication);
    }
}
