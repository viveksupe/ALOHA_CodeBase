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
	private Date commentDate;
	private String userName;
	private int userId;
	private int postId;

	// region Getter Setter method
	
	// endregion

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

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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
			cui.setCommentDate(comment.getCommentDate());
			cui.setUserName(user.getFirstName() + user.getLastName());
			cui.setUserId(user.getUserId());
			cui.setPostId(post.getPostId());
			comments.add(cui);
		}

		return comments;
	}

	public boolean addComment(CommentUI comment) throws SQLException{
		
		Comment c = new Comment(-1,comment.getCommentData(),null, comment.getPostId(),comment.getUserId());
		int res = c.addComment(c);
		if(res==1)
			return true;
		else return false;
	}

}
