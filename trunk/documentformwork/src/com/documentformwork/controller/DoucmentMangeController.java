package com.documentformwork.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.dao.FileCategoryDao;
import com.documentformwork.entity.Document;
import com.documentformwork.entity.FileCategory;

public class DoucmentMangeController extends BaseController {

	public FileCategoryDao getFileCategoryService() {
		return fileCategoryService;
	}

	public void setFileCategoryService(FileCategoryDao fileCategoryService) {
		this.fileCategoryService = fileCategoryService;
	}

	private DocumentDao service;

	private FileCategoryDao fileCategoryService;

	private String indexView;

	public void setService(DocumentDao service) {
		this.service = service;
	}

	public String getIndexView() {
		return indexView;
	}

	public void setIndexView(String indexView) {
		this.indexView = indexView;
	}

	private String addView;

	public String getAddView() {
		return addView;
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public ModelAndView addDocument(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(this.addView);
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(this.indexView);
	}

	/**
	 * 查询 文档列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getDocumentList(HttpServletRequest request,
			HttpServletResponse response) {
		this.write(response, service.getGridJson2("select d from Document d"));
		return null;
	}

	public ModelAndView getFileCategoryTreeNode(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(fileCategoryService);
		this.write(response, fileCategoryService.getFileCategoryTreeNode()
				.toJSONObject().toString());
		return null;
	}

	/**
	 * delete choose file
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView deleteFile(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("id");
		if (ids != null && ids.length() != 0) {
			String[] fileIds = ids.split(",");
			for (String id : fileIds) {
				Long fileId = Long.parseLong(id);
				Document d = this.service.find(Document.class, fileId);
				if (d != null) {
					this.service.delete(d);
				}
			}
		}

		return null;

	}

	/**
	 * 保存文件类别
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView saveFileCategory(HttpServletRequest request,
			HttpServletResponse response) {
		String categoryType = request.getParameter("type");
		String name = request.getParameter("name");
		String icon = request.getParameter("icon");
		String description = request.getParameter("description");
		FileCategory category = new FileCategory();

		if (categoryType != null && !categoryType.equals("root")) {
			FileCategory category1 = fileCategoryService.find(
					FileCategory.class, categoryType);
			category.setParentId(category1);
		}

		category.setName(name);
		category.setDescription(description);
		category.setId(UUID.randomUUID().toString());

		fileCategoryService.save(category);

		return null;
	}
}
