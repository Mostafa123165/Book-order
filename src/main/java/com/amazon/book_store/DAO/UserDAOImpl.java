package com.amazon.book_store.DAO;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public List<User> findAllUser() {
				
		Session session = sessionFactory.getCurrentSession() ;

		Query<User>  theQuery = session.createQuery("FROM User" , User.class );
		List<User> users = theQuery.getResultList() ;
				
		return users;
	}

	@Override
	public User findUserById(int id,Boolean initializeBook) {
		
		Session session = sessionFactory.getCurrentSession() ;
		
		Query<User> theQuery = session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :userId",
												   User.class);
		theQuery.setParameter("userId", id);
				
		User theUser = null ;
		
		try {
			theUser = theQuery.getSingleResult() ;
			if(initializeBook) Hibernate.initialize(theUser.getBooks());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		if(theUser == null) {
			throw new NotFoundExc("Not found user id - " + id);
		}
				
		session.clear(); 
		
		return theUser;
	}

	@Override
	public User findUserByEmail(String email) {

		Session session = sessionFactory.getCurrentSession() ;
		
		Query<User> theQuery = session.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email", User.class);
		theQuery.setParameter("email", email);
		
		User theUser = theQuery.getSingleResult() ;
		
		session.clear();
		
		return theUser;
	}

	@Override
	public void add(User theUser) {

		Session session = sessionFactory.getCurrentSession() ;
		
		session.saveOrUpdate(theUser);
						
	}
		
	@Override
	public List<User> search(String scText) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    TypedQuery<Object[]> theQuery = session.createQuery(
	    			  "SELECT u.id,u.name FROM User u WHERE u.name LIKE :searchText", 
	    			  Object[].class);
	    theQuery.setParameter("searchText",scText + "%"+"%");

	    List<Object[]> results = theQuery.getResultList() ;
	    
	   List<User> scUsers = results.stream()
			   				.map(user -> {
			   					User theUser = new User() ;
			   					theUser.setId((int)user[0]);
			   					theUser.setName((String) user[1]);
			   					return theUser ;
			   				})
			   				.collect(Collectors.toList());
			   				
	    return scUsers;
	}

	@Override
	public void delete(int id) {
	    Session session = sessionFactory.getCurrentSession();

	    TypedQuery<?> theQuery = session.createQuery("DELETE FROM User WHERE id= :userId") ;
	    theQuery.setParameter("userId", id);
	    theQuery.executeUpdate() ;
	}	


	
}
