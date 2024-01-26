package com.amazon.book_store.DAO;

import java.util.List;

import com.amazon.book_store.entity.Order;

public interface OrderDAO {

	public List<Order> getCartForUser(int userId);

	public Order getDetailsOfOrder(int orderId) ;

	public void save(Order theOrder);
}
