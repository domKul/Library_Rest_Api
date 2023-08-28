package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("v1/readers")
public class ReaderController {
    private final ReaderService readerService;


    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto){
        readerService.addReader(readerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
