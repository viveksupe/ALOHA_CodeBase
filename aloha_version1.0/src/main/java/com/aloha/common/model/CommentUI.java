package com.aloha.common.model;

import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.entities.User;

public class CommentUI {
	private int CommentId;
	private String CommentData;
	private Date CommentDate;
	private String UserName;
	private int UserId;

	// region Getter Setter methods
	public int getCommentId() {
		return CommentId;
	}

	public void setCommentId(int commentId) {
		CommentId = commentId;
	}

	public String getCommentData() {
		return CommentData;
	}

	public void setCommentData(String commentData) {
		CommentData = commentData;
	}

	public Date getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(Date commentDate) {
		CommentDate = commentDate;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	// endregion

	public ArrayList<CommentUI> getCommentsForPost(User user, int postId) {

		ArrayList<CommentUI> comments = new ArrayList<CommentUI>();

		return comments;
	}
}
