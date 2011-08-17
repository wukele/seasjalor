package com.documentformwork.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;



public class UserRoot {

	private String name;
	private String password;

	private List<Module> modules;

	public List<Module> getModules() {
		return modules;
	}

	public void setModule(List<Module> modules) {
		this.modules = modules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addModule(Module module) {
		if (this.modules == null) {
			this.modules = new LinkedList<Module>();
		}
		this.modules.add(module);
	}

	/**
	 * 创建系统管理模块树
	 * @return
	 */
	public TreeNode createModuleTree() {
		if (this.modules != null) {
			List<TreeData> list = new LinkedList<TreeData>();
			TreeData root = new TreeData();
			root.setId("root");
			root.setText("管理功能");
			root.setParentId("--");
			list.add(root);
			Iterator<Module> localIterator = this.modules.iterator();
			while (localIterator.hasNext()) {
				Module m = localIterator.next();
				TreeData data = new TreeData();
				data.setIcon(m.getIcon());
				data.setText(m.getName());
				data.setUrl(m.getUrl());
				data.setParentId("root");
				data.setId(UUID.randomUUID().toString());
				System.out.println(UUID.randomUUID().toString());
				list.add(data);
				if (!(localIterator.hasNext())) {
					return new TreeModel(list).mergeTree("root");
				}
			}
		}
		return null;
	}

}
