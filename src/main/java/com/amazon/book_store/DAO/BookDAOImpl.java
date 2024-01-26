package com.amazon.book_store.DAO;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory factory ;
	
	@Override
	public List<Book> findAllBooks() {
		
		Session session = factory.getCurrentSession() ;
		
		Query<Book> theQuery  = session.createQuery("FROM Book" , Book.class); 
		
		List<Book> books =  theQuery.getResultList() ;
		
		return books; 
	}

	@Override
	public Book findBookById(int id) {
		
		Session session = factory.getCurrentSession() ;

		Book theBook =  session.get(Book.class,id);
		
		if(theBook == null) {
			throw new NotFoundExc("Not found Book id - " + id);
		}
		
		Hibernate.initialize(theBook.getUser()) ;
		
		return theBook;
	}

	@Override
	public List<Book> search(String scText) {
		Session session = factory.getCurrentSession() ;

		Query<Book> theQuery = session.createQuery("SELECT b FROM Book b WHERE b.title LIKE :scText" , 
													Book.class); 		
		theQuery.setParameter("scText", scText+"%%");
				
		List<Book> books = theQuery.getResultList(); 
		
		session.clear(); 
		
		return books;
	}

	@Override
	public LinkedHashMap<String, Object> save(Book theBook) {
		Session session = factory.getCurrentSession() ;
		
		session.saveOrUpdate(theBook);
		
		LinkedHashMap<String,Object> resp = new LinkedHashMap<>() ;
		resp.put("status", HttpStatus.OK.value());
		resp.put("message","Saved the book successfuly with id - " + theBook.getId() + " for user id - " + theBook.getUser().getId());
		
		return resp;
	}

	@Override
	public LinkedHashMap<String, Object> delete(int id) {
		Session session = factory.getCurrentSession() ;

		Query<?> theQuery = session.createQuery("DELETE FROM Book WHERE id = :bookId"); 
		theQuery.setParameter("bookId",id);
		
		theQuery.executeUpdate() ;
		
		LinkedHashMap<String, Object> resp = new LinkedHashMap<String, Object>() ;
		resp.put("status", HttpStatus.OK.value());
		resp.put("message", "Deleted book successfully id - " + id);

		return resp;
	}

	
}
