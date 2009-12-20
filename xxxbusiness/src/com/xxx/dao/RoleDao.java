package com.xxx.dao;

import java.util.List;

import com.xxx.right.entity.Role;

/**
 * 角色接口类
 * 
 * @author xiaohe
 * 
 */
public interface RoleDao {
	/**
	 * 获取角色 列表
	 * 
	 * @return
	 */
	public List<Role> getRoles();

}
