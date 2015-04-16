package com.aloha.common.entities;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aloha.common.dao_manager.dal.LikeDislikeDal;

public class Dislike {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dislike [dislikeId=" + dislikeId + ", type=" + type
				+ ", userId=" + userId + ", postId=" + postId + ", lDal="
				+ lDal + "]";
	}


	private int dislikeId;
	private LikeType type;
	private int userId;
	private int postId;
	private LikeDislikeDal lDal;
	
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


	public int dislike(Dislike dislike) throws SQLException{
		int dlikeId = dislike.getDislikeId();
		int result = -1;
		if (dlikeId == -1)
			result = lDal.insertDislike(dislike);
		else
			result = lDal.updateLike(2, dlikeId);
		return result;

	}
	
	
	public int unDislike(Dislike dislike) throws SQLException{
		return lDal.updateLike(0, dislike.getDislikeId());
	}

}
