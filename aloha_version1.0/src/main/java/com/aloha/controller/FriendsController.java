package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.FriendshipStatus;
import com.aloha.common.entities.user.User;
import com.aloha.common.model.UserUI;
import com.aloha.common.util.CommonUtils;

/**
 * @author Milind FriendsController to handle the controls and flow of the
 *         friends module
 */
@Controller
// @SessionAttributes("sessionUser")
public class FriendsController {

	private static final Logger logger = LoggerFactory
			.getLogger(FriendsController.class);
	CommonUtils commonUtils = new CommonUtils();
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Index page of friends for testing purposes
	 * 
	 * @param locale
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("friends/index")
	public String index(Locale locale, Model model, HttpSession session) {
		// Now is being fetched from the current logged in session user.
		UserUI testUser = null;
		// testuser = ud.selectUserByPrimaryKey(4);
		testUser = (UserUI) session.getAttribute("sessionUser");
		return "friends/index";
	}

	/**
	 * 
	 * friends listing Page
	 * 
	 * @param locale
	 * @param model
	 * @param session
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("friends")
	public String displayFriends(Locale locale, Model model, HttpSession session)
			throws SQLException {
		User u = new User();
		Friendship f = new Friendship();

		ArrayList<User> ulist;
		if (null == session.getAttribute("sessionUser")) {
			return "redirect:" + "login";
		} else {
			UserUI sessionUserUI = (UserUI) session.getAttribute("sessionUser");
			ulist = f.getUserFriends(commonUtils
					.convertUserUIToUser(sessionUserUI));
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

	/**
	 * 
	 * Function to send friend request to an existing aloha user
	 * 
	 * @param userId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "friends/add", method = RequestMethod.POST)
	public @ResponseBody int addFriend(@RequestParam("userIdToAdd") int userId,
			Model model, HttpSession session) {
		logger.info("Entered addFriend POST");
		Friendship f = new Friendship();
		int requestorId = -1;
		UserUI requestor = (UserUI) session.getAttribute("sessionUser");
		if (requestor != null) {
			requestorId = requestor.getUserId();
		} else {
			// since user is not in session do nothing. Simply reject the
			// friendship request.
			return -1;
		}
		int requesteeId = userId;
		if (f.addFriendship(requestorId, requesteeId))
			return 1;
		return -1;
	}

	/**
	 * Function to accept pending friend request
	 * 
	 * @param userId
	 * @param acceptorId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "friends/accept", method = RequestMethod.POST)
	public @ResponseBody int acceptFriend(
			@RequestParam("userIdToAccept") int userId,
			@RequestParam("acceptor") int acceptorId, Model model,
			HttpSession session) {
		logger.info("Entered addFriend POST");
		Friendship f = new Friendship();
		f = f.getExistingFriendship(userId, acceptorId);
		f.setStatus(FriendshipStatus.Friends);
		if (f.updateFriendship(f))
			return 1;
		return -1;
	}

	/**
	 * 
	 * Function to remove friend from friendslist
	 * 
	 * @param fId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "friends/remove", method = RequestMethod.POST)
	public @ResponseBody int removeFriend(
			@RequestParam("friendshipIdToRemove") int fId, Model model,
			HttpSession session) {
		logger.info("Entered addFriend POST");
		Friendship f = new Friendship();
		if (f.deleteFriendship(fId))
			return 1;
		return -1;
	}

	@RequestMapping(value = "friends/invite", method = RequestMethod.GET)
	public String inviteFriend(Locale locale, Model model, HttpSession session) {
		UserUI u = new UserUI();
		if (null == session.getAttribute("sessionUser")) {
			return "redirect:" + "../login";
		} else {
			u = (UserUI) session.getAttribute("sessionUser");
		}
		model.addAttribute("user", u);

		return "friends/invite";
	}

	@RequestMapping(value = "friends/invite", method = RequestMethod.POST)
	public @ResponseBody boolean inviteFriend(
			@RequestParam("email") String email, Model model,
			HttpSession session) {
		UserUI u = new UserUI();
		if (null == session.getAttribute("sessionUser")) {
			return false;
		} else {

			u = (UserUI) session.getAttribute("sessionUser");
			String mailContent = "Hey Checkout Aloha - A brand new social networking portal \n Click Here to Go to Aloha.com";
			if (email.contains(",")) {
				String[] emails = email.split(",");
				for (String eachEmailAddr : emails) {
					eachEmailAddr.trim();
					commonUtils.mailSendUtil(mailSender, eachEmailAddr,
							u.getEmail(), mailContent);
				}
			} else {
				commonUtils.mailSendUtil(mailSender, email, u.getEmail(),
						mailContent);
			}
			return true;
		}
	}
}
