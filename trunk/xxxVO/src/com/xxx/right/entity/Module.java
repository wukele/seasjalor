package com.xxx.right.entity;

import java.util.Date;

/**
 * 模块类VO
 * 
 * @author xiaohe
 * 
 */
public class Module {
	/**
	 * 父节点
	 */
	private Integer parentId;

	/**
	 * 模块 主键
	 */
	private Integer id;
	/**
	 * 模块名字
	 */
	private String moduleName;
	/**
	 * 创建时间
	 */
	private Date createName;
	/**
	 * 最后修改时间
	 */
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getCreateName() {
		return createName;
	}

	public void setCreateName(Date createName) {
		this.createName = createName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
