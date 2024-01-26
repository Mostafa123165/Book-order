package com.amazon.book_store.service;

import java.util.List;

import com.amazon.book_store.entity.Role;

public interface RoleService {

	public List<Role> findAllRoles() ;
	
	public Role findByName(String name) ;
	
	public Role findById(int id) ;
	
	public void save(Role theRole);

	public void delete(int id);
}
