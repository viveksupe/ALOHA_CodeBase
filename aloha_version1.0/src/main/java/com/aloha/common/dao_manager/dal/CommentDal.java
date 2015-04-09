/**
 * 
 */
package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Comment;
import com.aloha.common.entities.Post;

/**
 * @author Renuka
 *
 */
public class CommentDal {
	Connection con = null;
	private String SELECT;
	private String INSERT_COMM;
	private String UPDATE_COMM;
	private String DELETE_COMM;
	
	public CommentDal(){
		SELECT = "SELECT comment.comment_id, comment.comment_content, comment.user_id, comment.post_id FROM comment;";
		INSERT_COMM = "INSERT INTO comment ( comment_content, user_id, post_id) VALUES ( ?, ?, ?);";
		UPDATE_COMM = "UPDATE comment SET comment_content = ?, user_id = ?, post_id = ? WHERE comment_id = ?;";
		DELETE_COMM = "DELETE FROM comment WHERE comment_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}
	
	public Comment getCommentByPrimaryKey(int comment_id) throws SQLException{
		String getCommById = SELECT + " where comment.comment_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getCommById);
			ps.setInt(1, comment_id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				Comment comm = new Comment(
						rSet.getInt("comment_id"),
						rSet.getString("comment_content"), 
						rSet.getDate("date"),
						rSet.getInt("post_id"), 
						rSet.getInt("user_id"));

				return comm;
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
	
	public ArrayList<Comment> getCommentForPost(int post_id) throws SQLException{
		String getPostComms = SELECT + " where comment.post_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getPostComms);
			ps.setInt(1, post_id);
			rSet = ps.executeQuery();
			ArrayList<Comment> comms = new ArrayList<Comment>();
			while (rSet.next()) {
				Comment comm = new Comment(
						rSet.getInt("comment_id"),
						rSet.getString("comment_content"), 
						rSet.getDate("date"),
						rSet.getInt("post_id"), 
						rSet.getInt("user_id"));

				comms.add(comm);
			}
			return comms;

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

	public int deleteComment(int comment_id) throws SQLException{
		String updateCommStatement = DELETE_COMM;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateCommStatement);
			ps.setInt(1, comment_id);
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

	public int insertComment(Comment comment) throws SQLException{
		con = DatabaseHandlerSingleton.getDBConnection();
		String insertCommStatement = INSERT_COMM;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertCommStatement);
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getPostId());
			
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
	
	public int updateComment(Comment comment){
		String updateComm = UPDATE_COMM;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateComm);
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getPostId());
			ps.setInt(4, comment.getCommenttId());
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
