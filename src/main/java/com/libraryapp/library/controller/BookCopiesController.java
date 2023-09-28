package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.BookCopiesDto;
import com.libraryapp.library.service.BookCopiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book-copies")
public class BookCopiesController {

    private final BookCopiesService bookCopiesService;

    public BookCopiesController(BookCopiesService bookCopiesService) {
        this.bookCopiesService = bookCopiesService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "add")
    public ResponseEntity<Void> addCopyOfBook(@RequestBody BookCopiesDto bookCopiesDto) {
        bookCopiesService.addCopyOfBook(bookCopiesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "status-update")
    public void changeStatus(@RequestParam long bookcopyId, @RequestParam String status) {
        bookCopiesService.statusChangeOfBookCopy(bookcopyId, status);
    }

    @DeleteMapping(value = "delete")
    public void deleteCopyOfBook(@RequestParam long bookCOpyId) {
        bookCopiesService.deleteBookCopy(bookCOpyId);
    }
    @GetMapping(value = "all")
    public List<BookCopiesDto>findListOfAllBookCopies(){
        return bookCopiesService.getListOfAllBookCopies();
    }

    @GetMapping(value = "find-available-copies-by-title/{title}")
    public Long findAvailableCopiesByTitle(@PathVariable String title){
        return bookCopiesService.findNumbersOfCopyWithAvailableStatus(title);
    }

}
