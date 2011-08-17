package com.documentformwork.entity;

import java.io.Serializable;
import java.util.Date;

public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 权限ID
	 */

	private long privilegeId;

	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 所属组织
	 */
	private String organizer;

	private String updateMan;
	/**
	 * 权限代码
	 */
	private String prililegeCode;

	public long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public String getPrililegeCode() {
		return prililegeCode;
	}

	public void setPrililegeCode(String prililegeCode) {
		this.prililegeCode = prililegeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 说明
	 */
	private String remark;
	/**
	 * 权限名字
	 */
	private String privilegeName;

	/**
	 * 所属对象
	 */
	private String className;
}
