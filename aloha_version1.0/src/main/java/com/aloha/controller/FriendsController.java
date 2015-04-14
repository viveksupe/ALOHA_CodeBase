package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.user.User;

/**
 * @author Milind FriendsController to handle the controls and flow of the
 *         friends module
 */
@Controller
@SessionAttributes("sessionUser")
public class FriendsController {

	@RequestMapping("friends/index")
	public String index(Locale locale, Model model){
		//fetching a user from database and putting in session. THis is a sample for development. 
		//Later to be removed.
		//Later the user will be fetched in the login page after user has successfully logged in.
		User testuser=null;
		UserDal ud = new UserDal();
		try {
			testuser = ud.selectUserByPrimaryKey(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		model.addAttribute("sessionUser",testuser);
		return "friends/index";
	}
	
	@RequestMapping("friends")
	public String displayFriends(Locale locale, Model model, HttpSession session) throws SQLException {
		// creating user to start working with and finding friends.
		User u = new User();
		Friendship f = new Friendship();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(6);
		ArrayList<User> ulist;
		//Getting the user from session and fetching its friends from DB
		if(null==session.getAttribute("sessionUser")){
			ulist = f.getUserFriends(u);			
		}else{
			ulist = f.getUserFriends((User)session.getAttribute("sessionUser"));
		}
		model.addAttribute("users", ulist);

/*		// fetching the friends of current user u
		FriendshipDal fd = new FriendshipDal();

		List<Friendship> flist = fd.selectFriendshipAllByUser(u.getUserId());
		model.addAttribute("friends", flist);
*/		
		
		
		return "friends/friends";

	}
}
