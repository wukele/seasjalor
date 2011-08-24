package com.documentformwork.dao.impl;

import java.util.List;

import com.documentformwork.dao.FileCategoryDao;
import com.documentformwork.entity.FileCategory;
import com.documentformwork.treedemo.model.TreeNode;
import com.documentformwork.util.TreeModuleFactory;

public class FileCategoryServiceImpl extends
		BaseDaoServiceImpl<FileCategory, Integer> implements FileCategoryDao {

	@Override
	public List<FileCategory> getTopFileTreeNode() {
		return this.findList("select f from FileCategory f  WHERE f.parentId IS NULL ", null, 0, 100);
	}

	@Override
	public List<FileCategory> getFileTreeNodeById(long id) {
		return this.findBySQL("select * from document_category where id=1");
	}

	public TreeNode getFileCategoryTreeNode() {
		TreeModuleFactory factory = TreeModuleFactory.getInstance();
		return factory.createFullFileTreeNode(this.getTopFileTreeNode());
	}

}
