package com.amazon.book_store.controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.entity.Order;
import com.amazon.book_store.entity.User;
import com.amazon.book_store.service.OrderService;
import com.amazon.book_store.service.UserService;
import com.amazon.book_store.view.OrderView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderSerivce ;
	
	@Autowired
	private UserService userService ;
	
	@GetMapping("/orders/{user_id}")
	@JsonView({OrderView.PUBLIC.class})
	public List<Order> getCartForUser(@PathVariable int user_id){
		
		List<Order> orders = orderSerivce.getCartForUser(user_id);
		
		return orders ;
	}  
	
	@GetMapping("/orders/details")
	@JsonView({OrderView.PRIVATE.class})
	public Order getDetailsOfOrder(
			@RequestParam(name="orderId") int orderId) {
		
		Order theOrder = orderSerivce.getDetailsOfOrder(orderId) ;
		
		return theOrder ;
	}
	
	@PostMapping("/orders")
	public LinkedHashMap<String,Object> CreateOrder(
			@Valid @RequestBody Order theOrder) {

		findUser(theOrder.getUserId());
		
		theOrder.setCreateAt(LocalDate.now().toString());
		
		LinkedHashMap<String, Object> resp = new LinkedHashMap<>() ;
		
		orderSerivce.save(theOrder) ;
				
		if(theOrder.getId() == 0) {
			resp.put("error",HttpStatus.BAD_REQUEST.value());
			resp.put("error", "Found problem ,please try later.");
		}
		else {
			resp.put("status", HttpStatus.OK.value());
			resp.put("message", "Order Add Successfully - " + theOrder.getId());
		}
		
		return resp ;
	}  
	
	public void findUser(int userId) {
		User theUser =  userService.findUserById(userId,false); 
	
		if(theUser == null) {
			throw new NotFoundExc("Not found user id - " + userId);
		}
		
	}
	
}
