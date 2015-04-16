package com.aloha.common.entities;

import java.util.*;

public class Like {
	private int likeId;
	private LikeType type;
	private int userId;
	private int postId;
	
	/**
	 * @param likeId
	 * @param type
	 * @param userId
	 * @param postId
	 * @param likeCount
	 * @param dislikeCount
	 */
	public Like(int likeId, LikeType type, int userId, int postId) {
		super();
		this.likeId = likeId;
		this.type = type;
		this.userId = userId;
		this.postId = postId;
	}
	
	//region

	public int getLikeId() {
		return likeId;
	}



	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}



	public LikeType getType() {
		return type;
	}



	public void setType(LikeType type) {
		this.type = type;
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

	//endregion


	public Like() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Like> getLikes(int postId){return null;}
	
	public boolean Like(int postId){return false;}
	
	
	public boolean UnLike(int postId){return false;}
	
	
	
	
	

}
