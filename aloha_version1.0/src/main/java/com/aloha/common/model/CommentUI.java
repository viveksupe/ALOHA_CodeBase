package com.aloha.common.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.dal.CommentDal;
import com.aloha.common.entities.Comment;
import com.aloha.common.entities.Post;
import com.aloha.common.entities.user.User;

public class CommentUI {
	private int commentId;
	private String commentData;
	private String commentDate;
	private String userName;
	private int userId;
	private int postId;
	
	

	/**
	 * @param commentData
	 * @param userId
	 * @param postId
	 */
	public CommentUI(String commentData, int userId, int postId) {
		super();
		this.commentData = commentData;
		this.userId = userId;
		this.postId = postId;
	}

	// region Getter Setter method
	
	// endregion

	public CommentUI() {
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentData() {
		return commentData;
	}

	public void setCommentData(String commentData) {
		this.commentData = commentData;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public ArrayList<CommentUI> getCommentsForPost(User user, Post post) throws SQLException {

		ArrayList<CommentUI> comments = new ArrayList<CommentUI>();
		
		if(post.getComments()==null){
			return new ArrayList<CommentUI>();
		}
		for (Comment comment : post.getComments()) {
			CommentUI cui = new CommentUI();
			cui.setCommentId(comment.getCommenttId());
			cui.setCommentData(comment.getComment());
			cui.setCommentDate(Helper.getLocalDate(comment.getCommentDate()));
			cui.setUserName(user.getFirstName() + user.getLastName());
			cui.setUserId(user.getUserId());
			cui.setPostId(post.getPostId());
			comments.add(cui);
		}

		return comments;
	}

	public CommentUI commentToCommentUI(Comment comment) throws SQLException{
		CommentUI cui = new CommentUI();
		
		User user = new User();
		user = user.getUser(comment.getUserId());
		
		cui.setCommentId(comment.getCommenttId());
		cui.setCommentData(comment.getComment());
		cui.setCommentDate(Helper.getLocalDate(comment.getCommentDate()));
		cui.setUserName(user.getFirstName() + user.getLastName());
		cui.setUserId(comment.getUserId());
		cui.setPostId(comment.getPostId());
		return cui;
	}
	
	public CommentUI addComment(CommentUI comment) throws SQLException{
		
		Comment c = new Comment(-1,comment.getCommentData(),null, comment.getPostId(),comment.getUserId());
		Comment res = c.addComment(c);
		return commentToCommentUI(res);
		
	}
	
	public boolean deleteComment(int commId) throws SQLException{
		Comment c = new Comment();
		boolean result = c.deleteComment(commId);
		return result;
	}

}
