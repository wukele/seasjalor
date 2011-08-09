<%@ page language="java" pageEncoding="UTF-8"%>
<%
	request.getRequestDispatcher("/login.do?method=login").forward(request,response);
%>