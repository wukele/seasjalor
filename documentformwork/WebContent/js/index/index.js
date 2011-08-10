
/**
 * 初始化 首页
 */
init = function() {
	// 设置图片地址
	Ext.BLANK_IMAGE_URL = contextPath + "/resources/images/default/s.gif";

	// 创建首页
	// var centerPanel = new privilegesystem.web.index.CenterPanel(null);

	// 框架布局
	var viewPort = new Ext.Viewport({
		layout : 'border',
		enableTabScroll : true,
		bodyStyle : 'margin:0px 0px 0px 0px',
		items : [
				{
					region : 'north',
					frame : true,
					height : 60,
					html : "<div><div >权限系统</div><div  style='float:right;padding-left:8px;' ><a class='head'  href=javascript:locale()>English  </a></div><div  style='float:right'><a  class='head' href='"
							+ contextPath
							+ "/user!logout.action'>  登出</a> </div> </div>"

				}, new privilegesystem.web.index.CenterPanel(null),
				new privilegesystem.web.index.WestPanel(null)]

	});
};
