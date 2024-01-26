package com.amazon.book_store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.book_store.DAO.OrderDAO;
import com.amazon.book_store.entity.Order;

@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderDAO orderDAO ;
	
	@Override
	@Transactional
	public List<Order> getCartForUser(int userId) {
		return orderDAO.getCartForUser(userId);
	}

	@Override
	@Transactional
	public Order getDetailsOfOrder(int orderId) {
		return orderDAO.getDetailsOfOrder(orderId);
	}

	@Override
	@Transactional
	public void save(Order theOrder) {	
		 orderDAO.save(theOrder);
	}

}
