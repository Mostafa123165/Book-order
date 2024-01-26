package com.amazon.book_store.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.book_store.DAO.UserDAO;
import com.amazon.book_store.entity.Role;
import com.amazon.book_store.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO ;
	
	@Override
	@Transactional
	public List<User> findAllUser() {
		return userDAO.findAllUser();
	}

	@Override
	@Transactional
	public User findUserById(int id,Boolean initializeBook) {
		return userDAO.findUserById(id , initializeBook);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) {
		
		
		User theUser = findUserByEmail(email) ;
				
		if(theUser == null) {
			throw new RuntimeException("userName/password not found");
		}
		
		List<Role> roles = null ;
		
		roles = theUser.getRoles();
		
		
		return  new org.springframework.security.core.userdetails.User(
						theUser.getEmail() , 
						theUser.getPassword() ,
						ConvertRoleToAuthority(roles));
	}

	Collection<? extends GrantedAuthority> ConvertRoleToAuthority(Collection<Role> roles) {
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		
	}
	
	@Override
	@Transactional
	public User findUserByEmail(String email) {
		
		return userDAO.findUserByEmail(email);
	}

	@Override
	@Transactional
	public List<User> search(String scText) {
		return userDAO.search(scText);
	}

	@Override
	@Transactional
	public void add(User theUser) {
		userDAO.add(theUser);
	}

	@Override
	@Transactional
	public void delete(int id) {
		userDAO.delete(id);
	}
	
	
}
