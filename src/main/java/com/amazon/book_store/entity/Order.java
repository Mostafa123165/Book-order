package com.amazon.book_store.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.amazon.book_store.view.OrderView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView({OrderView.PUBLIC.class})
	private int id ;
	
	@Column(name="user_id")
	@JsonView({OrderView.PUBLIC.class})
	@Min(value = 1 ,message = "userId field is required")
	private int userId ;
	
	@Column(name="state")
	@JsonView({OrderView.PUBLIC.class})
	@NotNull(message = "state field is required")
	private String state ;
	
	@Column(name="create_at")
	@JsonView({OrderView.PUBLIC.class})
	private String createAt ;
	
	@Column(name="phone")
	@JsonView({OrderView.PUBLIC.class})
	@NotNull(message = "phone field is required")
	private String phone ;
	
	@Column(name="country")
	@JsonView({OrderView.PUBLIC.class})
	@NotNull(message = "country field is required")
	private String country;
	
	@Column(name="city")
	@JsonView({OrderView.PUBLIC.class})
	@NotNull(message = "city field is required")
	private String city;
	
	@Column(name="street")
	@JsonView({OrderView.PUBLIC.class})
	@NotNull(message = "street field is required")
	private String street ;

	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@JsonView({OrderView.PRIVATE.class})
	@Size(min = 1,message = "orderDetails field is required")
	private List<OrderDetails> orderDetails ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
