package com.aloha.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(UserSignupController.class);

	@RequestMapping(value = "search/users", method = RequestMethod.GET)
	public String searchUsers(Locale locale, Model model) {
		logger.info("Entered Search Users GET");	
		return "search/users";
	}
	
}
