package com.documentformwork.service;

import java.util.List;

import com.documentformwork.entity.User;

public interface UserService {
	public void save(User user);

	public void delete(long id);

	public List<User> findList();

}
