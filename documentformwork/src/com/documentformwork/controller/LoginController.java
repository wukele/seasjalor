package com.documentformwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

public class LoginController extends BaseController {

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {	
		return new ModelAndView("login");
	}

	public ModelAndView inlidate(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("redirect", "common.do?method=index");
		result.put("message", "登录成功");
		this.write(response, result.toString());
		return null;
	}

}
