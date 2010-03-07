Ext.onReady(function() {
	/* 登录界面初始化 */
	var formPanel = new Ext.Panel( {
		title : '用户登录',
		width : 550,
		height : 500,
		labelAlign : 'right',
		hideLabels : false,
		frame : true,
		defaultType : 'textfield'

	});
	/* 数据类型定义 */
	var ds_role_type = [ {
		name : 'id',
		type : 'int'
	}, {
		name : 'roleName',
		type : 'string'
	} ];
	/* 创建角色数据存储器 */
	var ds_role_select = new Ext.data.Store( {
		url : 'roleList.action',
		reader : new Ext.data.JsonReader( {
			root : 'root'
		}, ds_role_type)

	});

	/* 用户 窗体 */
	var window = new Ext.Window( {
		title : '用户登录',
		width : 500,
		frame : true,
		height : 500,
		items : [ {
			xtype : 'panel',
			layout : 'form',
			defaultType : 'textfield',
			labelAlign : 'right',
			hideLabels : false,
			defaults : {
				width : 140
			},
			items : [ {
				fieldLabel : '用户角色',
				xtype : 'combo',
				selectOnFocus : true,
				loadText : '正在加载....'
			}, {
				fieldLabel : '用户名:',
				name : 'name',
				allowBlank : false

			}, {
				fieldLabel : '密码',
				allowBlank : false,
				name : 'password',
				inputType : 'password'
			}, {
				fieldLabel : '确认密码',
				allowBlank : false,
				inputType : 'password'
			} ],
			buttons : [ {
				text : '确认',
				type : 'submit',
				handler : function() {
					ds_role_select.load();
					alert(ds_role_select);
				}
			}, {
				text : '取消',
				type : 'reset'
			} ]
		} ]
	});
	window.show();
});