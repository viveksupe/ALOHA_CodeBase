package com.aloha.controller;

import java.sql.SQLException;
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

	@RequestMapping("friends")
	public String index(Locale locale, Model model) throws SQLException {
		// creating user to start working with and finding friends.
		User u = new User();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(1);

		// fetching the friends of current user u
		FriendshipDal fd = new FriendshipDal();

		List<Friendship> flist = fd.selectFriendshipAllByUser(u.getUserId());
		model.addAttribute("friends", flist);
		
		List<User> ulist = ud.selectUserAll();
		
		model.addAttribute("users", flist);

		
		return "friends/friends";

	}
}
