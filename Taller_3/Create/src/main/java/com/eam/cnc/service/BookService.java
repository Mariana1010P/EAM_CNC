package com.eam.cnc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.cnc.model.Book;
import com.eam.cnc.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

    public Optional<Book> getBookById(Long isbn) {
        return repository.findById(isbn);
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    
}
