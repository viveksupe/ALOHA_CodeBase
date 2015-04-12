package com.aloha.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + " has opened a connection");
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
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * 
	 * @throws ParseException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws JsonParseException, JsonMappingException, IOException  {
		ChatToken userChat;
	     ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	     // IMPORTANT
	     // without this option set adding new fields breaks old code
	     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	     userChat = mapper.readValue(message, ChatToken.class);
	     System.out.println(userChat.getChatMsg());
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					try {
						client.getBasicRemote().sendText(message);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId() + " has ended");
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