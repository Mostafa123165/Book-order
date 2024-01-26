package com.amazon.book_store.DAO;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory factory; 
	
	
	@Override
	public List<Role> findAllRoles() {
		
		Session session = factory.getCurrentSession() ;
		
		Query<Role> query = session.createQuery("FROM Role" , Role.class) ;

		return query.getResultList();
	}
	
	@Override
	public Role findByName(String name) {
		
		Session session = factory.getCurrentSession() ;
	
		Query<Role> query = session.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName" ,
												Role.class) ;
		query.setParameter("roleName", name) ;
		
		Role theRole = query.uniqueResult() ;
		
		if(theRole == null) {
			throw new NotFoundExc("Not found role name - " + name);
		}
		
		
		return theRole;
	}

	@Override
	public Role findById(int id) {
		
		Session session = factory.getCurrentSession() ;
		
		Query<Role> query = session.createQuery("SELECT r FROM Role r WHERE r.id = :roleId" ,
												Role.class) ;
		query.setParameter("roleId",id) ;

		Role theRole = query.uniqueResult();
		
		if(theRole == null) {
			throw new NotFoundExc("Not found role id - " + id);
		}
		
		return theRole;
	}

	
	@Override
	public void Save(Role theRole) {
		Session session = factory.getCurrentSession(); 
		
		session.saveOrUpdate(theRole) ;
	}

	@Override
	public void delete(int id) {
		Session session = factory.getCurrentSession(); 
		
		TypedQuery<?> query = session.createQuery("DELETE FROM Role WHERE id = :roleId") ;
		query.setParameter("roleId", id); 
		query.executeUpdate();
	}


}
