package com.documentformwork.controller;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public abstract class BaseController extends AbstractController {
	/**
	 * 默认视图
	 */
	private String defaultView;

	public String getDefaultView() {
		return defaultView;
	}

	public void setDefaultView(String defaultView) {
		this.defaultView = defaultView;
	}

	@Override
	protected final ModelAndView handleRequestInternal(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cmd = this.getCmdName(request);
		ModelAndView mav = null;
		try {

			Method m = super.getClass().getMethod(
					cmd,
					new Class[] { HttpServletRequest.class,
							HttpServletResponse.class });
			mav = (ModelAndView) m.invoke(this, new Object[] { request,
					response });

		} catch (NoSuchMethodException ex) {
			System.out.println("没有找到" + super.getClass() + "方法" + cmd + "失败:"
					+ ex.getMessage());

		}

		return mav;
	}

	private String getCmdName(HttpServletRequest request) {
		String cmd = request.getParameter("method");

		Enumeration enumaration = request.getParameterNames();

		while (enumaration.hasMoreElements()) {
			String name = (String) enumaration.nextElement();
			if (name.indexOf("method") != 0) {
				continue;
			}
			int len = "method".length();
			if (name.length() == len) {
				return request.getParameter(name);

			}
			return name.substring(len);

		}

		return null;
	}

}
