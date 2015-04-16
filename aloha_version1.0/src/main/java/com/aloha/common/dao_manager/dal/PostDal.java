/**
 * 
 */
package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Post;
import com.aloha.common.entities.user.User;
import com.mysql.jdbc.Statement;

/**
 * @author Renuka
 *
 */
public class PostDal {
	Connection con = null;
	// write the queries for Post table
	private String SELECT;
	private String INSERT_POST;
	private String UPDATE_POST;
	private String DELETE_POST;

	public PostDal() {
		SELECT = "SELECT post.post_id, post.post_content, post.timestamp, post.user_id FROM post";
		INSERT_POST = "INSERT INTO post (post_content, user_id) VALUES ( ?, ?);";
		UPDATE_POST = "UPDATE post SET post_content = ? WHERE post_id = ?;";
		DELETE_POST = "DELETE FROM post WHERE post_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	public Post getPostByPrimaryKey(int id) throws SQLException {
		String getPostById = SELECT + " where post.post_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getPostById);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				Post post = new Post(rSet.getInt("post_id"),
						rSet.getString("post_content"), 
						rSet.getTimestamp("timestamp"),
						null, null);

				return post;
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

	public int deletePost(int id) throws SQLException {
		String updateUserStatement = DELETE_POST;
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

	public Post insertPost(Post post, int user_id) throws SQLException {
		String insertUserStatement = INSERT_POST;
		PreparedStatement ps = null;
		
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserStatement, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, post.getPost());
			ps.setInt(2, user_id);
			

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				Post insertedPost = getPostByPrimaryKey(rs.getInt(1));
				return insertedPost;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
		return null;
	}

	public int updatePost(Post post) throws SQLException {
		String updatePost = UPDATE_POST;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updatePost);
			ps.setString(1, post.getPost());
			ps.setInt(2, post.getPostId());
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

	public ArrayList<Post> getPostsForUser(int userId) throws SQLException {
		String getPostById = SELECT + " where post.user_id=? order by post.timestamp desc;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getPostById);
			ps.setInt(1, userId);
			rSet = ps.executeQuery();
			ArrayList<Post> posts = new ArrayList<Post>();
			while (rSet.next()) {
				Post post = new Post(
						rSet.getInt("post_id"),
						rSet.getString("post_content"), 
						rSet.getTimestamp("timestamp"),
						null, null);
				posts.add(post);
			}
			return posts;

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

	public ArrayList<User> getPostsForUserAndFriends(int userId) {
		return null;
	}

}
