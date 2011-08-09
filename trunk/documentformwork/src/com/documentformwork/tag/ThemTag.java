package com.documentformwork.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ThemTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		String contextPath = request.getContextPath();
		StringBuffer render = new StringBuffer();
		render.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
				+ contextPath + "/ext/resources/css/ext-all.css\" />\r\n");
		render.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
				+ contextPath + "/css/style.css\" />\r\n");
		render.append("<script type=\"text/javascript\" src=\"");
		render.append(contextPath);
		render.append("/ext/adapter/ext/ext-base.js\"></script>\r\n");
		render.append("<script type=\"text/javascript\" src=\"");
		render.append(contextPath);
		render.append("/ext/ext-all.js\"></script>\r\n");
		render.append("<script type=\"text/javascript\" src=\"");
		render.append(contextPath);
		render.append("/ext/ext-lang-zh_CN.js\"></script>\r\n");
		render.append("<script type=\"text/javascript\" src=\"");
		render.append(contextPath);
		render.append("/ext/ext-webmessage.js\"></script>\r\n");
		render.append("<script type=\"text/javascript\">");
		render.append("Ext.BLANK_IMAGE_URL='").append(contextPath).append(
				"/ext/resources/images/default/s.gif';\r\n");
		render.append("var contextPath='").append(contextPath).append("';\r\n");
		render.append("var controllerPath='").append(contextPath).append(
				request.getAttribute("request.controller.path")).append(
				"';\r\n");
		render.append("</script>\r\n");
		try {
			this.pageContext.getOut().write(render.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

}
