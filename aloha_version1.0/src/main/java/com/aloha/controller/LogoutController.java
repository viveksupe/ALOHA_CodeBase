package com.aloha.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionUser")
public class LogoutController {
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String sign_up(Model model, HttpSession session) {
		model.addAttribute("sessionUser",null);
		return "home";
	}
}
