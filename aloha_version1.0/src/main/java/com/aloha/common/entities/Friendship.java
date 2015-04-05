package com.aloha.common.entities;

import java.util.ArrayList;

/**
 * @author Milind
 *
 */
public class Friendship {
	
	private int friendshipId;
	private User user1;
	private User user2;
	private FriendshipStatus status;
	private User blocked_by;
	private User req_sent_by;
	
	/**
	 * 
	 */
	public Friendship(){
	}
	
	/**
	 * @param fshipId
	 * @param u1
	 * @param u2
	 * @param fStatus
	 */
	public Friendship (int fshipId, User u1, User u2, FriendshipStatus fStatus){
		this.friendshipId = fshipId;
		this.user1 = u1;
		this.user2 = u2;
		this.status = fStatus;
	}
	
	/**
	 * @return the friendshipId
	 */
	public int getFriendshipId() {
		return friendshipId;
	}

	/**
	 * @param friendshipId the friendshipId to set
	 */
	public void setFriendshipId(int friendshipId) {
		this.friendshipId = friendshipId;
	}

	/**
	 * @return the user1
	 */
	public User getUser1() {
		return user1;
	}

	/**
	 * @param user1 the user1 to set
	 */
	public void setUser1(User user1) {
		this.user1 = user1;
	}

	/**
	 * @return the user2
	 */
	public User getUser2() {
		return user2;
	}

	/**
	 * @param user2 the user2 to set
	 */
	public void setUser2(User user2) {
		this.user2 = user2;
	}

	/**
	 * @return the status
	 */
	public FriendshipStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}

	/**
	 * @return the blocked_by
	 */
	public User getBlocked_by() {
		return blocked_by;
	}

	/**
	 * @param blocked_by the blocked_by to set
	 */
	public void setBlocked_by(User blocked_by) {
		this.blocked_by = blocked_by;
	}

	/**
	 * @return the req_sent_by
	 */
	public User getReq_sent_by() {
		return req_sent_by;
	}

	/**
	 * @param req_sent_by the req_sent_by to set
	 */
	public void setReq_sent_by(User req_sent_by) {
		this.req_sent_by = req_sent_by;
	}

	/**
	 * @param fshipId
	 * @return
	 */
	public boolean deleteFriendship(int fshipId){
		return false;
	}
	
	/**
	 * @param friendship
	 * @return
	 */
	public boolean addFriendship(Friendship friendship){
		return false;
	}
	
	/**
	 * @param userid
	 * @return
	 */
	public ArrayList<User> getUserFriends(int userid){
		return null;
	}
	
	/**
	 * @param friendship
	 * @return
	 */
	public boolean updateFriendship(Friendship friendship){
		return false;
	}

	
}
