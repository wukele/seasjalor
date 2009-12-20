package com.xh.dao;

import java.util.List;

import com.xh.entity.User;

public interface UserDao {
	/**
	 * 获取用户列表
	 * @param user 实体类
	 * @return  用户
	 */
	public User getUser(User user);

	public List<User> getList();

	public Integer save(User user);

	public Integer delete(Integer id);

}
