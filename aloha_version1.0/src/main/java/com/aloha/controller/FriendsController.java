package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.common.dao_manager.dal.FriendshipDal;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.User;

/**
 * @author Milind FriendsController to handle the controls and flow of the
 *         friends module
 */
@Controller
public class FriendsController {

	@RequestMapping("friends/index")
	public String index(Locale locale, Model model){
		return "friends/index";
	}
	
	@RequestMapping("friends")
	public String displayFriends(Locale locale, Model model) throws SQLException {
		// creating user to start working with and finding friends.
		User u = new User();
		Friendship f = new Friendship();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(6);
		ArrayList<User> ulist = f.getUserFriends(u);
		
		model.addAttribute("users", ulist);

/*		// fetching the friends of current user u
		FriendshipDal fd = new FriendshipDal();

		List<Friendship> flist = fd.selectFriendshipAllByUser(u.getUserId());
		model.addAttribute("friends", flist);
*/		

		
		return "friends/friends";

	}
}
