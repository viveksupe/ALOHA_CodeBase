package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.user.User;

/**
 * @author Milind FriendsController to handle the controls and flow of the
 *         friends module
 */
@Controller
// @SessionAttributes("sessionUser")
public class FriendsController {
	private static final Logger logger = LoggerFactory
			.getLogger(FriendsController.class);

	@RequestMapping("friends/index")
	public String index(Locale locale, Model model, HttpSession session) {
		// fetching a user from database and putting in session. THis is a
		// sample for development.
		// Later to be removed.
		// Later the user will be fetched in the login page after user has
		// successfully logged in.

		logger.info("Entered friend index page INFO");
		logger.debug("Entered friend index page DEBUG");
		logger.error("Entered friend index page ERROR");

		// Now is being fetched from the current logged in session user.
		User testUser = null;
		// testuser = ud.selectUserByPrimaryKey(4);
		testUser = (User) session.getAttribute("sessionUser");

		// model.addAttribute("sessionUser",testuser);
		return "friends/index";
	}

	@RequestMapping("friends")
	public String displayFriends(Locale locale, Model model, HttpSession session)
			throws SQLException {
		// creating user to start working with and finding friends.
		User u = new User();
		Friendship f = new Friendship();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(6);
		ArrayList<User> ulist;
		// Getting the user from session and fetching its friends from DB
		if (null == session.getAttribute("sessionUser")) {
			ulist = f.getUserFriends(u);
		} else {
			ulist = f
					.getUserFriends((User) session.getAttribute("sessionUser"));
		}
		model.addAttribute("users", ulist);

		/*
		 * // fetching the friends of current user u FriendshipDal fd = new
		 * FriendshipDal();
		 * 
		 * List<Friendship> flist = fd.selectFriendshipAllByUser(u.getUserId());
		 * model.addAttribute("friends", flist);
		 */

		return "friends/friends";

	}

	@RequestMapping(value = "friends/add", method = RequestMethod.POST)
	public @ResponseBody int addFriend(@RequestParam("userIdToAdd") int userId,
			Model model, HttpSession session) {
		logger.info("Entered addFriend POST");
		Friendship f = new Friendship();
		int requestorId = -1;
		User requestor = (User) session.getAttribute("sessionUser");
		if (requestor != null) {
			requestorId = requestor.getUserId();
		}
		int requesteeId = userId;
		if(f.addFriendship(requestorId, requesteeId))
		return 1;
		return -1;
	}

}
