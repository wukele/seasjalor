package com.documentformwork.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.documentformwork.dao.UserDao;
import com.documentformwork.entity.User;

public class PersistenTest {

	ApplicationContext context = null;
	UserDao userDao = null;
	Integer id;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
		userDao = (UserDao) context.getBean("userDao");
	}

	@Ignore
	public void testBaseDaoServiceImpl() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetPersistentClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		User user = new User();
		System.out.println("要删除的ID："+id);
		user.setUserId(id);

		userDao.delete(user);
		System.out.println("删除成功" + id);
	}

	@Test
	public void testFindAll() {
		System.out.println("查询总记录:" + userDao.findAll().size());
	}

	@Test
	public void testFindById() {
		System.out.println("要查询的ID："+id);
		System.out.println("根据ID" + id + "查找" + userDao.findById(4));
	}

	@Test
	public void testFindByJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBySQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRowCount() {
		System.out.println("总记录数为:"+userDao.findRowCount());
	}

	@Test
	public void testSave() {
		User user1 = new User();
		user1.setName("xiaohe");
		user1.setPassword("xiaohe");
		user1 = userDao.save(user1);
	}

	@Test
	public void testUpdate() {
		User u = new User();
		u.setUserId(id);
		userDao.update(u);
	}

	@Test
	public void testUpdateBySql() {
		String sql="update t_user where id="+id;
		userDao.updateBySql(sql);
	}

}
