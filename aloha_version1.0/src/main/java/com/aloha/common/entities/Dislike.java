package com.aloha.common.entities;

import java.util.ArrayList;

public class Dislike {
	private int dislikeId;
	private LikeType type;
	private int userId;
	private int postId;
	
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

	public ArrayList<Dislike> getDislikes(int postId){return null;}
	
	public boolean dislike(int postId){return false;}
	
	
	public boolean unDislike(int postId){return false;}

}