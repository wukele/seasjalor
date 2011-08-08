Ext.namespace("com.documentformwork.ui");
com.documentformwork.ui.DocumentManageWindow = function(itemConfig) {

	/**
	 * 配置属性
	 * 
	 */
	var config = {
		layout : 'absolute',// 采用绝对定位
		width : 500,
		height : 400,
		frame : true,
		border : false,
		defaults : {
			buttonAlign : 'center',
			width : 50,
			anchor : '50%'
		},
		items : [ {
			x : '25%',
			xtype : 'label',

			text : '用户名:'
		}, {
			x : '40%',
			allowBlank : false,// 不允许为空
			xtype : 'textfield'
		}, {
			x : '25%',
			y : '10%',
			xtype : 'label',
			text : '密码:'
		}, {
			x : '40%',
			y : '10%',
			inputType : 'password',
			id : 'password1',
			allowBlank : false,// 不允许为空
			xtype : 'textfield'
		}, {
			x : '25%',
			y : '20%',
			xtype : 'label',
			text : '确认密码:'
		}, {
			x : '40%',
			y : '20%',
			allowBlank : false,// 不允许为空
			inputType : 'password',
			vtypeText : "两次密码不一致！",
			confirmTo : 'password1',
			xtype : 'textfield'
		}, {
			x : '25%',
			y : '30%',
			xtype : 'label',
			text : '电子邮件:'
		}, {
			x : '40%',
			y : '30%',
			xtype : 'textfield',
			vtype : 'email'
		}, {
			x : '25%',
			y : '40%',
			xtype : 'label',
			allowBlank : false,// 不允许为空
			text : '年龄:'
		}, {
			x : '40%',
			y : '40%',
			xtype : 'numberfield'
		}, {
			x : '40%',
			y : '40%',
			xtype : 'textfield'
		}, {
			x : '25%',
			y : '50%',
			xtype : 'label',
			text : '性别:'

		}, {
			x : '40%',
			y : '50%',
			xtype : 'combo',
			triggerAction : 'all',
			mode : 'local',
			store : new Ext.data.ArrayStore( {
				fields : [ "text", "val" ],
				data : [ [ "男", 1 ], [ "女", 2 ] ]
			}),
			displayField : 'text',
			valueField : 'val'
		}, {
			x : '35%',
			y : '60%',
			anchor : '10%',
			xtype : 'button',
			text : '注册',
			handler : function() {
				// 发送请求 验证注册是否成功
		}
		}, {
			x : '70%',
			y : '60%',
			anchor : '10%',
			xtype : 'button',
			text : '重置'
		} ]

	};
	/**
	 * 
	 */
	config = Ext.apply(config, itemConfig);
	/**
	 * 复制父类构造函数
	 */
	com.documentformwork.ui.DocumentManageWindow.superclass.constructor
			.call(this, config);
};
/**
 * 继承ext window 类
 */
Ext.extend(com.documentformwork.ui.DocumentManageWindow, Ext.FormPanel, {

});

/*Ext.onReady(function() {
			var documentForm = new com.documentformwork.ui.DocumentManageWindow(null);
			documentForm.render(Ext.getBody());
		});*/