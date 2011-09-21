Ext.namespace("formwork.web.ui");

formwork.web.ui.FileCategoryFormPanel = function(itemConfig) {
	var items = [{
				triggerAction : 'all',
				xtype : 'combo',
				fieldLabel : '类型',
				mode : 'local',
				name : 'combType',
				store : new Ext.data.SimpleStore({
							fields : itemConfig.fields,
							data : itemConfig.data
						}),
				valueField : 'id',
				displayField : 'displayText'
			}, {
				xtype : 'textfield',
				name : 'textName',
				fieldLabel : '名字'
			}, {
				fieldLabel : '图标',
				name : 'textIcon'
			}, {
				fieldLabel : '描述',
				xtype : 'textarea',
				name : 'textaDescription'
			}];
	var config = {
		bodyStyle : 'padding:5px 5px 0',
		frame : true,
		defaultType : "textfield",
		width : 500,
		height : 370,
		defaults : {
			width : 180,
			anchor : '98%'
		},
		id : 'fileCategoryForm',
		items : items,
		buttonAlign : 'center',
		buttons : [{
					text : 'sava',
					handler : this.saveFileCategory.createDelegate(this)
				}, {
					text : 'cancel',
					handler : this.closeFileCatory.createDelegate(this)
				}]
	};
	Ext.apply(config, itemConfig || {});
	formwork.web.ui.FileCategoryFormPanel.superclass.constructor.call(this,
			config);
};
/**
 * 
 * @class formwork.web.ui.FileCategoryFormPanel
 * @extends Ext.FormPanel
 */
Ext.extend(formwork.web.ui.FileCategoryFormPanel, Ext.FormPanel, {
	fileCategory : {},
	fileCategoryForm : "",
	saveFileCategory : function() {
		this.fileCategoryForm = Ext.getCmp("fileCategoryForm").getForm();
		alert(this.fileCategoryForm.findField("textName").getValue());
		this.fileCategory.name = this.fileCategoryForm.findField("textName").getValue();
		
		this.fileCategory.type = this.fileCategoryForm.findField("combType").getValue();
		this.fileCategory.icon = this.fileCategoryForm.findField("textIcon").getValue();
		this.fileCategory.description = this.fileCategoryForm.findField("textaDescription").getValue();
		Ext.Ajax.request({
					url : contextPath + '/documentManage.do?method=saveFileCategory',
					method : 'post',
					params : {
							type:this.fileCategory.type,
							name:this.fileCategory.name,
							icon:this.fileCategory.icon,
							description:this.fileCategory.description
					},
					success : function(response) {						
						Ext.Msg.alert("MESSAGE", "save success");

					},
					failure : function() {
						Ext.Msg.alert("ERROR", "save fail ");
					}
				});

	},
	closeFileCatory : function() {

	},
	success : function(response) {
		Ext.Msg.alert("MESSAGE", "save success");

	}

});
