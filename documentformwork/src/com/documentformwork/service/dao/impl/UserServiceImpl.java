package com.documentformwork.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.documentformwork.dao.UserDao;
import com.documentformwork.entity.User;
import com.documentformwork.service.dao.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

}
