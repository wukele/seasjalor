Ext.onReady(function() {
	/* 创建视图 */
	var viewport = new Ext.Viewport( {
		layout : 'border',
		items : [ {
			title : 'north',
			region : 'north',
			html : '<h1>欢迎来到本用户权限管理系统</h1>'
		}, {
			title : 'west',
			width : 200,
			collapsible : true,
			region : 'west',
			html : '菜单'
		}, {
			title : 'center',
			region : 'center',
			xtype : 'tabpanel',
			items : [ {
				title : '面板1'
			}, {
				title : '面板2'
			} ]
		} ]
	});

});