package com.documentformwork.base.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateDaoImpl extends HibernateDaoSupport implements
		HibernateDao {

	@Override
	public Object getObject(Class clazz, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Class clazz, Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object entity) {
		// TODO Auto-generated method stub

	}

}
