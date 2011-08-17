<%@ page language="java"	
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.documemtformwork.com/tag-system"
	prefix="system"%>
<%

String contextPath = request.getContextPath();

	
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
		<system:theme />
		<!-- 包含ext js -->
			<script type="text/javascript"
			src="<%=contextPath%>/js/index/centerPanel.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/index/menuTreePanel.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/index/westPanel.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/index/index.js"></script>
		<script type="text/javascript">
		//  var contextPath="<%=contextPath%>";
		  var init;
		  var userName="admin";
		  Ext.onReady(init);
		</script>
	</head>
	<body>

		<div id="main" style="width:100%;"></div>
		
		<div id="center1">
        <p>系统简介...本系统为权限系统！</p>
    </div>
	</body>
</html>
