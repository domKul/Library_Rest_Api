package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.BorrowDto;
import com.libraryapp.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "create-borrow")
    public ResponseEntity<Void> creatingNewBorrow(@RequestBody BorrowDto borrowDto){
        borrowService.createBorrow(borrowDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "end-of-borrowing/{borrowId}")
    public void endOfBorrowing(@PathVariable long borrowId){
        borrowService.endOfBorrow(borrowId);
    }

    @GetMapping(value = "reader-borrows/{readerId}")
    public List<BorrowDto> findAllBorrowsOfReader(@PathVariable long readerId){
      return  borrowService.findBorrowsOfUser(readerId);
    }
}
