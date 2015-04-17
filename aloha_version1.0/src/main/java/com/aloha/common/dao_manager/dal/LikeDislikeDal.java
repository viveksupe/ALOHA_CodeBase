package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Dislike;
import com.aloha.common.entities.Like;
import com.aloha.common.entities.LikeDislike;
import com.aloha.common.entities.LikeType;
import com.aloha.common.entities.Post;

public class LikeDislikeDal {

	Connection con = null;
	// write the queries for Post table
	private String SELECT;
	private String INSERT;
	private String UPDATE;
	private String DELETE;

	public LikeDislikeDal() {
		SELECT = "SELECT likedislike.like_id, likedislike.like_type, likedislike.user_id, likedislike.post_id FROM likedislike";
		INSERT = "INSERT INTO likedislike ( like_type, user_id, post_id) VALUES ( ?, ?, ?);";
		UPDATE = "UPDATE likedislike SET like_type = ? WHERE like_id ?;";
		DELETE = "DELETE FROM likedislike";

		con = DatabaseHandlerSingleton.getDBConnection();

	}

	public LikeDislike GetPostLikeDislike(int postid) throws SQLException {

		ArrayList<Like> likes = new ArrayList<Like>();
		ArrayList<Dislike> dislikes = new ArrayList<Dislike>();

		String getStmt = SELECT
				+ " where likedislike.post_id=? and  likedislike.like_type in (1,2);";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getStmt);
			ps.setInt(1, postid);
			rSet = ps.executeQuery();

			while (rSet.next()) {

				int likeType = rSet.getInt("like_type");

				if (likeType == 1) {
					Like like = new Like();
					like.setLikeId(rSet.getInt("like_id"));
					like.setPostId(rSet.getInt("post_id"));
					like.setUserId(rSet.getInt("user_id"));
					//like.setType(LikeType.Like);
					likes.add(like);
				} else if (likeType == 2) {
					Dislike like = new Dislike();
					like.setDislikeId(rSet.getInt("like_id"));
					like.setPostId(rSet.getInt("post_id"));
					like.setUserId(rSet.getInt("user_id"));
					//like.setType(LikeType.Dislike);
					dislikes.add(like);
				}
			}
			return new LikeDislike(likes, dislikes);

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

	public int updateLike(int type, int likeId) throws SQLException {

		String updateStmt = UPDATE;

		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateStmt);
			ps.setInt(1, type);
			ps.setInt(2, likeId);
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

	
	public int insertLike(Like like) throws SQLException {

		String insertStmt = INSERT;

		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertStmt);
			ps.setInt(1, 1);
			ps.setInt(2, like.getUserId());
			ps.setInt(3, like.getPostId());
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

	public int insertDislike(Dislike like) throws SQLException {

		String insertStmt = INSERT;

		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertStmt);
			ps.setInt(1, 2);
			ps.setInt(2, like.getUserId());
			ps.setInt(3, like.getPostId());
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

	public int deleteAllDataOfPost(int postId) throws SQLException{
		String deleteStmt = DELETE + "where post_id = ?";

		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(deleteStmt);
			ps.setInt(1, postId);
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
