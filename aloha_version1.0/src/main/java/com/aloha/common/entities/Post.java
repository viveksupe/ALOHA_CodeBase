package com.aloha.common.entities;

import java.util.ArrayList;
import java.util.Date;

public class Post {
	private int postId;
	private String post; 
	private Date postDate;
	private ArrayList<Comment> comments;
	private LikeDislike likeStatistics;
	
	
	/**
	 * @param postId
	 * @param post
	 * @param postDate
	 * @param comments
	 * @param likeStatistics
	 */
	public Post(int postId, String post, Date postDate,
			ArrayList<Comment> comments, LikeDislike likeStatistics) {
		super();
		this.postId = postId;
		this.post = post;
		this.postDate = postDate;
		this.comments = comments;
		this.likeStatistics = likeStatistics;
	}

	public Post addPost(Post post){return null;}
	
	public boolean deletePost(int postId){return false;}
	
	public ArrayList<Post> getPostsUser(int userId){return null;}
	
	public ArrayList<Post> getPostsFriends(int userId){return null;}
	
	public Post updatePost(Post post){return null;}
	
	public Post getPost(int postId){return null;}
	
	public boolean hasComments(int postId){return false;}
	
	public ArrayList<Comment> getComments(int postId){
		//delegate to comment class
		return null;}
	

}
