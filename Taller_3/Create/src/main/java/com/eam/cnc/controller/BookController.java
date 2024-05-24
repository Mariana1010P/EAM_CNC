package com.eam.cnc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eam.cnc.model.Book;
import com.eam.cnc.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
    private BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Validated @RequestBody Book book, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid book data");
        }
        
        Optional<Book> existingBook = bookService.getBookById(book.getIsbn());
        if (existingBook.isPresent()) {
            return ResponseEntity.badRequest().body("Book already exists");
        }

        Book newBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

}
