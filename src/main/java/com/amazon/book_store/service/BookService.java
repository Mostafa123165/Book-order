package com.amazon.book_store.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.amazon.book_store.entity.Book;

public interface BookService {

	public List<Book> findAllBooks();
	
	public Book findBookById(int id) ;
	
	public List<Book> search(String scText);

	public LinkedHashMap<String,Object> save(Book theBook);
	
	public LinkedHashMap<String,Object> delete(int id);
}
