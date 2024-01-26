package com.amazon.book_store.DAO;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Order> getCartForUser(int userId) {
		
		Session session = sessionFactory.getCurrentSession() ;
		
		Query<Order> theQuery = session.createQuery("SELECT i FROM Order i WHERE i.userId =: userId",
														Order.class);
		theQuery.setParameter("userId", userId);
		
		List<Order> orders = theQuery.getResultList() ;
				
		return orders;
	}

	@Override
	public Order getDetailsOfOrder(int orderId) {
		
		Session session = sessionFactory.getCurrentSession() ;

		Query<Order> theQuery = session.createQuery("SELECT o FROM Order o WHERE o.id =:orderId ",
														Order.class) ;
		theQuery.setParameter("orderId", orderId) ;
		
		Order theOrder = theQuery.uniqueResult() ;
		
		if(theOrder == null) {
			throw new NotFoundExc("Not found order id - " + orderId);
		}
		
		Hibernate.initialize(theOrder.getOrderDetails());
		
		return theOrder;
	}

	@Override
	public void save(Order theOrder) {
		
		Session session = sessionFactory.getCurrentSession() ;
						
		session.save(theOrder);
		
	}
	
	

}
