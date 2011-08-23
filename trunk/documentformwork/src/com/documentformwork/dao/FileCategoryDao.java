package com.documentformwork.dao;

import java.util.List;

import com.documentformwork.entity.FileCategory;

public interface FileCategoryDao extends BaseDao<FileCategory, Integer> {
	public List<FileCategory> getTopFileTreeNode();
	
	public List<FileCategory> getFileTreeNodeById(long id);

}
