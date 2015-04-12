package com.aloha.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
	
	@RequestMapping("post")
	public String displayPosts(Locale locale, Model model){
		return "post";
	}

}
