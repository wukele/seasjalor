package com.documentformwork.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document implements Serializable {
	public Document() {

	}

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "document_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ISLEAF")
	private String isLeaf = "Y";

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "document_name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "update_user")
	private String updateMan;

	@Column(name = "update_time")
	private Date updateDate;

	@Column(name = "create_user")
	private String createUser;

	@Column(name = "size")
	private long size;

	@Column(name = "TAGS", nullable = true)
	private String tags;

	@Column(name = "LINK")
	private String link;

	@Column(name = "PARENT_ID")
	private String parentId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DELFLAG")
	private String delflag;

	@Column(name = "CREATE_TIME")
	private Date createeDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Date getCreateeDate() {
		return createeDate;
	}

	public void setCreateeDate(Date createeDate) {
		this.createeDate = createeDate;
	}

}
