/*
 * 首页西部面板
 * 
 */
Ext.namespace("privilegesystem.web.index");
/**
 * 初始化 首页西部面板
 * 
 * @param {}
 *            itemConfig
 */
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
	this.init();
};
/**
 * 继承 panel
 * 
 * @class privilegesystem.web.index.WestPanel
 * @extends Ext.Panel
 */
Ext.extend(privilegesystem.web.index.WestPanel, Ext.Panel, {
	/**
	 * 初始化面板里面的选项
	 */
	init : function() {
		// 获取 数据
		var menuStore = new Ext.data.JsonStore({
			url : contextPath + '/common.do?method=getModuleByRoot',
			fields : [{
						name : "id"
					}, {
						name : "text"
					}, {
						name : "contextPath"
					}, {
						name : "parentId"
					}],
			listeners : {
				'load' : function(store, records, s) {
					for (var i = 0; i < store.getCount(); ++i) {
						var record = store.getAt(i);
						Ext
								.getCmp("westPanel")
								.add(new privilegesystem.web.index.MenuTreePanel(
										{
											id : record.get("id"),
											text : record.get("text"),
											parentId : record.get("parentId")
										}));
					}
					Ext.getCmp("westPanel").doLayout();
				}
			}
		});
		menuStore.load();

	}
});