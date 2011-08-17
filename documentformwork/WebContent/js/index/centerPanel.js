/*
 * 中间面板
 * 
 */
Ext.namespace("privilegesystem.web.index");

privilegesystem.web.index.CenterPanel = function(itemConfig) {
	var config = {
		id : "centerPanel",
		enableTabScroll : true,
		region : 'center',
		deferredRender : false,
		autoScroll : true,
		activeTab : 0,
		margins : '2 0'
	};
	Ext.apply(config, itemConfig || {});
	privilegesystem.web.index.CenterPanel.superclass.constructor.call(this,
			config);
	this.init(itemConfig);
};

Ext.extend(privilegesystem.web.index.CenterPanel, Ext.TabPanel, {
	/**
	 * 初始化
	 */
	init : function(itemConfig) {
		this.add({
					title : '首页',
					closable : false,
					autoScroll : true,
					border : false,
					contentEl : Ext.DomHelper.append(document.body, {
								tag : 'iframe',
								style : 'border 0px none',
								id : Ext.id(),
								src : contextPath + '/index_main.html',
								height : "100%",
								width : "100%",
								frameBorder : 0
							})
				});

	},
	/**
	 * 添加选项卡
	 */
	addTab : function(node) {
		var tab = this.getItem(node.id);
		if (tab) {
			this.setActiveTab(tab);
		} else {
			var count = 8;
			if (this.items.length >= count) {
				Ext.Msg.alert('提示', '同时打开的菜单不能超过' + count + '个');
				return
			} else {
				Ext.MessageBox.show({
							msg : '正在载入菜单，请稍候...',
							width : 220,
							closable : false,
							icon : Ext.MessageBox.INFO
						});
				tab = this.add(this.createIframePanel(node));
				this.setActiveTab(tab);
			}
			tab.show();
			setTimeout(function() {
						Ext.MessageBox.hide();
					}, 600);
		}
	},
	/**
	 * 创建Iframe面板
	 * 
	 * @param {}
	 *            node
	 * @return {}
	 */
	createIframePanel : function(node) {
		var html = '<iframe src="'
				+ contextPath
				+ "/"
				+ node.attributes.url
				+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>';
		// 创建面板
		var ifPanel = new Ext.Panel({
					id : node.attributes.id,
					title : node.attributes.text,
					iconCls : 'tabs',
					closable : true,
					html : html,
					listeners : {
						beforedestroy : function(panel) {
							var iFrame = panel.contentEl;
							if (iFrame != undefined && iFrame != null
									&& iFrame.src != undefined
									&& iFrame.src != null)
								if (iFrame.src) {
									iFrame.src = "javascript:false";
								}
						}
					}
				});
		return ifPanel;
	}
});