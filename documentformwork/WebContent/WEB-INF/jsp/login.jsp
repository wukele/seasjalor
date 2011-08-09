<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib uri="http://www.documemtformwork.com/tag-system" prefix="system"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>登录</title>
		<system:theme />
		<script type="text/javascript"
			src="<c:url value='/js/login-window.js'/>"></script>
		<script type="text/javascript">
Ext.onReady(function() {
			Ext.QuickTips.init();
			var LoginWindow = new Ext.ux.LoginWindow({
						modal : true,
						// formBgcolor:'#f0edce',
						basePath : 'images/login',
						winBanner : 'logo.png',
						usernameField : 'userCode',
						passwordField : 'password',
						url : '<c:url value="/login.do?method=inlidate"/>'
					});
			LoginWindow.show();
		});
</script>
	</head>
</html>



























































































