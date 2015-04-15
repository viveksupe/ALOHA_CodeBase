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
	
	//region Getter Setter Method
	
		
	//endregion

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
				
				userPosts.add(pui);
			}
			
			return userPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
		
	}

	public boolean addPost(String post) throws SQLException{
		Post p = new Post(-1,post,null,null,null);
		int res = p.addPost(p, 1);
		if(res==1)
			return true;
		else return false;
	}
	
	
}
