package com.documentformwork.model;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class TreeNode extends TreeData {
	private boolean hasChild = false;
	private List<TreeNode> items;

	/**
	 * 判断是否包含子节点
	 * 
	 * @return
	 */
	public boolean hasChild() {
		return this.hasChild;
	}

	/**
	 * 添加节点
	 * 
	 * @param item
	 */
	public void addItem(TreeNode item) {
		if (this.leaf) {
			throw new RuntimeException("叶子节点不能增加子节点!");
		}

		if (this.items == null)
			this.items = new LinkedList();
		this.items.add(item);
		this.hasChild = true;
	}

	/**
	 * 取得子节点集合
	 * 
	 * @return
	 */
	public List<TreeNode> getItems() {
		if (this.leaf) {
			throw new RuntimeException("叶子节点没有子节点集合!");
		}

		return this.items;
	}

	/**
	 * 覆盖Object的toString()方法
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (this.leaf) {
			buffer.append("nodeId:").append(this.id).append(" parendId:")
					.append(this.parentId)
					.append(" isLeaf:true[nosubnode]\r\n");
		} else {
			buffer.append("nodeId:").append(this.id).append(" parendId:")
					.append(this.parentId).append(
							" isLeaf:false[hassubnode] \r\n");
			if (this.hasChild) {
				for (TreeNode node : this.items) {
					buffer.append(node.toString());
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 将树节点对象转化为JSONObject对象
	 * 
	 * @return
	 */
	public JSONObject toJSONObject() {
		JSONObject jobj = new JSONObject();
		jobj.put("id", this.id);
		jobj.put("text", this.text);
		if (!(StringUtils.isEmpty(this.url)))
			jobj.put("url", this.url);
		if (this.leaf)
			jobj.put("leaf", Boolean.valueOf(this.leaf));
		if (!(StringUtils.isEmpty(this.icon)))
			jobj.put("icon", this.icon);
		if (!(StringUtils.isEmpty(this.qtipCfg)))
			jobj.put("qtipCfg", this.qtipCfg);
		if (this.expanded)
			jobj.put("expanded", Boolean.valueOf(this.expanded));
		if (this.singleClickExpand)
			jobj.put("singleClickExpand", Boolean
					.valueOf(this.singleClickExpand));
		if (this.checked)
			jobj.put("checked", Boolean.valueOf(this.checked));
		if (this.hasChild) {
			JSONArray array = new JSONArray();
			for (TreeNode node : this.items) {
				array.add(node.toJSONObject());
			}
			jobj.put("children", array);
		}
		return jobj;
	}

	/**
	 * 将树节点数据用json格式的字符串表示
	 * 
	 * @return
	 */
	public String toJSonString() {
		return toJSONObject().toString();
	}
}
