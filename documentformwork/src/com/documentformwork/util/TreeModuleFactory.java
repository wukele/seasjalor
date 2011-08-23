package com.documentformwork.util;

import com.documentformwork.model.TreeNode;

/**
 * 树型结构工厂
 * 
 * @author style
 * 
 */
public class TreeModuleFactory {
	/**
	 * 创建一个树的最上层
	 * 
	 * @param rootName
	 * @return
	 */
	public TreeNode createRootTreeNode(String rootName) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId("root");
		treeNode.setText(rootName);
		treeNode.setExpanded(true);
		return treeNode;
	}

}
