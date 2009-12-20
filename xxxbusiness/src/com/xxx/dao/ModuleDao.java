package com.xxx.dao;

import java.util.List;

import com.xxx.right.entity.Module;

/**
 * 菜单 接口
 * 
 * @author xiaohe
 * 
 */
public interface ModuleDao {
	/**
	 * 获取模块列表
	 * 
	 * @return
	 */
	public List<Module> getModules();

}
