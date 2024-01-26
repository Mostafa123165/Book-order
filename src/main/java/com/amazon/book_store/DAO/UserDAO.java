package com.amazon.book_store.DAO;

import java.util.List;

import com.amazon.book_store.entity.User;

public interface UserDAO {

	public List<User> findAllUser();
	
	public User findUserByEmail(String email);
	
	public User findUserById(int id , Boolean initializeBook);
	
	public void add(User theUser);
	
	public List<User> search(String scText);
	
	public void delete(int id);
}
