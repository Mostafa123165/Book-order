package com.amazon.book_store.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.Book;
import com.amazon.book_store.entity.User;
import com.amazon.book_store.service.BookService;
import com.amazon.book_store.service.UserService;
import com.amazon.book_store.view.BookView;
import com.amazon.book_store.view.SharedUserBublicAndBookBublic;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class BookController {

	@Autowired
	private BookService bookSerivce; 	
	
	@Autowired
	private UserService userService; 	

	@GetMapping("/books")
	@JsonView({BookView.Internal.class})
	public List<Book> findAllBooks(){
		
		return bookSerivce.findAllBooks() ;
	} 
	
	
	@GetMapping("/books/{id}")
	@JsonView({BookView.BookINFO.class})
	public Book findBookById(@PathVariable int id){
		
		return bookSerivce.findBookById(id) ;
	} 
	
	@GetMapping("/books/search")
	@JsonView({SharedUserBublicAndBookBublic.class})
	public List<Book> search(@RequestParam(name = "st") String name){
		
		List<Book> books= bookSerivce.search(name) ;
		return books;
	} 
	
	@PostMapping("/books") 
	public Map<String,Object> save(@Valid @RequestBody Book theBook) {
			
		int userId = theBook.getUser().getId() ;
		
		if(userService.findUserById(userId,true) == null ) {
			throw new NotFoundExc("Not found user id - " + userId);
		}
				
		theBook.setPublisherDate(LocalDate.now().toString()) ;
				
		return bookSerivce.save(theBook) ;
	}	

	@DeleteMapping("/books/{id}") 
	public Map<String,Object> delete(@PathVariable int id) {
			
		bookSerivce.findBookById(id);
		
		return bookSerivce.delete(id) ;
	}
	

	@PutMapping("/books") 
	public Map<String,Object> update(@Valid @RequestBody Book theBook) {
								
		// for get owner of book 
		Book book = bookSerivce.findBookById(theBook.getId()) ;

		User theUser = new User() ;
		
		theUser.setId(book.getUser().getId()); 
		
		theBook.setUser(theUser) ;
		
		theBook.setPublisherDate(LocalDate.now().toString()) ;
				
		return bookSerivce.save(theBook) ;
	}	
}
