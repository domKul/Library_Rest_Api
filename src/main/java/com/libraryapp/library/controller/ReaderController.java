package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.ReaderDto;
import com.libraryapp.library.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/readers")
public class ReaderController {
    private final ReaderService readerService;


    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "add")
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto) {
        readerService.addReader(readerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("all")
    public ResponseEntity<List<ReaderDto>> getReaders() {
        return ResponseEntity.ok(readerService.showAllReaders());
    }

    @GetMapping(value = "get/{readerId}")
    public ResponseEntity<ReaderDto> getReaderById(@PathVariable long readerId){
        return ResponseEntity.ok(readerService.findReaderById(readerId));
    }

    @PutMapping(value = "update/{readerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> updateReader(@PathVariable long readerId,
                                                  @RequestBody ReaderDto readerDto) {
        return ResponseEntity.ok(readerService.updateReader(readerId, readerDto));
    }

    @DeleteMapping(value = "delete/{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable long readerId) {
        readerService.deleteReaderById(readerId);
        return ResponseEntity.ok().build();
    }

}
