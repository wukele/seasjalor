/*
 * 首页西部面板
 * 
 */
Ext.namespace("privilegesystem.web.index");
/**
 * 
 * 初始化 首页西部面板
 * 
 * @param {}
 *            itemConfig
 */
var loadMenuTree;
var buildTree;
var root = null;
privilegesystem.web.index.WestPanel = function(itemConfig) {
	/**
	 * 配置选项
	 */
	var config = {
		region : 'west',
		id : 'westPanel',
		title : '当前用户:' + userName,
		split : true,
		width : 200,
		minSize : 175,
		maxSize : 400,
		margins : '2 0',
		collapsible : true,
		collapseMode : 'mini',
		layout : 'accordion',
		layoutConfig : {
			animate : false
		}
	};
	// 复制itemConfig 属性
	Ext.apply(config, itemConfig || {});
	// 继承构造函数
	privilegesystem.web.index.WestPanel.superclass.constructor.call(this,
			config);
	this.loadTreeMenu();

};
/**
 * 继承 panel
 * 
 * @class privilegesystem.web.index.WestPanel
 * @extends Ext.Panel
 */
Ext.extend(privilegesystem.web.index.WestPanel, Ext.Panel, {
			loadTreeMenu : function() {
				Ext.Ajax.request({
							url : contextPath
									+ '/common.do?method=getModuleByRoot',
							params : {},
							success : loadMenuTree,
							failure : function() {
								Ext.Msg.alert("ERROR",
										"Server communication failure");
							}
						});
			}
		});

loadMenuTree = function(response) {
	var json = eval('(' + response.responseText + ')');
	root = new Ext.tree.TreeNode({
				text : json.text,
				id : json.id,
				expanded : true
			});
	buildTree(root, json.children);
	initTreeMenu();
};
buildTree = function(node, jsonList) {
	for (var i = 0; i < jsonList.length; i++) {
		var subNode = new Ext.tree.TreeNode({
					text : jsonList[i].text,
					id : jsonList[i].id,
					leaf : jsonList[i].leaf,
					url : jsonList[i].url,
					icon : jsonList[i].icon
				});
		node.appendChild(subNode);
		if (jsonList[i].children != null) {
			buildTree(subNode, jsonList[i].children);
		}
	}
}
initTreeMenu = function() {
	Ext.getCmp("westPanel")
			.add(new privilegesystem.web.index.MenuTreePanel(null));
	Ext.getCmp("westPanel").doLayout();
}
