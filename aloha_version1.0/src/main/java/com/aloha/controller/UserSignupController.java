package com.aloha.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserSignupController {
private static final Logger logger = LoggerFactory.getLogger(UserSignupController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "login/sign_up", method = RequestMethod.GET)
	public String sign_up(Locale locale, Model model) {
		logger.info("Welcome Sign_up! The client locale is {}.", locale);
		
		return "sign_up";
	}
}
