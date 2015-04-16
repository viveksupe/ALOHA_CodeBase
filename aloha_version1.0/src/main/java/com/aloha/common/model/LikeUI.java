package com.aloha.common.model;

import java.util.ArrayList;

import com.aloha.common.entities.Like;

public class LikeUI {
	private int likeId;
	private int postId;
	private int userId;

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<LikeUI> getLikesData(ArrayList<Like> likes) {

		ArrayList<LikeUI> likeData = new ArrayList<LikeUI>();
		for (Like like : likes) {
			LikeUI lui = new LikeUI();
			lui.setLikeId(like.getLikeId());
			lui.setPostId(like.getPostId());
			lui.setUserId(like.getUserId());
			likeData.add(lui);
		}
		return likeData;
	}
}
