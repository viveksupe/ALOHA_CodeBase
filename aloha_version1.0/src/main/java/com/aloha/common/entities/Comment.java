package com.aloha.common.entities;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
	private int commenttId;
	private String comment; 
	private Date commentDate;
	private int postId;
	private int userId;
	
	//region Getter Setter methods
	public int getCommenttId() {
		return commenttId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	//endregion

	/**
	 * @param commenttId
	 * @param comment
	 * @param commentDate
	 */
	public Comment(int commenttId, String comment, Date commentDate, int postId, int userId) {
		super();
		this.commenttId = commenttId;
		this.comment = comment;
		this.commentDate = commentDate;
		this.postId = postId;
		this.userId = userId;
	}
	
	public Comment addComment(Comment comment){return null;}
	
	public boolean deleteComment(int commId){return false;}
	
	public ArrayList<Comment> getCommentsPost(int postId){return null;}
	
	public Comment updateComment(Comment comment){return null;}
	
	public Comment getComment(int commId){return null;}
	
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(new StringBuilder().append("[commenttId=")
				.append(commenttId).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[comment=")
				.append(comment).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[commentDate=")
				.append(commentDate).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[postId=")
				.append(postId).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[userId=")
				.append(userId).append("]\n").toString());
		
		return stringBuffer.toString();
	}
}
