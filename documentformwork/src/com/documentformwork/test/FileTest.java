package com.documentformwork.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.entity.Document;

public class FileTest {

	ApplicationContext context = null;

	DocumentDao documentDao = null;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
		documentDao = (DocumentDao) context.getBean("documentService");
	}

	@Test
	public void saveFile() {
		Document document = new Document();

		document.setCreateDate(new Date());

		document.setCreateUser("root");
		document.setDelflag("false");
		document.setName("ceshi");
		document.setType("50");
		document.setSize(50);
		document.setParentId("1");
		document.setUpdateMan("root");
		document.setIsLeaf("N");
		document.setStatus("Y");
		document.setDelflag("N");
		document.setUpdateDate(new Date());
		document.setLink("aaa");
		Document d = documentDao.save(document);
		System.out.println(d);
	}

	@Test
	public void removeFile() {
		
//		Document document = new Document();
//
//		document.setCreateeDate(new Date());
//
//		document.setCreateUser("root");
//		document.setDelflag("false");
//		document.setName("ceshi");
//		document.setType("50");
//		document.setSize(50);
//		document.setParentId("1");
//		document.setUpdateMan("root");
//		document.setIsLeaf("N");
//		document.setStatus("Y");
//		document.setDelflag("N");
//		document.setUpdateDate(new Date());
//		document.setLink("aaa");
//		Document d = documentDao.save(document);
		
		Document d2=documentDao.find(Document.class,Long.parseLong("51"));
		
		this.documentDao.delete(d2);

	}

}
