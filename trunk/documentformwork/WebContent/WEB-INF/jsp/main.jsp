<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.documemtformwork.com/tag-system"
	prefix="system"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<system:theme />
<!-- src="<c:url value='/js/documentManage.js'/>" -->
<script type="text/javascript" language="javascript"
	>
Ext.onReady(function() {
	Ext.msg.alter("Hello");
	
});
	
</script>
</head>
<body>
		<a href="documentManage.do?method=addDocument">文件管理</a>		
</body>
</html>