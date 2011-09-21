package com.formwork.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.entity.Document;

public class UploadAction {
	private DocumentDao service = null;

	public UploadAction() {
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"application.xml");
		service = (DocumentDao) app.getBean("documentService");
	}

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

	public static String format(Date date, String parttern) {
		DateFormat df = new SimpleDateFormat(parttern);
		return df.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public String execute() {
		if (Filedata != null && Filedata.length() > 0) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String datastr = format(new Date(), "yyyy@MM@dd");
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath(
							basePath + File.separator
									+ pathSplit(datastr, "@", File.separator));
			String ext = FiledataFileName.substring(FiledataFileName
					.lastIndexOf("."));
			String type = FiledataFileName.substring(FiledataFileName
					.lastIndexOf(".") + 1);
			long size = Filedata.length();
			File path = new File(uploadPath);
			if (!path.exists()) {
				path.mkdirs();
			}
			String newName = uuid + ext;
			String newPath = uploadPath + File.separator + newName;
			Filedata.renameTo(new File(newPath));
			Document document = new Document();
			document.setCreateDate(new Date());
			document.setCreateUser("root");
			document.setDelflag("false");
			document.setName(newName);
			document.setType(type);
			document.setSize(size);
			document.setParentId("1");
			document.setUpdateMan("root");
			document.setIsLeaf("N");
			document.setStatus("Y");
			document.setDelflag("N");
			document.setUpdateDate(new Date());
			document.setLink("aaa");
			try {
				Document d = service.save(document);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Runtime ...");

			}
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
