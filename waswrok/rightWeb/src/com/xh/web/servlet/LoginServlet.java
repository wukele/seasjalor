package com.xh.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Context ct = null;
		try {
			ct = new InitialContext();
			DataSource ds = (DataSource) ct.lookup("jdbc/oracle");
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from emp");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("id:" + rs.getInt(1));
				System.out.println("name:" + rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
