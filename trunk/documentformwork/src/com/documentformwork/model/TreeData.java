package com.documentformwork.model;

public class TreeData {
	protected String id;
	protected String parentId;
	protected Object dataObject;
	protected String url;
	protected String text;
	protected String icon;
	protected String target;
	protected boolean expanded = false;
	protected boolean singleClickExpand = false;
	protected boolean checked = false;
	protected boolean leaf = false;
	protected String qtipCfg;

	public boolean isSingleClickExpand() {
		return this.singleClickExpand;
	}

	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public String getQtipCfg() {
		return this.qtipCfg;
	}

	public void setQtipCfg(String qtipCfg) {
		this.qtipCfg = qtipCfg;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Object getDataObject() {
		return this.dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	public boolean isExpanded() {
		return this.expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isLeaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

}
