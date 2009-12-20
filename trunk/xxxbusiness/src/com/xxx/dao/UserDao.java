package com.xxx.dao;

import java.util.List;

import com.xh.entity.User;

public interface UserDao {
	/**
	 * 获取用户
	 * 
	 * @param user
	 *            实体类
	 * @return 用户
	 */
	public User getUser(User user);

	/**
	 * 获取用户列表
	 * 
	 * @return
	 */

	public List<User> getList();

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */

	public Integer save(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */

	public Integer delete(Integer id);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */

	public Integer modify(User user);

}
