package com.documentformwork.dao.impl;

import com.documentformwork.dao.UserDao;
import com.documentformwork.model.User;

public class UserDaoImpl implements UserDao {
	@Override
	public boolean login(User user) {
		if (user.getName().equals("admin") && user.getPassword().equals("123")) {
			return true;
		} else {
			return false;
		}
	}

}
