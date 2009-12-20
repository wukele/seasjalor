package com.xxx.right.entity;

/**
 * 用户类 VO
 * 
 * @author xiaohe
 * 
 */
public class User {
	private Integer id;
	private String name;
	private String address;
	private String email;
	private String password;
	private String loginName;
	private Integer role = 1;
	private String agePassword;
	private String tempRole;

	public String getTempRole() {
		return tempRole;
	}

	public void setTempRole(String tempRole) {
		this.tempRole = tempRole;
	}

	public String getAgePassword() {
		return agePassword;
	}

	public void setAgePassword(String agePassword) {
		this.agePassword = agePassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}
