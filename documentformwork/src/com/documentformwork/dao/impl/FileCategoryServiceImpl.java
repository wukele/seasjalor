package com.documentformwork.dao.impl;

import java.util.List;

import com.documentformwork.dao.FileCategoryDao;
import com.documentformwork.entity.FileCategory;

public class FileCategoryServiceImpl extends
		BaseDaoServiceImpl<FileCategory, Integer> implements FileCategoryDao {

	@Override
	public List<FileCategory> getTopFileTreeNode() {
		return this
			.findList("select f from FileCategory f ", null, 0,100);
	}

	@Override
	public List<FileCategory> getFileTreeNodeById(long id) {
		return this
		.findBySQL("select * from document_category where id=1");
	}

}
