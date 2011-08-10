<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
#toolbar .x-panel-body {
	background-color: RGB(255, 255, 255);
	border: 0 none;
}
-->
</style>
<div id="north-div">
	<table width="100%" height="60" border="0" cellpadding="0"
		cellspacing="0">
		<tr valign="top">
			<td align="left" width="80%">
			<!-- 	<img
					src="<c:url value="/images/log_top.png"></c:url>"> -->
			</td>
			<td width="250" align="right" valign="middle">
				当前用户： ${sessionScope.userName}&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="show();">设置</a>&nbsp;&nbsp;
				<a
					href="<c:url value="/system/common.shtml?action=logout"></c:url>">注销</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div id="toolbar"></div>
			</td>
		</tr>
	</table>
</div>


























































































