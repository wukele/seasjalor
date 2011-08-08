package com.documentformwork.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
	public User(long userId, String name, String password) {

		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	public User() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userId;
	private String name;
	private String password;

	@Id
	@Column(name = "id", unique = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
