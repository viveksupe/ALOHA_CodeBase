package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.user.User;

@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserSignupController.class);

	@RequestMapping(value = "search/users", method = RequestMethod.GET)
	public String searchUsers(Locale locale, Model model) {
		logger.info("Entered Search Users GET");
		return "search/users";
	}

	@RequestMapping(value = "search/users", method = RequestMethod.POST)
	public String searchUsers(@RequestParam("searchKey") String searchKey,
			Model model) {
		logger.info("Entered Search Users POST method");
		// TODO write a query in USERDAL and use it here. to return the users
		// list.
		ArrayList<User> ulist=null;
		UserDal ud = new UserDal();
		try {
			ulist= ud.selectUsersByName(searchKey);
			model.addAttribute("users",ulist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO then create a global user view which will also fetch the users
		// from the DB and their friendship from the DB. If friendship exists
		// then add friend button should not be shown.
		return "search/users";
	}

}