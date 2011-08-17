package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class PrivilegeController extends BaseController {
	private String listView;

	public String getListView() {
		return listView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	/**
	 * 显示所有的权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView privilegeList(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView(this.listView);
	}

}
