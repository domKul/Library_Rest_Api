package com.libraryapp.library.controller;

import com.libraryapp.library.domain.dto.BorrowDto;
import com.libraryapp.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
