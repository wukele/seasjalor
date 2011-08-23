package com.documentformwork.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.JpaCallback;

public interface JPABaseDao {
	public abstract void persist(Object paramObject);

	public abstract Object merge(Object paramObject);

	public abstract void remove(Object paramObject);

	public abstract void remove(Class paramClass, Object paramObject);

	public abstract <T> T find(Class<T> paramClass, Object paramObject);

	public abstract List findList(String paramString);

	public abstract List findList(String paramString,
			Map<String, ? extends Object> paramMap);

	public abstract List findList(String paramString,
			Map<String, Object> paramMap, int paramInt1, int paramInt2);

	public abstract Object executeNativeSql(String paramString);

	public abstract Object executeCallback(JpaCallback paramJpaCallback);

	public abstract int executeUpdate(String paramString);

	public abstract int executeUpdate(String paramString,
			Map<String, Object> paramMap);

	public abstract String getValueByFunction(String paramString);

	public abstract String getValueByFunction(String paramString,
			Map<String, Object> paramMap);

}
