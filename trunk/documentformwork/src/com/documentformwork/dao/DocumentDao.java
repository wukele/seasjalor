package com.documentformwork.dao;

import com.documentformwork.entity.Document;
import com.documentformwork.model.TreeNode;

public interface DocumentDao extends BaseDao<Document, Integer> {

//	public List<Document> getAllList();
//
	public String getGridJson2(String query);
	
	public TreeNode getTopFileTreeNode();
	
	public TreeNode getFileTreeNode();

}
