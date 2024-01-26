package com.amazon.book_store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.amazon.book_store.view.OrderView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="orderDetails")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView({OrderView.PRIVATE.class})
	private int id ;
	
	@Column(name="book_id")
	@JsonView({OrderView.PRIVATE.class})
	@Min(value = 1 ,message =  "bookId field is required")
	private int BookId ;

	@Column(name="book_title")
	@JsonView({OrderView.PRIVATE.class})
	@NotNull(message =  "bookTitle field is required")
	private String BookTitle ; 

	@Column(name="quantity")
	@JsonView({OrderView.PRIVATE.class})
	@Min(value = 1 ,message =  "quantity field is required")
	private int quantity ;
	
	@Column(name="price")
	@JsonView({OrderView.PRIVATE.class})
	@Min(value = 0 ,message =  "price field is required")
	private double price ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getBookTitle() {
		return BookTitle;
	}

	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetails [BookId=" + BookId + ", BookTitle=" + BookTitle + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
	
}
