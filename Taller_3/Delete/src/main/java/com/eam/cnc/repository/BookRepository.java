package com.eam.cnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eam.cnc.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}