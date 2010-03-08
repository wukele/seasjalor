package com.xh.dao;

import java.util.List;

import com.xh.entity.User;

public interface UserDao {
	public User getUser(User user);

	public List<User> getList();

	public Integer save(User user);

	public Integer delete(Integer id);

	public Integer modify(Integer id);

}
