package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.dao.FileCategoryDao;

public class DoucmentMangeController extends BaseController {

	public FileCategoryDao getFileCategoryService() {
		return fileCategoryService;
	}

	public void setFileCategoryService(FileCategoryDao fileCategoryService) {
		this.fileCategoryService = fileCategoryService;
	}

	private DocumentDao service;

	private FileCategoryDao fileCategoryService;

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

	public ModelAndView getFileCategoryTreeNode(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(fileCategoryService);
		this.write(response, fileCategoryService.getFileCategoryTreeNode()
				.toJSONObject().toString());
		return null;
	}
}
