package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.PublicationsDto;
import com.libraryapp.library.service.PublicationsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publication")
public class PublicationController {

    private final PublicationsService publicationsService;

    public PublicationController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "add_new")
    public ResponseEntity<Void> createPublication(@Valid @RequestBody PublicationsDto publicationsDto) {
        publicationsService.addPublication(publicationsDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "findByName/{title}")
    public ResponseEntity<PublicationsDto> findPublicationByName(@PathVariable String title){
        return ResponseEntity.ok(publicationsService.findPublicationByTitle(title));
    }

    @GetMapping(value = "getAllPublications")
    public ResponseEntity<List<PublicationsDto>>showAllPublications(){
        return ResponseEntity.ok(publicationsService.findAllPublications());
    }
    @DeleteMapping(value = "deletePublication/{title}")
    public ResponseEntity<Void>deletePublicationByTitle(@PathVariable String title){
         publicationsService.deletePublicationByTitle(title);
         return ResponseEntity.ok().build();
    }

}
