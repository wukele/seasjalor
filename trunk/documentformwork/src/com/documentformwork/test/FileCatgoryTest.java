package com.documentformwork.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.documentformwork.dao.FileCategoryDao;
import com.documentformwork.entity.FileCategory;

public class FileCatgoryTest {

	ApplicationContext context = null;

	FileCategoryDao fileCategoryService = null;


	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
		fileCategoryService = (FileCategoryDao) context
				.getBean("fileCategoryService");
		System.out.println(fileCategoryService);
	}

	@Test
	public void testFindClass() {
		FileCategory f = fileCategoryService.find(FileCategory.class, "1");

		System.out.println(f.getName());

	}

	@Test
	public void testFileCategoryList() {
		List<FileCategory> list = fileCategoryService.getTopFileTreeNode();
		for (FileCategory f : list) {
			System.out.println(f);

		}

	}

	@Test
	public void testFileCategoryListById() {

		System.out.println(fileCategoryService.getTopFileTreeNode());
	}

	@Test
	public void testSaveFileCategory() {

		FileCategory fileCategory = new FileCategory();
		fileCategory.setId("8");
		fileCategory.setDescription("test");
		fileCategory.setName("test1");
		FileCategory parentFileCategory = fileCategoryService.find(
				FileCategory.class, "1");

		fileCategory.setParentId(parentFileCategory);
		fileCategoryService.save(fileCategory);

	}

	@Test
	public void removeFileCategory() {
		FileCategory fileCategory = new FileCategory();

		fileCategory.setId("1");
		fileCategoryService.delete(fileCategory);
	}
}
