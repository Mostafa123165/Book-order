package com.amazon.book_store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.amazon.book_store.view.BookView;
import com.amazon.book_store.view.SharedUserBublicAndBookBublic;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView({SharedUserBublicAndBookBublic.class})
	private int id ;
	
	@Column(name="title")
	@JsonView({SharedUserBublicAndBookBublic.class})
	@NotNull(message = "The title is required")
	private String title ;
	
	@Column(name="price")
	@JsonView({BookView.Internal.class})
	@DecimalMin(value = "0.1" , message = "Price of book must greater than 0 bound")
	private double price ;
	
	@Column(name="description")
	@JsonView({BookView.BookINFO.class})
	private String description ;
	
	
	@Column(name="author_name")
	@JsonView({BookView.BookINFO.class})
	private String authorName;

	@Column(name="publisher_date")
	@JsonView({BookView.Internal.class})
	private String publisherDate ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="publisher_id")
	@JsonView({BookView.BookINFO.class})
	private User user ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisherDate() {
		return publisherDate;
	}

	public void setPublisherDate(String publisherDate) {
		this.publisherDate = publisherDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", authorName=" + authorName + ", publisherDate=" + publisherDate + ", user=" + user + "]";
	}
	

	
}









