Ext.namespace("formwork.web.ui");
/**
 * 文档树型面板
 * 
 * @param {}
 *            itemConfig
 */
formwork.web.ui.DocumentTreePanel = function(itemConfig) {
	this.init();
	var config = {
		 height:document.documentElement.clientHeight,
		title : '文档管理',
		renderTo : 'documentTree',
		root : itemConfig.node
	};
	Ext.apply(config, itemConfig || {});
	formwork.web.ui.DocumentTreePanel.superclass.constructor.call(this, config);
};
/**
 * 继承Ext.tree.TreePanel
 * 
 * @class formwork.web.ui.DocumentTreePanel
 * @extends Ext.tree.TreePanel
 */
Ext.extend(formwork.web.ui.DocumentTreePanel, Ext.tree.TreePanel, {
			/**
			 * 初始化
			 */
			init : function() {
			
			}

		});