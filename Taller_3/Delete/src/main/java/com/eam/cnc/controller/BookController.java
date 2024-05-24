package com.eam.cnc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable Long isbn) {
        Optional<Book> existingBook = bookService.getBookById(isbn);
        if (!existingBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}
