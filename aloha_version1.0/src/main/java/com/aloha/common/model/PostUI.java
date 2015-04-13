package com.aloha.common.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.entities.*;

public class PostUI {
	private String UserName;
	private int UserId;
	private String PostDate;
	private String PostData;
	private int PostId;
	private ArrayList<CommentUI> Comments;
	
	//region Getter Setter Method
	
	
	
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

	public String getPostDate() {
		return PostDate;
	}

	public void setPostDate(String postDate) {
		PostDate = postDate;
	}

	public String getPostData() {
		return PostData;
	}

	public void setPostData(String postData) {
		PostData = postData;
	}

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}

	public ArrayList<CommentUI> getComments() {
		return Comments;
	}

	public void setComments(ArrayList<CommentUI> comments) {
		Comments = comments;
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
				pui.setPostDate(Helper.getLocalDate(post.getPostDate().toString()));
				pui.setPostData(post.getPost());
				pui.setPostId(post.getPostId());
				pui.Comments = comm.getCommentsForPost(user, post);
				
				userPosts.add(pui);
			}
			
			return userPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
		
	}


	
	
	
}
