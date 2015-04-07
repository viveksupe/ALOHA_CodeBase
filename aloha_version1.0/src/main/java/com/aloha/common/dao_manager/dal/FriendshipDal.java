package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.FriendshipStatus;
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
		SELECTALL = "SELECT friendship.friendship_id, friendship.user_id1, friendship.user_id2, friendship.friend_status_id, friendship.blocked_by, friendship.req_sent_by FROM friendship;";
		SELECT_FRIENDSHIP = "SELECT friendship.friendship_id, friendship.user_id1, friendship.user_id2, friendship.friend_status_id, friendship.blocked_by, friendship.req_sent_by FROM friendship WHERE friendship.friendship_id = ?;;";
		INSERT_FRIENDSHIP = "INSERT INTO friendship(friendship_id, user_id1, user_id2, friend_status_id, blocked_by, req_sent_by) VALUES(?, ?, ?, ?, ?, ?);";
		UPDATE_FRIENDSHIP = "UPDATE friendship SET friendship_id = ? , user_id1 = ? , user_id2 = ? , friend_status_id = ? , blocked_by = ? , req_sent_by = ? WHERE friendship_id = ?;";
		DELETE_FRIENDSHIP = "DELETE FROM friendship WHERE friendship.friendship_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	/**
	 * @return List of users
	 * @throws SQLException
	 */
	public ArrayList<Friendship> selectFriendshipAll() throws SQLException {
		String SelectUsersAllStatement = SELECTALL;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersAllStatement);
			rSet = ps.executeQuery();
			ArrayList<Friendship> friendships = new ArrayList<Friendship>();
			while (rSet.next()) {
				Friendship friendship = new Friendship();
				friendship.setUser1(new User());
				friendship.setUser2(new User());
				friendship.setFriendshipId(rSet.getInt("friendship_id"));
				friendship.getUser1().setUserId(rSet.getInt("user_id1"));
				friendship.getUser2().setUserId(rSet.getInt("user_id2"));
				friendship.setStatus(rSet.getInt("friend_status_id"));
				int request_sent_by = rSet.getInt("req_sent_by");
				friendship
						.setReq_sent_by(friendship.getUser1().getUserId() == request_sent_by ? friendship
								.getUser1() : friendship.getUser2());
				int blocked_by = rSet.getInt("blocked_by");
				friendship
				.setBlocked_by(friendship.getUser1().getUserId() == blocked_by ? friendship
						.getUser1() : friendship.getUser2());				
			}
			return friendships;
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

	public Friendship selectFriendshipByPrimaryKey(int id) throws SQLException {
		String SelectUsersByPrimaryKeyStatement = SELECT_FRIENDSHIP;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersByPrimaryKeyStatement);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				Friendship friendship = new Friendship();
				friendship.setUser1(new User());
				friendship.setUser2(new User());
				friendship.setFriendshipId(rSet.getInt("friendship_id"));
				friendship.getUser1().setUserId(rSet.getInt("user_id1"));
				friendship.getUser2().setUserId(rSet.getInt("user_id2"));
				friendship.setStatus(rSet.getInt("friend_status_id"));
				int request_sent_by = rSet.getInt("req_sent_by");
				friendship
						.setReq_sent_by(friendship.getUser1().getUserId() == request_sent_by ? friendship
								.getUser1() : friendship.getUser2());
				int blocked_by = rSet.getInt("blocked_by");
				friendship
				.setBlocked_by(friendship.getUser1().getUserId() == blocked_by ? friendship
						.getUser1() : friendship.getUser2());
				return friendship;
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

	public int insertFriendship(Friendship f) throws SQLException {
		con = DatabaseHandlerSingleton.getDBConnection();
		String insertUserStatement = INSERT_FRIENDSHIP;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserStatement);
			ps.setInt(1, f.getFriendshipId());
			ps.setInt(2, f.getUser1().getUserId());
			ps.setInt(3, f.getUser2().getUserId());
			ps.setInt(4, f.getStatus().getStatus());
			ps.setInt(5, f.getBlocked_by().getUserId());
			ps.setInt(6, f.getReq_sent_by().getUserId());

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

	public int updateFriendship(int id, String fname, String lname, String contactNo,
			String email, String pwd, Date dob, int isVerified, int isLocked,
			Date lastActive) throws SQLException {
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
