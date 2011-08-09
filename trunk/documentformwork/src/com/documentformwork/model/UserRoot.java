package com.documentformwork.model;

import java.util.LinkedList;
import java.util.List;

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

}
