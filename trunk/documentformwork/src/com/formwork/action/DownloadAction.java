package com.formwork.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import freemarker.template.SimpleDate;

/**
 * 下载Action
 * 
 * @author style
 * 
 */
public class DownloadAction implements Action {
	private String fileName;
	private Date createDate;

	public String getFileName() {
		return fileName;
	}

	private String inputPath;

	public void setInputPath(String value) {
		inputPath = value;
	}

	public void setFileName(String fileName) {
		// 获取请求参数
		String fname = ServletActionContext.getRequest().getParameter(
				"fileName");
		String dateStr=ServletActionContext.getRequest().getParameter(
		"createDate");
		try {
			fname = new String(fname.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("文件名:" + fileName);
		this.fileName = fname;
	}

	public InputStream getInputStream() throws Exception {
		// 获取更新时间
		String datastr = format(new Date(), "yyyy/MM/dd");
		return ServletActionContext.getServletContext().getResourceAsStream(
				"/uploads/"+datastr+
				"/"+this.fileName);
	}

	public String execute() throws Exception {

		return SUCCESS;
	}

	public static String format(Date date, String parttern) {
		DateFormat df = new SimpleDateFormat(parttern);
		return df.format(date);
	}
}
