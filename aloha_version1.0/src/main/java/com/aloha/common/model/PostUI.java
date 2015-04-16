package com.aloha.common.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.entities.*;
import com.aloha.common.entities.user.User;

public class PostUI {
	private String userName;
	private int userId;
	private String postDate;
	private String postData;
	private int postId;
	private ArrayList<CommentUI> comments;
	private LikeDislike likeStats;
	
	//region Getter Setter Method
	
	
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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public ArrayList<CommentUI> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentUI> comments) {
		this.comments = comments;
	}
	
	public LikeDislike getLikeStats() {
		return likeStats;
	}

	public void setLikeStats(LikeDislike likeStats) {
		this.likeStats = likeStats;
	}

	//endregion

	public ArrayList<PostUI> getPostsForUser(User user){
		
		ArrayList<PostUI> userPosts = new ArrayList<PostUI>();
		Post p = new Post();
		CommentUI comm = new CommentUI();
		ArrayList<Post> posts;
		try {
			posts = p.getPostsUser(user.getUserId());
			for (Post post : posts) {
				PostUI pui = new PostUI();
				pui.setUserName(user.getFirstName() + " " + user.getLastName());
				pui.setUserId(user.getUserId());
				pui.setPostDate(Helper.getLocalDate(post.getPostDate()));
				pui.setPostData(post.getPost());
				pui.setPostId(post.getPostId());
				pui.setComments(comm.getCommentsForPost(user, post));
				pui.setLikeStats(post.getLikeStatistics());
				userPosts.add(pui);
			}
			
			return userPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
		
	}

	
	public PostUI addPost(String post, User u) throws SQLException{
		Post p = new Post(-1,post,null,null,null);
		p = p.addPost(p, 1);
		return getPostUI(p, u);
	}
	
	public PostUI getPostUI(Post post, User u) throws SQLException{
		PostUI pui = new PostUI();
		CommentUI cui = new CommentUI();
		pui.setComments(cui.getCommentsForPost(u, post));
		pui.setLikeStats(post.getLikeStatistics());
		pui.setPostData(post.getPost());
		pui.setPostDate(Helper.getLocalDate(post.getPostDate()));
		pui.setPostId(post.getPostId());
		pui.setUserId(u.getUserId());
		pui.setUserName(u.getFirstName() + " " + u.getLastName());
		return pui;
	}
	
	
}
