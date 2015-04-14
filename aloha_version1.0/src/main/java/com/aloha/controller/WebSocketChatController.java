package com.aloha.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.aloha.common.entities.ChatToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

/*import com.aloha.chat.Data.MessageDecoder;
 import com.aloha.chat.Data.MessageEncoder;*/

/**
 * @ServerEndpoint gives the relative name for the end point This will be
 *                 accessed via ws://localhost:8080/EchoChamber/echo Where
 *                 "localhost" is the address of the host, "EchoChamber" is the
 *                 name of the package and "echo" is the address to access this
 *                 class from the server
 */
@ServerEndpoint(value = "/websocket")
public class WebSocketChatController {

	private static Set<Session> clients = Collections
			.synchronizedSet(new HashSet<Session>());
	private Map<Integer, Session> userIDToSessionMap = new ConcurrentHashMap<Integer, Session>();

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + " has opened a connection");
		// System.out.println(session.);
		clients.add(session);

		/*
		 * try { session.getBasicRemote().sendText("Connection Established"); }
		 * catch (IOException ex) { ex.printStackTrace(); }
		 */
	}

	/**
	 * When a user sends a message to the server, this method will intercept the
	 * message and allow us to react to it. For now the message is read as a
	 * String.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws JSONException
	 * 
	 * @throws ParseException
	 */
	@OnMessage
	public void onMessage(String message, Session session)
			throws JsonParseException, JsonMappingException, IOException {

		ChatToken userChat;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		// IMPORTANT
		// without this option set adding new fields breaks old code
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		userChat = mapper.readValue(message, ChatToken.class);
		System.out.print("Message: " + userChat.getChatMsg());
		System.out.print("ToUserID: " + userChat.getToUserID());
		System.out.println("FromUserID: " + userChat.getUserID());
		Integer intKey = null;
		for (Integer intr : userIDToSessionMap.keySet()) {
			if(intr.intValue()==userChat.getUserID()){
				intKey=intr;
			}
		}
		if(intKey==null){
			intKey=new Integer(userChat.getUserID());
			userIDToSessionMap.put(intKey,session);
		}
		for (Integer intr : userIDToSessionMap.keySet()) {
			if(intr.intValue()==userChat.getToUserID()){
				intKey=intr;
			}
		}
		if(intKey==null){
			return;
		}
			if (userIDToSessionMap.containsKey(intKey)) {
				// Register the nickname with the
				//userIDToSessionMap.put(new Integer(userChat.getUserID()),session);
			
				try {

					userIDToSessionMap.get(intKey).getBasicRemote().sendText(message);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				/*if(userIDToSessionMap.containsKey(userChat.getToUserID()))	
				{
					System.out.println("Testing");
				}
				try {

					client.getBasicRemote().sendText(message);
				} catch (IOException ex) {
					ex.printStackTrace();
				}*/
				}
			} 
		

	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose(Session session) {
		userIDToSessionMap.remove(session);
		System.out.println("Socket Session " + session.getId() + " has ended");
		clients.remove(session);
	}
}
/*
 * @ServerEndpoint("/websocket") public class WebSocketTest {
 * 
 * private static Set<Session> clients = Collections.synchronizedSet(new
 * HashSet<Session>());
 * 
 * @OnMessage public void onMessage(String message, Session session) throws
 * IOException {
 * 
 * synchronized(clients){ // Iterate over the connected sessions // and
 * broadcast the received message for(Session client : clients){ if
 * (!client.equals(session)){ client.getBasicRemote().sendText(message); } } }
 * 
 * }
 * 
 * @OnOpen public void onOpen (Session session) { // Add session to the
 * connected sessions set clients.add(session); Iterator<Session> iter =
 * clients.iterator(); while (iter.hasNext()) { System.out.println(iter.next());
 * } }
 * 
 * @OnClose public void onClose (Session session) { // Remove session from the
 * connected sessions set clients.remove(session); } }
 */