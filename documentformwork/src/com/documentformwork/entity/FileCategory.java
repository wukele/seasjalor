package com.documentformwork.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "document_category")
public class FileCategory implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "creator")
	private String creator;

	public Set<FileCategory> getFileCategorys() {
		return fileCategorys;
	}

	public void setFileCategorys(Set<FileCategory> fileCategorys) {
		this.fileCategorys = fileCategorys;
	}

	@Column(name = "upadator")
	private String updator;
	@Column(name = "create_time")
	private Date create_Date;
	@Column(name = "update_time")
	private Date updateDate;
	@Column(name = "parent_id")
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	private FileCategory parentId;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentId")
	private Set<FileCategory> fileCategorys=new HashSet<FileCategory>(0);

	public FileCategory getParentId() {
		return parentId;
	}

	public void setParentId(FileCategory parentId) {
		this.parentId = parentId;
	}

	@Column(name = "update_Date")
	private Date update_Date;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(Date createDate) {
		create_Date = createDate;
	}

	public Date getUpdate_Date() {
		return update_Date;
	}

	public void setUpdate_Date(Date updateDate) {
		update_Date = updateDate;
	}

}
