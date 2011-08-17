package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.documentformwork.service.SystemService;
import com.documentformwork.system.SystemConfig;

public class GeneralController extends BaseController {
	private SystemService service;
	private String indexView;

	public final void setService(SystemService service) {
		this.service = service;
	}

	public String getIndexView() {
		return indexView;
	}

	public void setIndexView(String indexView) {
		this.indexView = indexView;
	}

	/**
	 * Login success show index
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(this.indexView);
	}

	public ModelAndView getModuleByRoot(HttpServletRequest request,
			HttpServletResponse response) {
		// System.out.println(service);
		this.write(response, SystemConfig.getUserRoot().createModuleTree()
				.toJSonString());
		return null;

	}

}
