package com.aloha.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aloha.common.dao_manager.dal.ChatDal;
import com.aloha.common.entities.Chat;
import com.aloha.common.entities.ChatToken;
import com.aloha.common.entities.user.User;
import com.aloha.common.model.ChatUI;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*import com.aloha.chat.Data.MessageDecoder;
 import com.aloha.chat.Data.MessageEncoder;*/

/**
 * @ServerEndpoint gives the relative name for the end point This will be
 *                 accessed via ws://localhost:8080/EchoChamber/echo Where
 *                 "localhost" is the address of the host, "EchoChamber" is the
 *                 name of the package and "echo" is the address to access this
 *                 class from the server
 */
@SessionAttributes("sessionUser")
@Controller
@ServerEndpoint(value = "/websocket")
public class WebSocketChatController {

	// private static Set<Session> clients = Collections.synchronizedSet(new
	// HashSet<Session>());
	private static Map<Integer, Session> userIDToSessionMap = new ConcurrentHashMap<Integer, Session>();
	static int uID = 100;
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping("chat")
	public String home(Model model, HttpSession sesi) {
		// System.out.println(sesi.getAttribute("sessionUser"));
		logger.info("Welcome home! The client locale is {}.",
				sesi.getAttribute("sessionUser"));
		uID = ((User) sesi.getAttribute("sessionUser")).getUserId();

		// TODO get the user from onlline friends call to friends module.
		// and then return the users list to the jsp page.

		User u1 = new User();
		u1.setFirstName("Vivek");
		u1.setUserId(5);

		User u2 = new User();
		u2.setFirstName("Renuka");
		u2.setUserId(4);

		User u3 = new User();
		u3.setFirstName("Milind");
		u3.setUserId(8);

		ArrayList<User> onlineUsers = new ArrayList<User>();
		onlineUsers.add(u1);
		onlineUsers.add(u2);
		onlineUsers.add(u3);

		model.addAttribute("onlineUsers", onlineUsers);

		return "chat";
	}

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getId() + " has opened a connection");
		System.out.println(uID + ": =>" + session);
		userIDToSessionMap.put(uID, session);
		// System.out.println(sesi.getAttribute("sessionUser"));
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
	 * @throws SQLException 
	 * @throws JSONException
	 * 
	 * @throws ParseException
	 */
	@OnMessage
	public void onMessage(String message, Session session)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		

		// JSON Decoding
		ChatToken userChat;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		userChat = mapper.readValue(message, ChatToken.class);

		// Putting JSON to Local Variables For Manipulation
		String chatMessage = userChat.getChatMsg();
		int ToUserID = userChat.getToUserID();
		int FromUserID = userChat.getUserID();
		System.out.println(chatMessage + "+" + ToUserID + "+" + FromUserID);
		System.out.println(userIDToSessionMap.get(ToUserID));
		
		//Setting Chat Object to insert into database
		Chat cobj=new Chat();
		cobj.setUserID1(ToUserID);
		cobj.setUserID2(FromUserID);
		cobj.setChatContent(chatMessage);
		
		ChatUI cui=new ChatUI();
		cui.addChat(cobj);
		// Routing Starts
		if (userIDToSessionMap.containsKey(ToUserID)) {
			try {

				userIDToSessionMap.get(ToUserID).getBasicRemote()
						.sendText(message);
				System.out.println("Message sent to " + ToUserID + ": "
						+ message);
			} catch (IOException ex) {
				ex.printStackTrace();
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
		userIDToSessionMap.remove(uID);
		System.out.println("Socket Session " + session.getId() + " has ended");
		
	}
}
