package com.xh.web.action;

import com.xh.web.common.BaseAction;

public class RoleAction extends BaseAction {
	// private RoleService rs = new RoleService();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询所有的角色列表
	 * 
	 * @return list
	 */
	public String findAll() {
		System.out.println("哥哥 过来看看..........");
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

}
