package com.aloha.common.entities;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
	private int commenttId;
	private String comment; 
	private Date commentDate;
	private int postId;
	/**
	 * @param commenttId
	 * @param comment
	 * @param commentDate
	 */
	public Comment(int commenttId, String comment, Date commentDate, int postId) {
		super();
		this.commenttId = commenttId;
		this.comment = comment;
		this.commentDate = commentDate;
		this.postId = postId;
	}
	
	public Comment addComment(Comment comment){return null;}
	
	public boolean deleteComment(int commId){return false;}
	
	public ArrayList<Comment> getCommentsPost(int postId){return null;}
	
	public Comment updateComment(Comment comment){return null;}
	
	public Comment getComment(int commId){return null;}
	
	
}
