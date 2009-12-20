package com.xxx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 访问数据库 帮助类
 * 
 * @author xiaohe
 * 
 */
public class ConnectionDataBase {
	private static ConnectionDataBase instance = null;

	public ConnectionDataBase getIntance() {
		if (null == instance) {
			return new ConnectionDataBase();
		} else {
			return instance;
		}
	}

	@SuppressWarnings("static-access")
	public static Connection getConn() throws ClassNotFoundException,
			SQLException {
		return instance.getConnection();

	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(ConstantUtil.JDBC_FILE)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Class.forName(p.getProperty(ConstantUtil.DRIVER_CLASS_NAME));

		return DriverManager.getConnection(p.getProperty(ConstantUtil.URL), p
				.getProperty(ConstantUtil.NAME), p.get(ConstantUtil.PASSWORD)
				.toString());

	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		System.out.println(ConnectionDataBase.getConn());
	}

}
