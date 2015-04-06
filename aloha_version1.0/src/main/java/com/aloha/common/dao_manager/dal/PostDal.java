/**
 * 
 */
package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.util.ArrayList;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.User;

/**
 * @author Renuka
 *
 */
public class PostDal {
	Connection con = null;
	// write the queries for User table
	private String SELECT;
	private String INSERT_USER;
	private String UPDATE_USER;
	private String DELETE_USER;

	PostDal() {
		SELECT = "SELECT post.post_id, post.post_content, post.hascomments, post.user_id FROM post;";
		INSERT_USER = "INSERT INTO post (post_id, post_content, hascomments, user_id) VALUES (<{post_id: }>, <{post_content: }>, <{hascomments: 0}>, <{user_id: }>);";
		UPDATE_USER = "UPDATE user SET user_id = ?, fname = ?, lname = ?, contact_number = ?, email = ?, password = ?, bdate = ?, isVerfied = ?, isLocked = ?, lastactive = ? WHERE user_id = ?;";
		DELETE_USER = "DELETE FROM post";
		con = DatabaseHandlerSingleton.getDBConnection();
	}
	
	public User selectPost(int postId){
		return null;
	}
	
	public boolean deletePost(int postId){return false;}
	
	public boolean insertPost(User user){return false;}
	
	public boolean updatePost(User user){return false;}
	
	public ArrayList<User> getPostsForUser(int userId){return null;}
	
	public ArrayList<User> getPostsForUserAndFriends(int userId){return null;}
	
}
