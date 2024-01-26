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
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.amazon.book_store.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonView({UserView.INFO.class})
	private int id ;
	
	@Column(name="role_name")
	@JsonView({UserView.INFO.class})
	@Pattern(regexp = "^ROLE_[a-zA-Z]+$" , message = "Role name must start with ROLE_")
	private String roleName ;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "roles",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User>users;
	
	public Role() {
	
	}
	
	public Role(int id , String roleName) {
		this.id = id ;
		this.roleName = roleName;
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
	
}
