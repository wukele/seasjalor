Ext.namespace("formwork.web.ui");
/**
 * 文档树型面板
 * 
 * @param {}
 *            itemConfig
 */
formwork.web.ui.DocumentTreePanel = function(itemConfig) {

	var config = {
		height : document.documentElement.clientHeight,
		title : '文档管理',
		renderTo : 'documentTree',
		bbar : [{
					text : '添加类别',
					handler : this.addCategory.createDelegate(this)

				}, '->', {
					text : '修改类别'
				}],
		root : itemConfig.node
	};
	Ext.apply(config, itemConfig || {});
	formwork.web.ui.DocumentTreePanel.superclass.constructor.call(this, config);
	this.init(itemConfig.node);
};
/**
 * 继承Ext.tree.TreePanel
 * 
 * @class formwork.web.ui.DocumentTreePanel
 * @extends Ext.tree.TreePanel
 */
Ext.extend(formwork.web.ui.DocumentTreePanel, Ext.tree.TreePanel, {
	fields : [],
	data : new Array(),
	typeStore : {},
	/**
	 * 初始化
	 */
	init : function(childNodes) {
		this.fields = ["id", "displayText"];
		this.data.push(new Array(childNodes.id, childNodes.text));
		this.bulidCategory(this.data, childNodes.childNodes);
		this.typeStore.fields = this.fields;
		this.typeStore.data = this.data;
	},
	/**
	 * 递归取得类型的类型
	 * 
	 * @param {}
	 *            data
	 * @param {}
	 *            list
	 */
	bulidCategory : function(data, list) {
		for (var i = 0; i < list.length; ++i) {
			this.data.push(new Array(list[i].id, list[i].text));
			if (list[i].childNodes != null) {
				this.bulidCategory(this.data, list[i].childNodes);
			}

		}

	},
	/**
	 * 添加新类别
	 */
	addCategory : function() {
		var fileCategoryFormPanel = new formwork.web.ui.FileCategoryFormPanel(this.typeStore);
		new Ext.Window({
					title : '添加类别',
					width : 500,
					height : 400,
					items : [fileCategoryFormPanel]
				}).show();
	}

});