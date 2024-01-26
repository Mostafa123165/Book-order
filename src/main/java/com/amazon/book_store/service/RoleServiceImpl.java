package com.amazon.book_store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.book_store.DAO.RoleDAO;
import com.amazon.book_store.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDAO roleDao ;

	@Override
	@Transactional
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

	@Override
	@Transactional
	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

	@Override
	@Transactional
	public Role findById(int id) {
		return roleDao.findById(id);
	}
		
	@Override
	@Transactional
	public void save(Role theRole) {
		roleDao.Save(theRole);
	}

	@Override
	@Transactional
	public void delete(int id) {
		roleDao.delete(id);
	}


}
