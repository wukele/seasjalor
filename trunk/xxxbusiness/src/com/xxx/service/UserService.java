package com.xxx.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.xh.entity.User;
import com.xxx.common.util.ConnectionDataBase;
import com.xxx.dao.UserDao;

public class UserService implements UserDao {
	private static Connection conn = null;

	public Integer delete(Integer id) {
		Integer result = -1;

		try {
			Connection conn = ConnectionDataBase.getConn();
			CallableStatement call = conn
					.prepareCall("{call RIGHT_USER_MANAGE.DEL(?,?)}");
			call.setInt(1, id);
			call.registerOutParameter(2, Types.INTEGER);

			call.execute();
			result = call.getInt(2);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<User> getList() {
		List<User> list = new ArrayList<User>();
		try {
			conn = ConnectionDataBase.getConn();
			CallableStatement call = conn
					.prepareCall("{call RIGHT_USER_MANAGE.GET_USER(?)}");

			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			ResultSet rs = (ResultSet) call.getObject(1);

			while (rs.next()) {
				User user = new User();
				user.setAddress(rs.getString("t_address"));
				user.setEmail(rs.getString("t_email"));
				user.setId(new Integer(rs.getInt("id")).intValue());
				user.setLoginName(rs.getString("t_Login_Name"));
				user.setPassword(rs.getString("t_password"));
				user.setTempRole(rs.getString("t_Role_Id"));
				list.add(user);
			}
			System.out.println(list.size());
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public User getUser(User user) {
		User u = null;
		try {
			conn = ConnectionDataBase.getConn();
			CallableStatement call = conn
					.prepareCall("{call RIGHT_USER_MANAGE.LOGIN(?,?,?)}");
			System.out.println(user.getLoginName());
			System.out.println(user.getPassword());
			call.setString(1, user.getLoginName());
			call.setString(2, user.getPassword());
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.execute();
			ResultSet rs = (ResultSet) call.getObject(3);
			System.out.println(rs);
			System.out.println(rs.getRow());
			while (rs.next()) {
				u = new User();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return u;
	}

	public Integer save(User user) {
		System.out.println(user.toString());
		Integer result = -1;
		try {
			Connection conn = ConnectionDataBase.getConn();
			CallableStatement call = conn
					.prepareCall("{call RIGHT_USER_MANAGE.SAVE_USER(?,?,?,?,?,?,?)}");
			call.setString(1, user.getName());
			call.setString(2, user.getLoginName());
			call.setString(3, user.getPassword());
			call.setString(4, user.getEmail());
			call.setInt(5, user.getRole().intValue());
			call.setString(6, user.getAddress());
			call.registerOutParameter(7, Types.INTEGER);
			call.execute();
			result = call.getInt(7);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer modify(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
