package com.aloha.common.entities;

import java.util.Date;

public class Chat {
	private int chatID;
	private String chatContent;
	private Date timestamp;
	private int userID1;
	private int userID2;
	
	public Chat(int chatID, String chatContent, Date timestamp, int userID1,
			int userID2) {
		this.chatID = chatID;
		this.chatContent = chatContent;
		this.timestamp = timestamp;
		this.userID1 = userID1;
		this.userID2 = userID2;
	}
	public Chat() {
		this.chatID = 0;
		this.chatContent = null;
		this.timestamp = null;
		this.userID1 = 0;
		this.userID2 = 0;
	}
	public int getChatID() {
		return chatID;
	}
	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getUserID1() {
		return userID1;
	}
	public void setUserID1(int userID1) {
		this.userID1 = userID1;
	}
	public int getUserID2() {
		return userID2;
	}
	public void setUserID2(int userID2) {
		this.userID2 = userID2;
	}
	
	
}
