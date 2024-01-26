package com.amazon.book_store.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.amazon.book_store.entity.Role;
import com.amazon.book_store.entity.User;
import com.amazon.book_store.service.RoleService;
import com.amazon.book_store.service.UserService;
import com.amazon.book_store.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
public class UserController {

	@Autowired
	private  UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@JsonView(UserView.Internal.class)
	@GetMapping("/users")
	public List<User> getUsers() {
		
		List<User> users = userService.findAllUser() ;
			
		return users ;
	}
	
	@JsonView(UserView.INFO.class)
	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable int id) {
		
		User theUser = userService.findUserById(id,true) ;	
		
		return theUser;
	}
	
	
	@JsonView(UserView.PUBLIC.class)
	@GetMapping("/users/search/{scText}")
	public List<User> search(@PathVariable String scText) {

		return userService.search(scText) ;
	}
	
	@PostMapping("/users")
	public User add(@Valid @RequestBody User theUser) {

		return saveOrUpdate(theUser) ;
	}
	
	
	@PutMapping("/users")
	public User update(@Valid @RequestBody User theUser) {
		
		userService.findUserById(theUser.getId(),true) ;
		
		return saveOrUpdate(theUser) ;
	}
 	
	public User saveOrUpdate(User theUser) {
		
		// get roles from dataBase.
		// case1(save)  : then connect role with user then save user.
		// case2(delete): update user , delete old roles ,then add new roles 
		
		theUser.setName(theUser.getFirstName() + theUser.getLastName()) ;
		
		List<Role> myRoles = new ArrayList<Role>();
		for(Role role : theUser.getRoles()) {
			Role theRole = roleService.findByName(role.getRoleName());
			myRoles.add(theRole);			
		}
		theUser.setRoles(myRoles); 
		
		userService.add(theUser) ;
		
		return theUser ;
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, String> delete(@PathVariable int id) {
		
		// if user not found , service will throw notFound Exception.
		userService.findUserById(id,true) ;
		
		userService.delete(id) ;
		
		Map<String, String> resp = new HashMap<>() ;
		
		resp.put("message", "Deleted user successfully - " + id );
		
		return resp;
	}

	
}














