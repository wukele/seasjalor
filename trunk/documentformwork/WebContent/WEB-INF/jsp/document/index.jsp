<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.documemtformwork.com/tag-system"
	prefix="system"%>
<%

String contextPath = request.getContextPath();

	
%>
<html>
<head>
<title>文档管理</title>
<meta http-equiv="cache-control" content="no-cache,no-store">

<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/ext2.2/css/ext-all.css">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/swf/css/icon.css">
<script type="text/javascript"
	src="<%=contextPath %>/ext2.2/ext-base.js"></script>
<script type="text/javascript" src="<%=contextPath %>/ext2.2/ext-all.js"></script>
 <script src="<%=contextPath %>/swf/swfupload.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/swfupload.speed.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/mode.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/handlers.js" type="text/javascript"></script> 



</head>
<body>

<script type="text/javascript">
	var contextPath="<%=contextPath%>";
	var root;

	function UploadGrid()
	   {
	          var mine=this;           
	          var states=[{v:-1,t:'等待'},{v:0,t:'就绪'},{v:1,t:'上传中'},{v:2,t:'停止'},{v:3,t:'成功'},{v:4,t:'失败'}];  
	          function  statesRender(v)
	          {
	             for(var i=0;i<states.length ;i++)
	             {
	                if(states[i].v==v)
	                {
	                  return states[i].t;
	                }
	             }
	          }
	          function rateRender(v)
	          {
	             v=v?v:1;
	             return "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'><tr><td bgcolor='#0000FF' height='100%' align='center' width='"+v+"%'><font color='white'>"+v+"%</font></td><td></td></tr></table>";
	          
	          }
	                
	 		 var rn=new Ext.grid.RowNumberer();
	 		 var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});					 
	 		 var cm = new Ext.grid.ColumnModel([	
	 	       	rn,
	 	       	sm,	
	 			{header:'文件名称',dataIndex:'fileName',menuDisabled:true},
	 			{header:'大小'   ,dataIndex:'fileSize',menuDisabled:true},
	 			{header:'进度'   ,dataIndex:'rate',menuDisabled:true,renderer:rateRender},
	 			{header:'速度'   ,dataIndex:'speed',menuDisabled:true},
	 			{header:'状态'   ,dataIndex:'state',menuDisabled:true,renderer:statesRender}
	 	       ]);    
	 	
	 	        this.ds = new Ext.data.Store({        
	 	        proxy: new Ext.data.HttpProxy({url:'test!query.action',method:'post'}),
	 	        remoteSort:false,
	 	        reader: new Ext.data.JsonReader(
	 	        {totalProperty:'records',root:'root'},       
	 	        [
	 	        {name: 'id'},
	 			{name: 'fileName'},
	 			{name: 'code'},
	 			{name: 'fileSize'},
	 			{name: 'rate'},
	 			{name: 'speed'},
	 			{name: 'state'}
	 	        ]) 
	 	        
	 	    });  
	 	    
	 	    
	 	    var RC=Ext.data.Record.create([
	 		         {name: 'id', mapping: 'id'},
	 		         {name: 'code', mapping: 'code'},
	 		         {name: 'fileName', mapping: 'fileName'},
	 		         {name: 'fileSize', mapping: 'fileSize'},
	 		         {name: 'rate', mapping: 'rate'},
	 		         {name: 'speed', mapping: 'speed'}
	 		         ]);	   
	 		         
	 	    this.grid = new Ext.grid.GridPanel({      
	 	    title:'上传表格',
	 		ds: mine.ds,
	 	    cm: cm,
	 	    sm: sm,	
	 	    anchor:'100%',
	 	    loadMask:{msg:'数据加载中...'},
	 	    viewConfig:{forceFit:true},
	 	    height:300, 
	 	    tbar:[{id:'spanSWFUploadButton',text:'-'},'-'],	 
	 		listeners:{
	 		  render:function()
	 		  {
	 		    // ytb-sep
	 		    var cmp=Ext.getCmp("spanSWFUploadButton");
	 		    var pcont=cmp.getEl().parent();		   
	 		    pcont.update("<span id='spanSWFUploadButton' class='blank'>bb</span>");
	 			var swfu = new SWFUpload({
	 				upload_url :"<%=request.getContextPath()%>" +"/upload.action",
	 				flash_url : "swf/swfupload.swf",
	 				button_placeholder_id : "spanSWFUploadButton",
	 				button_image_url : "swf/bt.png",
	 				button_text_right_padding : 100,				
	 				button_width: 61,
	 				button_height : 22,
	 				
	 				button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES,
	 				// handler here 
	 				swfupload_loaded_handler : Handlers.swfUploadLoaded,				
	 				file_queued_handler : Handlers.fileQueued,
	 				file_queue_error_handler : Handlers.fileQueueError,	
	 				upload_progress_handler : Handlers.uploadProgress,
	 				upload_error_handler : Handlers.uploadError,
	 				upload_success_handler : Handlers.uploadSuccess				
	 			});
	 			swfu.grid=mine.grid;
	 			swfu.ds=mine.ds;
	 			swfu.RC=RC;			
	 		}
	 	  }	
	 	  }); 
	   }
	 		 
  Ext.onReady(function(){  
	   Ext.QuickTips.init();
	   Ext.form.Field.prototype.msgTarget = 'side';
	   Ext.BLANK_IMAGE_URL =contextPath+'/ext2.2/images/default/s.gif';

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
					  fields: ['id', 'name', 'type', 'size', 'isleaf', 'createDateStr', 'link'],
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
		   // autoScroll: 'auto',
		    height:document.documentElement.clientHeight,
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
				            {id:'id',header: '文档ID', width: 100, sortable: true, dataIndex: 'id', hidden:true},
				            {id:'name',header: '文件名', width: 200, sortable: true, dataIndex: 'name'},
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

	//请求获取文档目录结构
	 Ext.Ajax.request({
		    url : contextPath+ '/documentManage.do?method=getFileCategoryTreeNode',
			method:'post',
			success:loadDocumentTree,
			failure:function(){
		 		 Ext.Msg.alert("ERROR",
					"Server communication failure");
			}
		  });
	  function loadDocumentTree(response){
				var json=eval('('+response.responseText+')');
				//创建节点
				 root=new Ext.tree.TreeNode({
							id:json.id,
							text:json.text,
							expanded:true
					});
				buildTree(root,json.children);
				initDocumentTree(root);
		  };
		  
	 function buildTree(node,jsonList){
		 for(var i=0;i<jsonList.length;++i){
			 
			 var subNode = new Ext.tree.TreeNode({
					text : jsonList[i].text,
					id : jsonList[i].id,
					leaf : jsonList[i].leaf,
					url : jsonList[i].url,
					icon : jsonList[i].icon
				});
			node.appendChild(subNode);
			if(jsonList[i].children!=null){
				buildTree(subNode,jsonList[i].children);
				}
			 }
		 
		 };
	
	 function initDocumentTree(root){
		var itemConfig={node:root};
			var documentPanel=new formwork.web.ui.DocumentTreePanel(itemConfig);
		 };

		 Ext.fly("documentList-upload-button").on("click",function(){
		 var grid=new UploadGrid();
		  var window=new Ext.Window({
					title:'upload',
					width:500,
					height:300,
					items:[grid.grid]
			  });
		  window.show();
		 });
	   
		 
		
  });			 
</script>
 <script src="<%=contextPath %>/js/document/documentTreePanel.js" type="text/javascript"></script> 
<div id="documentListDiv">
<div id="documentListPathBarDiv"></div>
<div id="documentListToolBarDiv"></div>
<div id="documentTree" style="float:left;width:10%;height:100%"></div>
<div id="documentListGridDiv" style="height:100%" ></div>
</div>
</body>
</html>

