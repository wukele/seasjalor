package com.documentformwork.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.documentformwork.dao.DocumentDao;
import com.documentformwork.dao.UserDao;
import com.documentformwork.entity.User;

public class SpringBeanTest {
	ApplicationContext context = null;
	UserDao userDao = null;
	DocumentDao documentDao=null;
	long id;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
		userDao = (UserDao) context.getBean("userDao");
		
		documentDao=(DocumentDao)context.getBean("documentService");
	}

	@Test 
	public void testDocumentFindList(){
		System.out.println(documentDao.getGridJson2(("select d from Document d")));
		
	}
	
	@Ignore
	public void testSave() {
		User user1 = new User();
		user1.setName("xiaohe");
		user1.setPassword("xiaohe");
		user1 = userDao.save(user1);
		id = user1.getUserId();

	}

	//@Test
	public void testFindList() {
		
		
		System.out.println("用户表集合大小:"+userDao.findAll().size());
	}

	//@Test
	public void testUpdate() {
		User user1 = new User();
		user1.setUserId(id);
		user1.setName("xiaohe1");
		user1.setPassword("xiaohe1");
	}

	@Ignore
	public void testJPA() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("systemPU");
		System.out.println(factory);
		EntityManager em = factory.createEntityManager();
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		em.persist(user);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

		em.close();

		factory.close();

	}

}
