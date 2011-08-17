package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class DoucmentMangeController extends BaseController {
	private String indexView;

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
}
