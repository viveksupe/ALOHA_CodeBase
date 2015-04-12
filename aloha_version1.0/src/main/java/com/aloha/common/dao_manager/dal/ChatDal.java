package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.User;


public class ChatDal {
	Connection con = null;
	// write the queries for Post table
	private String SELECT;
	private String INSERT_CHAT;
	private String UPDATE_CHAT;
	private String DELETE_CHAT;

	public ChatDal() {
		SELECT = "SELECT chat.chat_id,chat.chatContent,chat.timestamp,chat.user_id1,chat.user_id2 FROM chat order by chat_id desc limit N";
		INSERT_CHAT = "INSERT INTO post (post_content, hascomments, user_id) VALUES ( ?, ?, ?);";
		UPDATE_CHAT = "UPDATE post SET post_content = ?, hascomments = ?, WHERE post_id = ?;";
		DELETE_CHAT = "DELETE FROM post WHERE post_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	/**
	 * @return List of users
	 * @throws SQLException
	 */
	public ArrayList<User> selectRecentFive() throws SQLException {
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
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getContactNumber());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setDate(6, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(7, u.getIsVerified());
			ps.setInt(8, u.getIsLocked());
			ps.setDate(9, (java.sql.Date) u.getLastActive());

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
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getContactNumber());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setDate(6, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(7, u.getIsVerified());
			ps.setInt(8, u.getIsLocked());
			ps.setDate(9, (java.sql.Date) u.getLastActive());
			ps.setInt(10, u.getUserId());
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
