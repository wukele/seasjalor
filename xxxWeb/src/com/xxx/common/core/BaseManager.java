package com.xxx.common.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xxx.common.util.ConnectionDataBase;

/**
 * 核心实现类对SQL语句 对存储过程的操作
 * 
 * @author xiaohe
 * 
 * @param <T>
 *            泛型
 */
public abstract class BaseManager<T extends Base> {

	@SuppressWarnings("unchecked")
	private ListBase listbase = new ListBase();
	/**
	 * 日志 记录 对象
	 */

	private static Log log = LogFactory.getLog(BaseManager.class);
	/**
	 * 数据连接对象
	 */
	private Connection conn = null;
	/**
	 * 存储过程执行对象
	 */

	private CallableStatement call = null;

	/**
	 * 执行构造函数 初始化 数据库连接对象 和存储过程执行对象
	 */
	public BaseManager() {
		/**
		 * 获取连接
		 */
		try {
			conn = ConnectionDataBase.getConn();
		} catch (ClassNotFoundException e) {
			log.error("-------------获取数据库连接发生错误没有找到CLASS--------------" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("-------------获取数据库连接发生错误SQL异常--------------" + e);
			e.printStackTrace();
		}

	}

	/**
	 * 调用存储过程返回 数据集list
	 * 
	 * @param t
	 *            输入参数
	 * @param procedureName
	 *            存储过程名称 (包名+存储过程名)
	 * @param page
	 *            分页对象
	 * @return 执行结果集的List
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeQueryProcedureGetListEntity(List t,
			String procedureName, Page page) {

		return null;
	}

	/**
	 * 执行 （增删 改 存储过程 返回 执行 的结果）
	 * 
	 * @param inList
	 *            输入参数集合
	 * @param procedureName
	 *            存储过程名
	 * @return 是否成功 表识 -1为不成功 0 为成功
	 */
	@SuppressWarnings("unchecked")
	public Integer executeProcedureNoParme(List inList, String procedureName) {
		return 0;
	}

	/**
	 * 执行存储过程 返回 list 不带实体类
	 * 
	 * @param inList
	 *            输入参数
	 * @param procedureNam
	 *            存储过程名 如 第一个方法
	 * @param page
	 *            分页对象
	 * @return list
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public ListBase executeQueryProcedureGetList(List inList,
			String procedureName, Page page) {
		// 执行存储过程
		try {
			// 得到 ping 的存储过程 信息
			String sql = this.assemblyProcedureStatement(procedureName, inList,
					page);
			// 初始化 CallableStatement 对象
			initCallableStatment(sql);
			// 设置存储过程的输入参数
			this.setInParameRegisterOutParam(call, inList, page);

			boolean flag = call.execute();
			// 设置 查询到的信息
			ResultSet rs = this.setQueryInfo(call, inList);

			log.info("执行结果" + flag);
			/**
			 * 初始化List信息
			 */
			initList(rs);
			/**
			 * 关闭资源
			 */
			this.closeRes(conn, call, rs);
		} catch (SQLException e) {
			log.error("执行存储过程execute 发生异常" + e);
			e.printStackTrace();
		}
		return this.listbase;
	}

	/**
	 * 执行存储过程 返回List对象 里面存map对象 列名为 键 为小写
	 * 
	 * @param inList
	 *            输入参数 集合
	 * @param procedureName
	 *            存储过程名
	 * @param page
	 *            分页对象
	 * @return map
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public List executeQueryProcedureGetMap(List inList, String procedureName,
			Page page) {
		try {
			// 得到 ping 的存储过程 信息
			String sql = this.assemblyProcedureStatement(procedureName, inList,
					page);
			// 初始化 CallableStatement 对象
			initCallableStatment(sql);
			// 设置存储过程的输入参数
			this.setInParameRegisterOutParam(call, inList, page);

			boolean flag = call.execute();
			// 设置 查询到的信息
			ResultSet rs = this.setQueryInfo(call, inList);

			log.info("执行结果" + flag);
			/**
			 * 初始化List信息
			 */
			initMap(rs);
			/**
			 * 关闭资源
			 */
			this.closeRes(conn, call, rs);
		} catch (SQLException e) {
			log.error("获取存储Map 信息出错" + e);
			e.printStackTrace();
		}
		return this.listbase.getDataList();

	}

	/**
	 * 组装 存储过程语句
	 * 
	 * @param procedureName存储过程名
	 * @param inList
	 *            输入参数
	 * @return 执行存储过程的语句
	 */
	@SuppressWarnings("unchecked")
	public static String assemblyProcedureStatement(String procedureName,
			List inList, Page page) {
		/**
		 * 执行 存储过程 固定格式
		 */
		StringBuffer sql = new StringBuffer("{call  ");
		/**
		 * 追加 存储过程名(包名+存储过程名)(如果没有 存储过程没有包名例外 )
		 */
		sql.append(procedureName);
		sql.append("(");
		if (null != inList && inList.size() != 0)
			/**
			 * 为 存储过程 赋占位符 根据传如的参数大小 由于 list的索引 从0开始 所有 它的大小加上1
			 */
			for (int i = 0; i < inList.size(); ++i) {
				System.out.println(i);
				sql.append("?");

				/**
				 * 如果没有到最后一个参数赋占位符 为参数后面加上逗号
				 */
				if (i < inList.size() - 1) {
					sql.append(",");
				}

			}
		/**
		 * 如果分页对象不为空 我们将 增加 三个占位符 1.开始行,2为结束行,3为总记录数
		 */
		if (null != page && null != inList && inList.size() != 0) {
			sql.append(",?,?,?");
		} else {
			sql.append("?,?,?");
		}
		/**
		 * 增加 输出的结果集 和 游标
		 */
		sql.append(",?,?");
		sql.append(")}");
		log.info("sql:" + sql.toString());

		return sql.toString();
	}

	/**
	 * 初始化 callableStatement 对象(执行存储过程对象)
	 */
	public void initCallableStatment(String procedureName) {
		try {
			call = conn.prepareCall(procedureName);
		} catch (SQLException e) {
			log.error("-----------初始化存储过程执行对象出错-----------" + e);
			e.printStackTrace();
		}

	}

	/**
	 * 关闭所有的资源
	 * 
	 * @param conn
	 *            数据库连接
	 * @param call存储过程执行对象
	 * @param rs
	 *            结果集
	 */

	public void closeRes(Connection conn, CallableStatement call, ResultSet rs) {
		try {
			if (null != conn) {
				conn.close();
			} else if (null != call) {
				call.close();
			} else if (null != null) {
				rs.close();
			}
		} catch (SQLException e) {
			log.error("--------------关闭 资源 发生错误--------------" + e);
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				call.close();
				rs.close();
			} catch (SQLException e1) {
				log.error("--------------关闭 资源 发生错误--------------" + e1);
				e1.printStackTrace();
			}
		}

	}

	/**
	 * 设置 存储过程参数 和注册输出参数
	 * 
	 * @param call
	 *            执行存储过程对象
	 * @param inList
	 *            输入参数
	 * @param page
	 *            分页对象
	 * @return 查询结果集
	 */
	@SuppressWarnings("unchecked")
	public void setInParameRegisterOutParam(CallableStatement call,
			List inList, Page page) {
		try {
			/**
			 * 循环 设置输入参数
			 */
			System.out.println(inList.size());
			for (int i = 0; i < inList.size(); ++i) {
				/**
				 * 判断传入的类型 然后在做添加 输入参数
				 */
				System.out.println(i);
				call.setObject(i + 1, inList.get(i));
			}
			// 设置分页参数 开始行 结束行 因为 list 索引从0 开始
			call.setInt(inList.size() + 1, page.getStartRow());
			call.setInt(inList.size() + 2, page.getEndRow());
			/**
			 * 注册 游标和返回的结果集
			 */
			call.registerOutParameter(inList.size() + 3, Types.INTEGER);
			call.registerOutParameter(inList.size() + 4, Types.INTEGER);
			call.registerOutParameter(inList.size() + 5, OracleTypes.CURSOR);
		} catch (SQLException e) {
			log.error("设置输入参数发生错误" + e);
			e.printStackTrace();
		}

	}

	/**
	 * 设置 查询的信息
	 * 
	 * @param call
	 *            存储过程对象
	 */
	@SuppressWarnings("unchecked")
	public ResultSet setQueryInfo(CallableStatement call, List inList) {
		ResultSet rs = null;
		try {
			this.listbase.setResultRecord(call.getInt(inList.size() + 4));
			/**
			 * 设置 list 输出信息
			 */
			rs = (ResultSet) call.getObject(inList.size() + 5);

		} catch (SQLException e) {
			log.error("得到输出的参数信息出错" + e);
			e.printStackTrace();
		}
		return rs;

	}

	/**
	 * 初始化Map
	 * 
	 * @param rs
	 *            数据库结果集
	 * @return map 数据集合 key 为列名 为小写
	 */

	@SuppressWarnings("unchecked")
	public List initMap(ResultSet rs) {

		try {
			/**
			 * 获取列和属性的 对象
			 */
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取 数据库 列名个数
			Integer rowCount = rsmd.getColumnCount();

			// 读取 数据
			while (rs.next()) {
				Map map = new HashMap();
				/**
				 * 循环取出列名 用以做map 的键
				 */
				for (int i = 1; i < rowCount; ++i) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				/**
				 * 将map对象 添加到 List 中
				 */
				this.listbase.getDataList().add(map);

			}
		} catch (SQLException e) {
			log.error("封装Map信息出错" + e);
			e.printStackTrace();
		}
		return this.listbase.getDataList();
	}

	@SuppressWarnings("unchecked")
	public List<T> initList(ResultSet rs) {
		try {
			/**
			 * 获取 列数
			 */
			ResultSetMetaData rsmd = rs.getMetaData();
			Integer rowCount = rsmd.getColumnCount();
			while (rs.next()) {
				List list = new ArrayList();
				for (int i = 1; i < rowCount; ++i) {
					list.add(rs.getObject(i));
				}
				/**
				 * 将list 添加到list中的vo
				 */
				listbase.getDataList().add(list);

			}
		} catch (SQLException e) {
			log.error("循环数据发生异常" + e);
			e.printStackTrace();
		}
		return listbase.getDataList();
	}

	public static void main(String[] agrs) throws SQLException {
		/*
		 * List list = new ArrayList(); Page p = new Page(); // list.add("aa");
		 * // list.add("nm"); list.add("1"); list.add("1F"); list.add(1);
		 * list.add("111"); list.add(1); list.add(10); // // list.add(p); //
		 * System.out.println(BaseManager // .assemblyProcedureStatement("aa",
		 * null, p)); // for (int i = 0; i < list.size(); ++i) { //
		 * System.out.println(list.get(i).getClass()); // } // BaseManager b =
		 * new UserService(); List l = b.executeQueryProcedureGetMap(list,
		 * "RIGHT_USER_MANAGE.QUERY_USER_ALL", new Page());
		 * System.out.println(l.size());
		 */
		// b.call = b.conn.prepareCall("{call RIGHT_USER_MANAGE.LOGIN(?,?,?)}");
		// ParameterMetaData pmd = b.call.getParameterMetaData();
		// System.out.println(pmd.getParameterCount());
		// System.out.println(pmd.getParameterType(1));
	}

}
