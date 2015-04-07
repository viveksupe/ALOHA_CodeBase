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
		INSERT_COMM = "INSERT INTO post (post_id, post_content, hascomments,date, user_id) VALUES (?, ?, ?, ?, ?);";
		UPDATE_COMM = "UPDATE post SET post_id = ?, post_content = ?, hascomments = ?, date = ? WHERE post_id = ?;";
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
}
