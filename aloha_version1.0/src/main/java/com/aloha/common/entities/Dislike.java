package com.aloha.common.entities;

import java.util.ArrayList;

public class Dislike {
	private int dislikeId;
	private LikeType type;
	private int userId;
	private int postId;
	
	//region
	
	public int getDislikeId() {
		return dislikeId;
	}

	public void setDislikeId(int dislikeId) {
		this.dislikeId = dislikeId;
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

	/**
	 * @param dislikeId
	 * @param type
	 * @param userId
	 * @param postId
	 * @param likeCount
	 * @param dislikeCount
	 */
	public Dislike(int dislikeId, LikeType type, int userId, int postId) {
		super();
		this.dislikeId = dislikeId;
		this.type = type;
		this.userId = userId;
		this.postId = postId;
	}

	public Dislike() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Dislike> getDislikes(int postId){return null;}
	
	public boolean dislike(int postId){return false;}
	
	
	public boolean unDislike(int postId){return false;}

}
