package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.service.PublicationsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/publication")
public class PublicationController {

    private final PublicationsService publicationsService;

    public PublicationController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPublication(@Valid @RequestBody PublicationsDto publicationsDto){
        publicationsService.addPublication(publicationsDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
