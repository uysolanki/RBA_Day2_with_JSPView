package com.greatlearning.rbanewfrontend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.rbanewfrontend.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);

}
