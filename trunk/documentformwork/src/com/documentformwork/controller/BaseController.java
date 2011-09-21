package com.documentformwork.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.documentformwork.util.FormworkUtil;

public abstract class BaseController extends AbstractController {

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
			System.out.println("" + super.getClass() + "" + cmd + ""
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

	/**
	 * 输出 内容相应页面
	 * 
	 * @param response
	 * @param content
	 */
	public void write(HttpServletResponse response, String content) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", -10);
			response.getWriter().write(content);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}

	}

	/**
	 * 根据请求的类 获取参数值
	 * 
	 * @param <T>
	 * @param clazz
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public <T> T fillToBean(Class<T> clazz, HttpServletRequest request)
			throws Exception {
		Object t = clazz.newInstance();
		// 获取此类声明的字段 私有公共的
		Field[] fields = t.getClass().getDeclaredFields();

		for (Field f : fields) {
			String typeName = f.getType().getName();
			// 检查是否属于支持的类型 不支持 跳过
			if (!FormworkUtil.isSupportType(typeName)) {
				continue;
			}
			// 获取请求值
			String value = request.getParameter(typeName);
			// 如果值不等于空
			if (value != null) {
				Object obj = FormworkUtil.convert(typeName, value);
				System.out.println("CLASS 类型:" + f.getType());
				Method m = clazz.getMethod(FormworkUtil.getMethodByFieldName(f
						.getName()), new Class[] { f.getType() });
				m.invoke(m, obj);
			}

		}
		return (T) t;
	}
}
