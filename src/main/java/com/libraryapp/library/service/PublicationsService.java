package com.libraryapp.library.service;

import com.libraryapp.library.domain.Publications;
import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.exception.DuplicatedPublicationException;
import com.libraryapp.library.mapper.PublicationsMapper;
import com.libraryapp.library.repository.PublicationsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PublicationsService {

    private final PublicationsRepository publicationsRepository;
    private final PublicationsMapper publicationsMapper;

    public PublicationsService(PublicationsRepository publicationsRepository, PublicationsMapper publicationsMapper) {
        this.publicationsRepository = publicationsRepository;
        this.publicationsMapper = publicationsMapper;
    }

    @Transactional
    public Publications addPublication(@Valid PublicationsDto publicationsDto) {
        String title = publicationsDto.title();
        if(publicationsRepository.existsByTitle(title)){
            throw new DuplicatedPublicationException("Publication "+ title + " already exist");
        }
        Publications createdPublication = publicationsMapper.mapToPublications(publicationsDto);
        return publicationsRepository.save(createdPublication);
    }
}
