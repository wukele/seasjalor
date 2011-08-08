package com.documentformwork.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.documentformwork.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoServiceImpl<T, ID extends Serializable> extends
		JpaDaoSupport implements BaseDao<T, ID> {
	static final Log logger = LogFactory.getLog(BaseDaoServiceImpl.class);
	private Class persistentClass;

	public BaseDaoServiceImpl() {
		this.persistentClass = (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		logger.info("当前实体类:"+this.persistentClass.getName());
		
	}

	public Class getPersistentClass() {
		return persistentClass;
	}

	@Override
	public void delete(T entity) {
		getJpaTemplate().remove(entity);

	}

	@Override
	public List findAll() {
		return getJpaTemplate().executeFind(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) throws PersistenceException {
				StringBuffer sql = new StringBuffer(" from  ");
				sql.append(persistentClass.getName());
				System.out.println("SQL：" + sql);
				sql.append("  obj");
				return em.createQuery(sql.toString()).getResultList();
			}
		});
	}

	@Override
	public T findById(Serializable id) {
		return (T) getJpaTemplate().find(persistentClass, id);

	}

	@Override
	public List findByJPQL(String jpql) {
		return getJpaTemplate().find(jpql);
	}

	@Override
	public List findBySQL(final String sql) {
		return getJpaTemplate().executeFind(new JpaCallback() {
			public List doInJpa(EntityManager em) throws PersistenceException {
				return em.createNativeQuery(sql).getResultList();
			}
		});
	}

	@Override
	public long findRowCount() {
		return  (Long)getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				StringBuffer sql = new StringBuffer("select count(*) from  ");
				sql.append(" " + persistentClass.getName());
				System.out.println("查询语句SQL"+sql);
				return em.createQuery(sql + "").getResultList().get(0);
			}
		});
	}

	@Override
	public T save(T entity) {
		getJpaTemplate().persist(entity);
		return (T) (entity);
	}

	@Override
	public T update(T entity) {
		getJpaTemplate().merge(entity);
		return (T) entity;
	}

	@Override
	public Integer updateBySql(final String sql) {
		return (Integer) getJpaTemplate().execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) throws PersistenceException {
				return em.createNamedQuery(sql).executeUpdate();
			}
		});
	}

}
