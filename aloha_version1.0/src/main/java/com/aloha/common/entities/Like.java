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

	public Like() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Like> getLikes(int postId){return null;}
	
	public boolean Like(int postId){return false;}
	
	
	public boolean UnLike(int postId){return false;}
	
	
	
	
	

}
