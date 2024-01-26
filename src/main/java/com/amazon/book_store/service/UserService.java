package com.amazon.book_store.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.amazon.book_store.entity.User;

public interface UserService extends UserDetailsService{
		
	public List<User> findAllUser();
	
	public User findUserByEmail(String email);  // For login
	
	public User findUserById(int id ,Boolean initializeBook);
	
	public List<User> search(String scText); 
	
	public void add(User theUser) ;
	
	public void delete(int id) ;
	
}
