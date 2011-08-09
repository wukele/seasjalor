package com.documentformwork.base.dao;

public interface JPABaseDao {
	public void save(Object entity);

	public void saveOrUpdate(Object entity);

	public void update(Object entity);

	public void remove(Object entity);

	public void remove(Class clazz, Object id);

	public Object getObject(Class clazz, Object id);

}
