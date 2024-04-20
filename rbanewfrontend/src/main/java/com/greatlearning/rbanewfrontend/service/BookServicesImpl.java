package com.greatlearning.rbanewfrontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.rbanewfrontend.entity.Book;
import com.greatlearning.rbanewfrontend.repository.BookRepository;

@Service
public class BookServicesImpl implements BookServices{

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		List<Book> books=bookRepository.findAll();
		return books;
	}

	@Override
	public Book findById(int theId) {
		// TODO Auto-generated method stub
		return bookRepository.findById(theId).get();
	}

	@Override
	public void save(Book theBook) {
		// TODO Auto-generated method stub
		bookRepository.save(theBook);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(theId);
		
	}

	@Override
	public List<Book> searchBy(String name, String author) {
		// TODO Auto-generated method stub
		List<Book> books=bookRepository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
		return books;
	}
	

}
