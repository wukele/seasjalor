package com.documentformwork.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface BaseDao<T, ID extends Serializable> {

	public T save(T entity);

	public T update(T entity);

	public Integer updateBySql(final String sql);

	public void delete(T entity);

	public abstract <T> T find(Class<T> paramClass, Object paramObject);

	public T findById(ID id);

	public List findByJPQL(String jpql);

	public List findBySQL(String sql);

	public List findAll();

	public long findRowCount();

	public List findList(final String queryString,
			final Map<String, Object> paramsMap, final int beginIndex,
			final int maxCount);

	public String getGridJson(String listQuery, Map params, String countSql,
			String start, String limmit);

}
