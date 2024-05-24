package com.eam.cnc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eam.cnc.model.Book;
import com.eam.cnc.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
    private BookService bookService;

    @GetMapping("/get/{isbn}")
    public ResponseEntity<?> getBookById(@PathVariable Long isbn) {
        Optional<Book> book = bookService.getBookById(isbn);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
