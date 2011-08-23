package com.documentformwork.treedemo.model;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class TreeNode {

	protected String id;
	protected TreeNode parent;
	protected Object dataObject;
	protected String url;
	protected String text;
	protected String icon;
	protected String target;
	protected boolean expanded = false;
	protected boolean singleClickExpand = false;
	protected boolean checked = false;
	//protected boolean leaf = false;
	protected String qtipCfg;
	protected List<TreeNode> childNodes;
	
	public boolean hasChildNodes(){
		return childNodes!=null&&childNodes.size()>0;
	}
	
	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Object getDataObject() {
		return dataObject;
	}
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isSingleClickExpand() {
		return singleClickExpand;
	}
	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isLeaf() {
		return !hasChildNodes();
	}
	public String getQtipCfg() {
		return qtipCfg;
	}
	public void setQtipCfg(String qtipCfg) {
		this.qtipCfg = qtipCfg;
	}
	public List<TreeNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	/**
	 * 将树节点对象转化为JSONObject对象
	 * @return
	 */
	public JSONObject toJSONObject() {
		JSONObject jobj = new JSONObject();
		jobj.put("id", this.id);
		jobj.put("text", this.text);
		if (!(StringUtils.isEmpty(this.url)))
			jobj.put("url", this.url);
		if (this.isLeaf())
			jobj.put("leaf", Boolean.valueOf(this.isLeaf()));
		if (!(StringUtils.isEmpty(this.icon)))
			jobj.put("icon", this.icon);
		if (!(StringUtils.isEmpty(this.qtipCfg)))
			jobj.put("qtipCfg", this.qtipCfg);
		if (this.expanded)
			jobj.put("expanded", Boolean.valueOf(this.expanded));
		if (this.singleClickExpand)
			jobj.put("singleClickExpand", Boolean
					.valueOf(this.singleClickExpand));
		if(!"root".equals(this.id))jobj.put("checked", Boolean.valueOf(this.checked));
		if(this.url!=null&&this.url.length()>0)jobj.put("url", this.url);
		if(this.icon!=null&&this.icon.length()>0)jobj.put("icon", this.icon);
		
		if (this.hasChildNodes()) {
			JSONArray array = new JSONArray();
			for (TreeNode node : this.childNodes) {
				array.add(node.toJSONObject());
			}
			jobj.put("children", array);
		}
		return jobj;
	}
}
