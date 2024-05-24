package com.eam.cnc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/update/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable Long isbn, @Validated @RequestBody Book updatedBook, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid book data");
        }

        Optional<Book> existingBook = bookService.getBookById(isbn);
        if (!existingBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book savedBook = bookService.updateBook(isbn, updatedBook);
        return ResponseEntity.ok(savedBook);
    }

    
}
