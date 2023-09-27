package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.BookCopiesDto;
import com.libraryapp.library.service.BookCopiesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Book-Copies")
public class BookCopiesController {

    private final BookCopiesService bookCopiesService;

    public BookCopiesController(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "add")
    public void addCopyOfBook(@RequestBody BookCopiesDto bookCopiesDto){
        bookCopiesService.addCopyOfBook(bookCopiesDto);
    }
}
