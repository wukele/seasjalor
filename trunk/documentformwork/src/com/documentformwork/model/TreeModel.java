package com.documentformwork.model;

import java.util.LinkedList;
import java.util.List;

public class TreeModel {
	private List<TreeData> branchs;
	private List<TreeData> leafs;
	private boolean hasLeaf = false;

	public TreeModel(List<TreeData> treeDataList) {
		this.branchs = treeDataList;
	}

	public TreeModel(List<TreeData> branchs, List<TreeData> leafs) {
		this.branchs = branchs;
		this.leafs = leafs;
		if (leafs != null)
			this.hasLeaf = true;
	}

	/**
	 * 取得指定节点所包含的分支
	 * @param parentId
	 * @return
	 */
	private List<TreeData> getBranchsByParentId(String parentId) {
		List items = new LinkedList();
		for (TreeData branch : this.branchs) {
			if (branch.getId().equals(branch.getParentId())) {
				throw new RuntimeException("当前节点的父级节点指向了当前节点本身！Id:"
						+ branch.getId());
			}
			if (!(parentId.equals(branch.getParentId())))
				continue;
			items.add(branch);
		}
		return items;
	}

	/**
	 * 取得分支节点的叶子节点
	 * @param branchId
	 * @return
	 */
	private List<TreeData> getLeafsByBranchId(String branchId) {
		List items = new LinkedList();
		if (this.leafs != null) {
			for (TreeData leaf : this.leafs) {
				if (!(leaf.getParentId().endsWith(branchId)))
					continue;
				items.add(leaf);
			}
		}
		return items;
	}

	/**
	 * 合并分枝节点中id与当前节点相同的节点
	 * @param rootId
	 * @return
	 */
	public TreeNode mergeTree(String rootId) {
		TreeNode root = new TreeNode();
		for (TreeData branch : this.branchs) {
			if (!(rootId.equals(branch.getId())))
				continue;
			root.setDataObject(branch.getDataObject());
			copy(branch, root);
			merge(root);
			break;
		}
		return root;
	}

	private void copy(TreeData s, TreeData t) {
		t.checked = s.checked;
		t.dataObject = s.dataObject;
		t.expanded = s.expanded;
		t.icon = s.icon;
		t.id = s.id;
		t.leaf = s.leaf;
		t.parentId = s.parentId;
		t.qtipCfg = s.qtipCfg;
		t.singleClickExpand = s.singleClickExpand;
		t.target = s.target;
		t.text = s.text;
		t.url = s.url;
	}

	private void merge(TreeNode node) {
		boolean flag = false;
		TreeNode item;
		if (this.hasLeaf) {
			List<TreeData> leafItems = getLeafsByBranchId(node.getId());
			if (leafItems.size() > 0)
				flag = true;
			for (TreeData leaf : leafItems) {
				item = new TreeNode();
				item.setDataObject(leaf.getDataObject());
				copy(leaf, item);
				item.setLeaf(true);
				node.addItem(item);
			}
		}
		List<TreeData> branchItems = getBranchsByParentId(node.getId());
		if ((!(flag)) && (branchItems.size() == 0)) {
			node.setLeaf(true);
		}
		for (TreeData branch : branchItems) {
			item = new TreeNode();
			item.setDataObject(branch.getDataObject());
			copy(branch, item);
			node.addItem(item);
			merge(item);
		}
	}

	public TreeNode mergeAndClearTree(String rootId) {
		TreeNode root = new TreeNode();
		for (TreeData branch : this.branchs) {
			if (!(rootId.equals(branch.getId())))
				continue;
			root.setDataObject(branch.getDataObject());
			copy(branch, root);
			if (genAndClear(root))
				break;
			return null;
		}

		return root;
	}

	private boolean genAndClear(TreeNode node) {
		boolean flag = false;

		List<TreeData> leafItems = getLeafsByBranchId(node.getId());
		if (leafItems.size() > 0) {
			flag = true;
		}
		TreeNode item;
		for (TreeData leaf : leafItems) {
			item = new TreeNode();
			item.setDataObject(leaf.getDataObject());
			copy(leaf, item);
			item.setLeaf(true);
			node.addItem(item);
		}
		List<TreeData> branchItems = getBranchsByParentId(node.getId());
		for (TreeData branch : branchItems) {
			item = new TreeNode();
			item.setDataObject(branch.getDataObject());
			copy(branch, item);
			boolean genFlag = genAndClear(item);
			if ((!(flag)) && (genFlag)) {
				flag = genFlag;
			}
			if (genFlag)
				node.addItem(item);
		}
		return flag;
	}
}
