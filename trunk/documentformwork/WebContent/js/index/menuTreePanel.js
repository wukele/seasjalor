/*
 * 树型菜单面板
 * 
 */

Ext.namespace("privilegesystem.web.index");
/**
 * 初始化 树型菜单
 * 
 * @param {}
 *            itemConfig
 */
privilegesystem.web.index.MenuTreePanel = function(treeConfig) {
	var config = {
		title : treeConfig.text,
		// dataUrl : contextPath + '/right.action',
		loader : new Ext.tree.TreeLoader({
					url : contextPath + '/common.do?method=getModuleByRoot',
					baseAttrs : {
						url : "url"
					}
				}),
		root : new Ext.tree.AsyncTreeNode({
					id : treeConfig.id,
					isTarget : false,
					listeners : {
						'beforeload' : function(treeLoader, n) {
						}
					}
				}),
		rootVisible : false,
		leaf : treeConfig.leaf,
		dataMap : {
			//id : 'id',
			text : 'text',
			leaf : 'leaf',
			link : 'url'
		}

	};
	/**
	 * 复制属性
	 */
	Ext.apply(config, treeConfig || {});
	/**
	 * 调用 父类构造函数
	 */
	privilegesystem.web.index.MenuTreePanel.superclass.constructor.call(this,
			config);
	this.registerNodeClik();
};
/**
 * 继承 树 面板
 * 
 * @class privilegesystem.web.index.MenuTreePanel
 * @extends Ext.tree.TreePanel
 */
Ext.extend(privilegesystem.web.index.MenuTreePanel, Ext.tree.TreePanel, {
			registerNodeClik : function() {
				// 点击事件
				this.on("click", function(node, e) {
							// 如果还有子节点
							if (node.leaf) {
								// 添加选项卡
								Ext.getCmp("centerPanel").addTab(node);
							}
						});

			}
		});