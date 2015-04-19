package com.aloha.common.entities;

import java.sql.SQLException;
import java.util.*;

import com.aloha.common.dao_manager.dal.LikeDislikeDal;

public class Like {
	private int likeId;
	//private LikeType type;
	private int userId;
	private int postId;
	private LikeDislikeDal lDal;

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
		//this.type = type;
		this.userId = userId;
		this.postId = postId;
		lDal = new LikeDislikeDal();
	}
	
	public Like(int userId, int postId) {
		super();
		this.userId = userId;
		this.postId = postId;
		lDal = new LikeDislikeDal();
	}

	// region

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
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

	// endregion

	public Like() {
		// TODO Auto-generated constructor stub
	}



	public int likePost(Like like) throws SQLException {
		int likeId = like.getLikeId();
		int result = -1;
		if (likeId == -1)
			result = lDal.insertLike(like);
		else
			result = lDal.updateLike(1, likeId);
		return result;
	}

	public int unLike(Like like) throws SQLException {
		return lDal.updateLike(0, like.getLikeId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Like [likeId=" + likeId + ", userId="
				+ userId + ", postId=" + postId + ", lDal=" + lDal + "]";
	}

	
}
