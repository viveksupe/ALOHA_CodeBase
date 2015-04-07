package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.User;

/**
 * @author Milind
 *
 */
public class UserDal {

	Connection con = null;
	// write the queries for User table
	private String SELECT;
	private String INSERT_USER;
	private String UPDATE_USER;
	private String DELETE_USER;

	/**
	 * Constructor
	 */
	public UserDal() {
		SELECT = "SELECT user.user_id, user.fname, user.lname, user.contact_number, user.email, user.password, user.bdate, user.isVerified, user.isLocked, user.lastactive FROM user";
		INSERT_USER = "INSERT INTO user(user_id, fname, lname, contact_number, email, password, bdate, isVerified, isLocked, lastactive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UPDATE_USER = "UPDATE user SET user_id = ?, fname = ?, lname = ?, contact_number = ?, email = ?, password = ?, bdate = ?, isVerified = ?, isLocked = ?, lastactive = ? WHERE user_id = ?;";
		DELETE_USER = "DELETE FROM user WHERE user_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	/**
	 * @return List of users
	 * @throws SQLException
	 */
	public ArrayList<User> selectUserAll() throws SQLException {
		String SelectUsersAllStatement = SELECT;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersAllStatement);
			rSet = ps.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			while (rSet.next()) {
				User u = new User(rSet.getInt("user_id"),
						rSet.getString("fname"), rSet.getString("lname"),
						rSet.getString("contact_number"),
						rSet.getString("email"), rSet.getString("password"),
						rSet.getDate("bdate"), rSet.getInt("isVerified"),
						rSet.getInt("isLocked"), rSet.getDate("lastActive"));
				users.add(u);
			}
			return users;
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

	public User selectUserByPrimaryKey(int id) throws SQLException {
		String SelectUsersByPrimaryKeyStatement = SELECT
				+ " where user.user_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersByPrimaryKeyStatement);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				User u = new User(rSet.getInt("user_id"),
						rSet.getString("fname"), rSet.getString("lname"),
						rSet.getString("contact_number"),
						rSet.getString("email"), rSet.getString("password"),
						rSet.getDate("bdate"), rSet.getInt("isVerified"),
						rSet.getInt("isLocked"), rSet.getDate("lastActive"));
				return u;
			} else
				return null;
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

	public int insertUser(User u) throws SQLException {
		con = DatabaseHandlerSingleton.getDBConnection();
		String insertUserStatement = INSERT_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserStatement);
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getContactNumber());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getPassword());
			ps.setDate(7, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(8, u.getIsVerified());
			ps.setInt(9, u.getIsLocked());
			ps.setDate(10, (java.sql.Date) u.getLastActive());

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

	public int updateUser(User u) throws SQLException {
		String updateUserStatement = UPDATE_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateUserStatement);
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getContactNumber());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getPassword());
			ps.setDate(7, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(8, u.getIsVerified());
			ps.setInt(9, u.getIsLocked());
			ps.setDate(10, (java.sql.Date) u.getLastActive());
			ps.setInt(11, u.getUserId());
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
		String updateUserStatement = DELETE_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
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
