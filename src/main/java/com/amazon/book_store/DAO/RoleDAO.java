package com.amazon.book_store.DAO;

import java.util.List;

import com.amazon.book_store.entity.Role;


public interface RoleDAO {

	public List<Role> findAllRoles();
	
	public Role findByName(String name) ;
	
	public Role findById(int id);
	
	public void Save(Role theRole);
	
	public void delete(int id);
}
