package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.User;

public class UserDal {

	Connection con = null;
	// write the queries for User table
	private String SELECT;
	private String INSERT_USER;
	private String UPDATE_USER;
	private String DELETE_USER;

	UserDal() {
		SELECT = "SELECT user.user_id, user.fname, user.lname, user.contact_number, user.email, user.password, user.bdate, user.isVerfied, user.isLocked, user.lastactive FROM user";
		INSERT_USER = "INSERT INTO user(user_id, fname, lname, contact_number, email, password, bdate, isVerfied, isLocked, lastactive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UPDATE_USER = "UPDATE user SET user_id = ?, fname = ?, lname = ?, contact_number = ?, email = ?, password = ?, bdate = ?, isVerfied = ?, isLocked = ?, lastactive = ? WHERE user_id = ?;";
		DELETE_USER = "DELETE FROM user";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	public User selectUserByPrimaryKey(int id) throws SQLException {
		String SelectUsersByPrimaryKeyStatement = SELECT + " where userid=?";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			ps = con.prepareStatement(SelectUsersByPrimaryKeyStatement);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			User u = new User(rSet.getInt("user_id"), rSet.getString("fname"),
					rSet.getString("lname"), rSet.getString("email"),
					rSet.getString("password"), rSet.getDate("bdate"));
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rSet != null)
				rSet.close();
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int insertUser(int id, String fname, String lname, String email, String pwd, Date dob) throws SQLException {
		String insertUserStatement = INSERT_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			ps = con.prepareStatement(insertUserStatement);
			ps.setInt(1, id);
			ps.setString(2, fname);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, email);
			ps.setString(5, pwd);
			ps.setDate(6, (java.sql.Date) dob);
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int updateUser(int id, String fname, String lname, String email, String pwd, Date dob) throws SQLException {
		String updateUserStatement = UPDATE_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			ps = con.prepareStatement(updateUserStatement);
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int deleteUser(int id) throws SQLException {
		String updateUserStatement = DELETE_USER + " where user_id=?";
		PreparedStatement ps = null;
		int result = -1;
		try {
			ps = con.prepareStatement(updateUserStatement);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}
}
