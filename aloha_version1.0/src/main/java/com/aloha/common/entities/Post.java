package com.aloha.common.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.dal.PostDal;

public class Post {
	private int postId;
	private String post;
	private Date postDate;
	private ArrayList<Comment> comments;
	private LikeDislike likeStatistics;
	private boolean hasComments;
	private PostDal dal;

	/**
	 * @param postId
	 * @param post
	 * @param postDate
	 * @param comments
	 * @param likeStatistics
	 */
	public Post(int postId, String post, Date postDate, boolean hasComments,
			ArrayList<Comment> comments, LikeDislike likeStatistics) {
		super();
		this.postId = postId;
		this.post = post;
		this.postDate = postDate;
		this.comments = comments;
		this.likeStatistics = likeStatistics;
		this.hasComments = hasComments;
		this.dal = new PostDal();
	}

	// region gettersetter

	public Post() {
		// TODO Auto-generated constructor stub
		this.dal = new PostDal();
	}

	public int getPostId() {
		return postId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public LikeDislike getLikeStatistics() {
		return likeStatistics;
	}

	public void setLikeStatistics(LikeDislike likeStatistics) {
		this.likeStatistics = likeStatistics;
	}

	public boolean isHasComments() {
		return hasComments;
	}

	public void setHasComments(boolean hasComments) {
		this.hasComments = hasComments;
	}

	// endregion gettersetter

	public int addPost(Post post, int user_id) throws SQLException {

		int success = dal.insertPost(post, user_id);
		return success;
	}

	public boolean deletePost(int postId) throws SQLException {
		int result = dal.deletePost(postId);
		if (result == 1)
			return true;
		else
			return false;
	}

	public ArrayList<Post> getPostsUser(int userId) throws SQLException {
		ArrayList<Post> posts = new ArrayList<Post>();
		posts = dal.getPostsForUser(userId);
		
		for (Post post : posts) {
			if(post.hasComments = true)
			{
				Comment comm = new Comment();
				post.comments = comm.getCommentsPost(postId);
			}
			
		}
		return posts;
	}

	public ArrayList<Post> getPostsFriends(int userId) {
		return null;
	}

	public boolean updatePost(Post post) throws SQLException {
		int success = dal.updatePost(post);
		if (success == 1)
			return true;
		else
			return false;

	}

	public Post getPost(int postId) throws SQLException {
		Post post = dal.getPostByPrimaryKey(postId);
		if(post.hasComments = true)
		{
			Comment comm = new Comment();
			post.comments = comm.getCommentsPost(postId);
		}
		return post;
	}

	public ArrayList<Comment> getComments(int postId) {
		if (this.hasComments) {
		} else {
		}
		return null;
	}

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(new StringBuilder().append("[postid=")
				.append(postId).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[post=")
				.append(post).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[postdate=")
				.append(postDate).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[hasComm=")
				.append(hasComments).append("]\n").toString());
		
		return stringBuffer.toString();
	}

}
