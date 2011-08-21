package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.documentformwork.dao.DocumentDao;

public class DoucmentMangeController extends BaseController {

	private DocumentDao service;

	private String indexView;

	public void setService(DocumentDao service) {
		this.service = service;
	}

	public String getIndexView() {
		return indexView;
	}

	public void setIndexView(String indexView) {
		this.indexView = indexView;
	}

	private String addView;

	public String getAddView() {
		return addView;
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public ModelAndView addDocument(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(this.addView);
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(this.indexView);
	}

	/**
	 * 查询 文档列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getDocumentList(HttpServletRequest request,
			HttpServletResponse response) {
		this.write(response, service.getGridJson2("select d from Document d"));
		return null;
	}
}
