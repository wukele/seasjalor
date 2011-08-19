package com.documentformwork.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.documentformwork.dao.BaseDao;
import com.documentformwork.util.FormworkUtil;

@SuppressWarnings("unchecked")
public class BaseDaoServiceImpl<T, ID extends Serializable> extends
		JpaDaoSupport implements BaseDao<T, ID> {
	static final Log logger = LogFactory.getLog(BaseDaoServiceImpl.class);
	private Class persistentClass;

	public BaseDaoServiceImpl() {
		this.persistentClass = (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println("当前实体类:"+this.persistentClass.getName());
		logger.info("当前实体类:" + this.persistentClass.getName());

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
		return (Long) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				StringBuffer sql = new StringBuffer("select count(*) from  ");
				sql.append(" " + persistentClass.getName());
				System.out.println("查询语句SQL" + sql);
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

	/**
	 * 返回Grid Json 字符
	 */
	@Override
	public String getGridJson(String listQuery, Map params, String countSql,
			String start, String limmit) {

		List list = this.findBySQL(listQuery);
		JSONArray array = new JSONArray();
		try {
			if (list != null && list.size() > 0) {

				Class cls = list.get(0).getClass();
				System.out.println(cls.getSimpleName());
				// 获取所有的字段
				Field fields[] = cls.getFields();
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					JSONObject json = new JSONObject();
					for (Field f : fields) {
						Method method = cls.getDeclaredMethod(FormworkUtil
								.getMethodByFieldName(f.getName()), null);
						Object value = method.invoke(f.getName(), obj);
						json.put(f.getName(), value);
					}
					array.add(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGridJson error detail :" + e.getMessage());
		}
		JSONObject obj = new JSONObject();
		obj.put("root", array);
		return obj.toString();
	}

	@Override
	public List findList(final String queryString,
			final Map<String, Object> paramsMap, final int beginIndex,
			final int maxCount) {
		return getJpaTemplate().executeFind(new JpaCallback() {
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery(queryString);
				if (paramsMap != null) {
					for (String key : paramsMap.keySet()) {
						query.setParameter(key, paramsMap.get(key));
					}
				}
				query.setFirstResult(beginIndex);
				query.setMaxResults(maxCount);
				return query.getResultList();
			}
		});
	}

	@Override
	public <T> T find(Class<T> paramClass, Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
