package com.xxx.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.xh.entity.User;

public class ConnectionDataBase {
	private static String password = "tiger";
	private static String name = "scott";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
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

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, name, password);

	}

	public static void main(String[] agrs) throws ClassNotFoundException,
			SQLException {

		instance.saveUser();
	}

	public static void searchProcedur() throws ClassNotFoundException,
			SQLException {
		int result = -1;
		Connection conn = instance.getConn();
		CallableStatement call = null;
		call = conn.prepareCall("{call RIGHT_USER_MANAGE.DEL(?,?)}");
		call.setInt(1, 1);
		call.registerOutParameter(2, Types.INTEGER);
		boolean rs = call.execute();
		int i = call.getInt(2);
		System.out.println(i);
		System.out.println(rs);

	}

	public static void saveUser() throws ClassNotFoundException, SQLException {
		User user = new User();
		// user.setAddress("����");
		user.setEmail("www.xiaohe@163.com");
		user.setLoginName("admin");
		user.setName("xiaohe");
		user.setPassword("admin");
		user.setRole(1);
		Connection conn = instance.getConn();
		CallableStatement call = conn
				.prepareCall("{call RIGHT_USER_MANAGE.SAVE_USER(?,?,?,?,?,?,?)}");
		call.setString(1, "null");
		call.setString(2, user.getLoginName());
		call.setString(3, user.getPassword());
		call.setString(4, user.getEmail());
		call.setInt(5, user.getRole());
		call.setString(6, user.getAddress());
		call.registerOutParameter(7, Types.INTEGER);

		boolean result = call.execute();
		int rs = call.getInt(7);
		System.out.println(result);
		// System.out.println(rs);

	}

	public static List<User> getUser() throws ClassNotFoundException,
			SQLException {
		Connection conn = instance.getConn();
		CallableStatement call = conn
				.prepareCall("{call RIGHT_USER_MANAGE.GET_USER(?)}");
		call.registerOutParameter(1, OracleTypes.CURSOR);
		call.execute();
		ResultSet rs = (ResultSet) call.getObject(1);
		while (rs.next()) {
			System.out.println(rs.getString("T_EMAIL"));
		}
		return null;
	}

}
