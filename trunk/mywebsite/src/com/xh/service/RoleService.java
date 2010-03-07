package com.xh.service;

import java.util.List;

import com.xh.dao.RoleDao;
import com.xh.dao.impl.RoleDaoImpl;
import com.xh.entity.Role;

public class RoleService {
	private RoleDao rd = new RoleDaoImpl();

	public List<Role> findAll() {
		return rd.findAll();
	}

}
