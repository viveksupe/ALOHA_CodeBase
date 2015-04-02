package com.aloha.common.entities;

import java.util.ArrayList;

public class Friendship {
	
	private int friendshipId;
	private User user1;
	private User user2;
	private FriendshipStatus status;
	
	public Friendship (int fshipId, User u1, User u2, FriendshipStatus fStatus){
		this.friendshipId = fshipId;
		this.user1 = u1;
		this.user2 = u2;
		this.status = fStatus;
	}
	
	public boolean deleteFriendship(int fshipId){
		return false;
	}
	
	public boolean addFriendship(Friendship friendship){
		return false;
	}
	
	public ArrayList<User> getUserFriends(int userid){
		return null;
	}
	
	public boolean updateFriendship(Friendship friendship){
		return false;
	}

}
