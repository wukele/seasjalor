package com.xh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xh.dao.RoleDao;
import com.xh.entity.Role;

public class RoleDaoImpl implements RoleDao {

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		Role r = new Role();
		r.setId(1);
		r.setRoleName("管理员");
		List<Role> roles = new ArrayList<Role>();
		roles.add(r);
		return roles;
	}

	@Override
	public List<Role> findByRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modify(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer sava(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
