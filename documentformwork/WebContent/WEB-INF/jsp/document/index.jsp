<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.documemtformwork.com/tag-system"
	prefix="system"%>
	<%

String contextPath = request.getContextPath();

	
%> 
<html>
<head>
<title>swf 上传</title>
<link  rel="stylesheet" type="text/css" href="<%=contextPath %>/ext2.2/css/ext-all.css">
 <script  type="text/javascript" src="<%=contextPath %>/ext2.2/ext-base.js"></script>
 <script  type="text/javascript" src="<%=contextPath %>/ext2.2/ext-all.js"></script>
 <script src="<%=contextPath %>/swf/swfupload.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/swfupload.speed.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/mode.js" type="text/javascript"></script>
 <script src="<%=contextPath %>/swf/handlers.js" type="text/javascript"></script> 
</head>
<body>

<script type="text/javascript"> 

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
				upload_url : "upload.action",
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
	   Ext.BLANK_IMAGE_URL ='../../images/s.gif';
	   var grid=new UploadGrid();
	   var viewPort =new Ext.Viewport({
	    layout:'fit',
	    items:[grid.grid]
	   })
  })			 
 </script>
</body>
</html>

