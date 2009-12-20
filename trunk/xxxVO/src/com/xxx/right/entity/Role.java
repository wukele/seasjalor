package com.xxx.right.entity;

import java.util.Date;
import java.util.List;

/**
 * 角色类VO
 * 
 * @author xiaohe
 * 
 */
public class Role {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 模块
	 */
	private List<Module> modules;
	/**
	 * 用户
	 */
	private List<User> users;

	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 创建时间
	 * 
	 */
	private Date createDate;
	/**
	 * 最后修改时间
	 */
	private Date update_Date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdate_Date() {
		return update_Date;
	}

	public void setUpdate_Date(Date updateDate) {
		update_Date = updateDate;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
