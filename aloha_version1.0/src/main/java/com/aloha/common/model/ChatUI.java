package com.aloha.common.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.entities.Chat;
import com.aloha.common.entities.Post;
import com.aloha.common.entities.User;

public class ChatUI {
	private String chatContent;
	private String timestamp;
	private int userID1;
	private int userID2;
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
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
	
public ArrayList<ChatUI> getChatsForUser(User user1,User user2){
		
		ArrayList<ChatUI> userChats = new ArrayList<ChatUI>();
		Chat p = new Chat();
		ArrayList<Chat> chats;
		try {
			chats = p.getChatUser(user1.getUserId(),user2.getUserId());
			for (Chat chat : chats) {
				ChatUI cui = new ChatUI();
				cui.setUserID1(user1.getUserId());
				cui.setUserID2(user2.getUserId());
				cui.setTimestamp(Helper.getLocalDate(chat.getTimestamp()));
				cui.setChatContent(chat.getChatContent());
				userChats.add(cui);
			}
			
			return userChats;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
		
	}

}
