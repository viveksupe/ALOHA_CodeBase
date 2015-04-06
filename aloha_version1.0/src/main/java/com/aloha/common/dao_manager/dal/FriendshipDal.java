package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.User;

/**
 * @author Milind
 *
 */
public class FriendshipDal {

	Connection con = null;
	// write the queries for User table
	private String SELECTALL;
	private String SELECT_FRIENDSHIP;
	private String INSERT_FRIENDSHIP;
	private String UPDATE_FRIENDSHIP;
	private String DELETE_FRIENDSHIP;

	
	/**
	 * Constructor
	 */
	public FriendshipDal() {
		SELECTALL = "SELECT friendship.FId, friendship.UserID1, friendship.UserID2, friendship.FriendStatusID, friendship.BlockedBy, friendship.ReqSentBy FROM friendship;";
		SELECT_FRIENDSHIP = "SELECT friendship.FId, friendship.UserID1, friendship.UserID2, friendship.FriendStatusID, friendship.BlockedBy, friendship.ReqSentBy FROM friendship WHERE friendship.FId = ?;;";
		INSERT_FRIENDSHIP = "INSERT INTO friendship(FId, UserID1, UserID2, FriendStatusID, BlockedBy, ReqSentBy) VALUES(?, ?, ?, ?, ?, ?);";
		UPDATE_FRIENDSHIP = "UPDATE friendship SET FId = ? , UserID1 = ? , UserID2 = ? , FriendStatusID = ? , BlockedBy = ? , ReqSentBy = ? WHERE FId = ?;";
		DELETE_FRIENDSHIP = "DELETE FROM friendship WHERE friendship.FId = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	
	/**
	 * @return List of users
	 * @throws SQLException
	 */
	public ArrayList<User> selectUserAll() throws SQLException {
		String SelectUsersAllStatement = SELECTALL;
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
		String SelectUsersByPrimaryKeyStatement = SELECT_FRIENDSHIP;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersByPrimaryKeyStatement);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				int fId=rSet.getInt("FId");
				int userID1=rSet.getInt("UserID1");
				int userID2=rSet.getInt("UserID2");
				int friendStatusID=rSet.getInt("FriendStatusID");
				int blockedBy=rSet.getInt("BlockedBy");
				int reqSentBy=rSet.getInt("ReqSentBy");
				/*Fetch the user object in the */
				
				
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

	public int insertUser(int id, String fname, String lname, String contactNo,
			String email, String pwd, Date dob, int isVerified, int isLocked,
			Date lastActive) throws SQLException {
		con = DatabaseHandlerSingleton.getDBConnection();
		String insertUserStatement = INSERT_FRIENDSHIP;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserStatement);
			ps.setInt(1, id);
			ps.setString(2, fname);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, contactNo);
			ps.setString(5, email);
			ps.setString(6, pwd);
			ps.setDate(7, (java.sql.Date) dob);
			ps.setInt(8, isVerified);
			ps.setInt(9, isLocked);
			ps.setDate(10, (java.sql.Date) lastActive);

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

	public int updateUser(int id, String fname, String lname, String contactNo,
			String email, String pwd, Date dob, int isVerified, int isLocked, Date lastActive) throws SQLException {
	String updateUserStatement = UPDATE_FRIENDSHIP;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateUserStatement);
			ps.setInt(1, id);
			ps.setString(2, fname);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, contactNo);
			ps.setString(5, email);
			ps.setString(6, pwd);
			ps.setDate(7, (java.sql.Date) dob);
			ps.setInt(8, isVerified);
			ps.setInt(9, isLocked);
			ps.setDate(10, (java.sql.Date) lastActive);
			ps.setInt(11, id);
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
		String updateUserStatement = DELETE_FRIENDSHIP;
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
