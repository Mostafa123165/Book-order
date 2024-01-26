package com.amazon.book_store.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.book_store.DAO.BookDAO;
import com.amazon.book_store.entity.Book;

@Service
public class BookServiceImpl  implements BookService{

	@Autowired
	private BookDAO bookDAO ;
	
	@Override
	@Transactional
	public List<Book> findAllBooks() {
		return bookDAO.findAllBooks();
	}

	@Override
	@Transactional
	public Book findBookById(int id) {
		return bookDAO.findBookById(id);

	}

	@Override
	@Transactional
	public List<Book> search(String scText) {
		return bookDAO.search(scText);

	}

	@Override
	@Transactional
	public LinkedHashMap<String, Object> save(Book theBook) {
		return bookDAO.save(theBook);
	}
	
	@Override
	@Transactional
	public LinkedHashMap<String, Object> delete(int id) {
		return bookDAO.delete(id);
	}

}
