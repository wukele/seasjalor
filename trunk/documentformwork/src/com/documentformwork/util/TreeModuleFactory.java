package com.documentformwork.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.documentformwork.entity.FileCategory;
import com.documentformwork.treedemo.model.TreeNode;
import com.vsg.framework.util.exception.RunException;

/**
 * 树型结构工厂
 * 
 * @author style
 * 
 */
public class TreeModuleFactory {
	private static TreeModuleFactory factory = null;

	public static TreeModuleFactory getInstance() {
		if (factory == null) {
			factory = new TreeModuleFactory();
		}
		return factory;

	}

	/**
	 * 创建一个树的最上层
	 * 
	 * @param rootName
	 * @return
	 */
	private TreeNode createRootTreeNode(String rootName) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId("root");
		treeNode.setText(rootName);
		treeNode.setExpanded(true);
		return treeNode;
	}

	/**
	 * 构建完整文件类别树
	 * 
	 * @param fileCategory
	 * @return
	 */
	public TreeNode createFullFileTreeNode(List<FileCategory> fileCategorys) {
		TreeNode root = createRootTreeNode("所有文档");
		for (FileCategory f : fileCategorys) {
			createTreeNode(root, f);
		}
		return root;

	}

	/**
	 * 递归树的类别
	 * 
	 * @param parent
	 * @param fileCategory
	 */
	private void createTreeNode(TreeNode parent, FileCategory fileCategory) {
		// 不允许ID重复
		if (parent.getId().equals(fileCategory.getId())) {
			throw new RunException("父节点和子节点重复");
		}
		TreeNode node = createSingleTreeNode(fileCategory);

		node.setParent(parent);

		if (parent.getChildNodes() == null) {
			parent.setChildNodes(new ArrayList<TreeNode>());
		}
		parent.getChildNodes().add(node);
		Set<FileCategory> subFileCategory = fileCategory.getFileCategorys();

		if (null != subFileCategory) {
			for (FileCategory subFile : subFileCategory) {
				createTreeNode(node, subFile);
			}
		}

	}

	private TreeNode createSingleTreeNode(FileCategory fileCategory) {
		TreeNode node = new TreeNode();
		node.setId(fileCategory.getId());
		node.setText(fileCategory.getName());
		return node;
	}

}
