<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String contextPath = request.getContextPath();

	
%>
<html>
<head>
<title>文档管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/ext2.2/css/ext-all.css">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/swf/css/icon.css">
<script type="text/javascript"
	src="<%=contextPath %>/ext2.2/ext-base.js"></script>
<script type="text/javascript" src="<%=contextPath %>/ext2.2/ext-all.js"></script>
<script src="<%=contextPath %>/swf/swfupload.js" type="text/javascript"></script>
<script src="<%=contextPath %>/swf/swfupload.speed.js"
	type="text/javascript"></script>
<script src="<%=contextPath %>/swf/mode.js" type="text/javascript"></script>
<script src="<%=contextPath %>/swf/handlers.js" type="text/javascript"></script>
<script src="<%=contextPath %>/swf/uploadgrid.js" type="text/javascript"></script>

</head>
<body>

<script type="text/javascript">
	var contextPath="<%=contextPath%>";
  Ext.onReady(function(){  
	   Ext.QuickTips.init();
	   Ext.form.Field.prototype.msgTarget = 'side';
	   Ext.BLANK_IMAGE_URL =contextPath+'/ext2.2/images/s.gif';

	   var documentListToolbar = new Ext.Toolbar({
	    	renderTo: 'documentListToolBarDiv',
	    	items: [
				new Ext.Button({
				    id: 'documentList-back-button',
				    text: '返回',
					iconCls: 'arrow_turn_left'
				}),
				new Ext.Button({
				    id: 'documentList-create-button',
				    text: '新建文件夹',
					iconCls: 'folder_add'
				}),
				'-',
				new Ext.Button({
				    id: 'documentList-upload-button',
				    text: '上传',
					iconCls: 'upload'
				}),
				new Ext.Button({
				    id: 'documentList-download-button',
				    text: '下载',
					iconCls: 'download'
				}),
				new Ext.Button({
				    id: 'documentList-downloadzip-button',
				    text: '打包下载',
					iconCls: 'rar'
				}),
				'-',
				new Ext.Button({
				    id: 'documentList-move-button',
				    text: '移动',
					iconCls: 'folder_go'
				}),
				new Ext.Button({
				    id: 'documentList-delete-button',
				    text: '删除',
					iconCls: 'delete'
				})]
	 		});

		var documentStore=new Ext.data.JsonStore({
					  url:contextPath+'/documentManage.do?method=getDocumentList',
					  fields: ['documentId', 'documentName', 'type', 'size', 'isleaf', 'createDateStr', 'link'],
					  root:'root',
					  autoLoad:true
						
		});

		// 用户数据表格
		var sm = new Ext.grid.CheckboxSelectionModel();
		var documentListGrid = new Ext.grid.GridPanel({		
			id: 'documentListGrid',
			renderTo: 'documentListGridDiv',
			border: false,
			columnLines: true,
			stateful: true,
		    autoScroll: 'auto',
		    height:200,
	        store: documentStore,
	        loadMask: true,
	        cm: new Ext.grid.ColumnModel({
	            defaults: {
	                width: 100,
	                sortable: true
	            },
	            columns: [
							sm,
							new Ext.grid.RowNumberer({header:'Number',width:100}),
				            {id:'isleaf',header: '', width: 30, sortable: true, dataIndex: 'isleaf'},
				            {id:'documentId',header: '文档ID', width: 100, sortable: true, dataIndex: 'documentId', hidden:true},
				            {id:'documentName',header: '文件名', width: 200, sortable: true, dataIndex: 'documentName'},
				            {id:'type',header: '类型', width: 50, sortable: true, dataIndex: 'type'},
				            {id:'size',header: '大小', width: 100, sortable: true, dataIndex: 'size'},
				            {id:'createDateStr',header: '时间', width: 150, sortable: true, dataIndex: 'createDateStr'},
				            {id:'link',header: '链接', width: 150, sortable: true, dataIndex: 'link', hidden:true}
						]
	        }),
	        sm: sm,  
	        bbar: new Ext.PagingToolbar({
				pageSize: 100,
				store: documentStore,
				displayInfo: true,
				displayMsg: "到-----",
				emptyMsg:"没有数据",
				doLoad: function(start){
					baseParams.start = start;
					this.store.load({params: baseParams});
				}
	        })
	      
	    });

	
	  //Hello
	 
  });			 
</script>
<div id="documentListDiv">
<div id="documentListPathBarDiv"></div>
<div id="documentListToolBarDiv"></div>
<div id="documentListGridDiv" style="width: 100%; height: 100%;"></div>
</div>
</body>
</html>

