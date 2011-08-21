package com.formwork.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.dao.impl.DocumentServiceImpl;
import com.documentformwork.entity.Document;

public class UploadAction {
	private DocumentDao service=new DocumentServiceImpl();


	private File Filedata;

	private String FiledataFileName;

	private String FiledataContentType;

	private static final String basePath = "uploads";

	// 上传文件
	private String pathSplit(String timeStr, String o, String n) {
		StringBuffer sb = new StringBuffer();
		for (String a : timeStr.split(o)) {
			sb.append(a);
			sb.append(n);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public DocumentDao getService() {
		return service;
	}

	public void setService(DocumentDao service) {
		this.service = service;
	}

	public static String format(Date date, String parttern) {
		DateFormat df = new SimpleDateFormat(parttern);
		return df.format(date);
	}

	public String execute() {
		System.out.println(service);
		System.out.println("======");
		if (Filedata != null && Filedata.length() > 0) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String datastr = format(new Date(), "yyyy@MM@dd");
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath(
							basePath + File.separator
									+ pathSplit(datastr, "@", File.separator));
			String ext = FiledataFileName.substring(FiledataFileName
					.lastIndexOf("."));
			String type=FiledataFileName.substring(FiledataFileName.lastIndexOf(".")+1); 
			System.out.println("type:"+type);
			long size=Filedata.length();
			File path = new File(uploadPath);
			if (!path.exists()) {
				path.mkdirs();
			}
			String newName = uuid + ext;
			String newPath = uploadPath + File.separator + newName;
			Filedata.renameTo(new File(newPath));
			System.out.println(uploadPath);
			System.out.println("FiledataFileName:" + FiledataFileName);
			System.out.println("文件大小："+Filedata.length());
		
			
			
			Document document=new Document();
			
			document.setCreateeDate(new Date());
			
			document.setCreateUser("root");
			document.setDelflag("false");
			document.setName(FiledataFileName);
			document.setType(type);
			document.setSize(size);
			document.setParentId("1");
			document.setUpdateMan("root");
			document.setIsLeaf("N");
			document.setStatus("Y");
			document.setDelflag("N");
			document.setUpdateDate(new Date());
			document.setLink(newPath);
			this.service.save(document);
		}

		return null;
	}

	public File getFiledata() {
		return Filedata;
	}

	public void setFiledata(File filedata) {
		Filedata = filedata;
	}

	public String getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		FiledataContentType = filedataContentType;
	}

}
