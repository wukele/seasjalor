package com.xh.dao;

import java.util.List;

import com.xh.entity.Role;

public interface RoleDao {
	public List<Role> findAll();

	public List<Role> findByRoleName();

	public Integer sava(Role role);

	public Integer modify(Integer id);

	public Integer delete(Integer id);

}
