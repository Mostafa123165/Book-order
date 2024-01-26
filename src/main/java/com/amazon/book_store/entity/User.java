package com.amazon.book_store.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.amazon.book_store.view.SharedUserBublicAndBookBublic;
import com.amazon.book_store.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView({SharedUserBublicAndBookBublic.class})
	private int id ;
	
	@Column(name="name")
	@JsonView({SharedUserBublicAndBookBublic.class})
	private String name;
	
	@Column(name="first_name")
	@NotNull(message="The firstName is required.")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="The lastName is required.")
	private String lastName;
	
	@Column(name="email")
	@JsonView({UserView.Internal.class})
	@Email(message = "The email is not a valid email.")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	@JsonView({UserView.Internal.class})
	@Pattern(regexp = "^\\+2[0-9]{11}$" ,
			 message = "The phone must start with +2 and include 11 digits [0-9] (total 13 digits)" )
	private String phone ;

	@Column(name="gendr")
	@JsonView({UserView.Internal.class})
	@Pattern(regexp = "^(MALE|FEMAL)$",
			 message = "The gendr must hava value either MALE or FEMALE.")
	private String gendr;

	@ManyToMany(fetch=FetchType.LAZY )
	@JoinTable(
			name = "roles",
			joinColumns = @JoinColumn(name="user_id") ,
			inverseJoinColumns = @JoinColumn(name="role_id"))
	@JsonView({UserView.INFO.class})
	@Size(min = 1, message = "New user must have roles.")
	private List<Role> roles;
		
	@OneToMany(fetch = FetchType.LAZY , 
			   mappedBy = "user")
	@JsonView({UserView.INFO.class,})
	private List<Book> books ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGendr() {
		return gendr;
	}

	public void setGendr(String gendr) {
		this.gendr = gendr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", gendr=" + gendr + "]";
	}


}


