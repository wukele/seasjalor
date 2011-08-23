package com.documentformwork.base.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.documentformwork.base.dao.JPABaseDao;
import com.vsg.framework.dao.DaoRuntimeException;
import com.vsg.framework.dao.impl.JpaDaoImpl;

public class JPABaseDaoImpl extends JpaDaoSupport implements JPABaseDao {
	protected final static Log log = LogFactory.getLog(JpaDaoImpl.class);

	public void save(Object entity) {
		try {
			super.getJpaTemplate().persist(entity);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void update(Object entity) {
		getJpaTemplate().merge(entity);
	}

	public void remove(Object entity) {
		getJpaTemplate().remove(entity);
	}

	public void saveOrUpdate(Object entity) {
		getJpaTemplate().merge(entity);
	}

	public Object getObject(Class clazz, Object id) {
		return getJpaTemplate().find(clazz, id);
	}

	public Object getObject(Object id) {
		return getObject(Object.class, id);
	}

	public Object getObject(String className, Object id)
			throws DaoRuntimeException {
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (Exception e) {
			throw new DaoRuntimeException("cause:" + e);
		}
		return getObject(clazz, id);
	}

	public Object find(Object id) {
		return getJpaTemplate().find(Object.class, id);
	}

	public Object getObjectWithParameter(final String eql, final Map paramsMap)
			throws DaoRuntimeException {
		Object object = null;
		try {
			JpaCallback callback = new JpaCallback() {
				public Object doInJpa(EntityManager em) {
					Query query = em.createQuery(eql);
					if (paramsMap != null) {
						String key = null;
						Iterator<String> it = paramsMap.keySet().iterator();
						while (it.hasNext()) {
							key = (String) it.next();
							query.setParameter(key, paramsMap.get(key));
						}
					}
					return query.executeUpdate();
				}
			};
			object = getJpaTemplate().execute(callback);
		} catch (DataAccessException e) {
			throw new DaoRuntimeException("cause:" + e);
		}
		return object;
	}

	public int executeUpdate(final String eql, final Map paramsMap) {

		JpaCallback callback = new JpaCallback() {
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery(eql);
				if (paramsMap != null) {
					String key;
					for (Iterator iterator = paramsMap.keySet().iterator(); iterator
							.hasNext(); query.setParameter(key, paramsMap
							.get(key)))
						key = (String) iterator.next();
				}
				return Integer.valueOf(query.executeUpdate());
			}
		};
		return ((Integer) (getJpaTemplate().execute(callback))).intValue();
	}

	public List find(final String eql, final Object[] params, final int begin,
			final int max) {
		// TODO Auto-generated method stub
		List ret = (List) this.getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				// TODO Auto-generated method stub
				Query query = em.createQuery(eql);
				int parameterIndex = 1;
				if (params != null && params.length > 0) {
					for (Object obj : params) {
						query.setParameter(parameterIndex++, obj);
					}
				}
				if (begin >= 0 && max > 0) {
					query.setFirstResult(begin);
					query.setMaxResults(max);
				}
				if (begin >= 0 && max > 0) {
					query.setFirstResult(begin);
					query.setMaxResults(max);
				}
				return query.getResultList();
			}
		});
		if (ret != null && ret.size() >= 0) {
			return ret;
		} else {
			return null;
		}
	}

	public Object executeNativeSql(final String sql) {
		return getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) {
				return Integer.valueOf(em.createNativeQuery(sql)
						.executeUpdate());
			}
		});
	}

	public void persist(Object entity) {
		try {
			getJpaTemplate().persist(entity);
			logger.debug((new StringBuilder("persist ")).append(
					entity.getClass().getName()).append(" successed!")
					.toString());
		} catch (RuntimeException e) {
			logger.error((new StringBuilder("persist ")).append(
					entity.getClass().getName()).append(" failed!").toString(),
					e);
			throw e;
		}
	}

	public Object merge(Object entity) {
		Object object;
		try {
			object = getJpaTemplate().merge(entity);
			logger.debug((new StringBuilder("merge ")).append(
					entity.getClass().getName()).append(" successed!")
					.toString());
		} catch (RuntimeException e) {
			logger.error((new StringBuilder("merge ")).append(
					entity.getClass().getName()).append(" failed!").toString(),
					e);
			throw e;
		}
		return object;
	}

	public String getValueByFunction(final String eql,
			final Map<String, Object> paramsMap) {
		return getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery(eql);
				if (paramsMap != null) {
					for (String key : paramsMap.keySet()) {
						query.setParameter(key, paramsMap.get(key));
					}
				}

				return query.getSingleResult();
			}
		}).toString();
	}

	public int executeUpdate(String eql) {
		return executeUpdate(eql, null);
	}

	public String getValueByFunction(String eql) {
		return getValueByFunction(eql, null);
	}

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

	public void remove(Class clazz, Object id) {
		remove(find(clazz, id));
	}

	public <T> T find(Class<T> clazz, Object id) {
		try {
			Object t = getJpaTemplate().find(clazz, id);
			this.logger.debug("find " + clazz.getName() + " successed."
					+ " id:" + id);
			return (T) t;
		} catch (RuntimeException e) {
			this.logger.error("find " + clazz.getName() + " failed." + " id:"
					+ id, e);
			throw e;
		}
	}

	public List findList(String queryString) {
		try {
			List list = getJpaTemplate().find(queryString);
			this.logger.debug("execute queryString successed!");
			return list;
		} catch (RuntimeException e) {
			this.logger.error("callBack execute failed!", e);
			throw e;
		}
	}

	public List findList(String queryString,
			Map<String, ? extends Object> paramsMap) {
		return getJpaTemplate().findByNamedParams(queryString, paramsMap);
	}

	public List findList(String queryString, Object[] params) {
		return getJpaTemplate().find(queryString, params);
	}

	public Object executeCallback(JpaCallback callback) {
		return getJpaTemplate().execute(callback);
	}

	public List getObjectList(Class clazz) {
		throw new RuntimeException("not implemented method!");
	}

	public List queryList(String queryName) throws DaoRuntimeException {
		throw new RuntimeException("not implemented method!");
	}

	public List queryListWithParameter(String queryName, Map paramsMap)
			throws DaoRuntimeException {
		throw new RuntimeException("not implemented method!");
	}

	public List queryListWithParameter(String queryName, Object[] values)
			throws DaoRuntimeException {
		throw new RuntimeException("not implemented method!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
