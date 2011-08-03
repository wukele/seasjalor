package com.documentmanagement.servelt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServelt extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Æô¶¯DispatcherServlet start");
		
		super.init(config);
		System.out.println("Æô¶¯DispatcherServlet end");
	}

}
