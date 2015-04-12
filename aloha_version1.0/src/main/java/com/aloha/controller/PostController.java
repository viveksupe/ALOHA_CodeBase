package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.common.dao_manager.dal.*;
import com.aloha.common.entities.*;

@Controller
public class PostController {
	
	@RequestMapping("post")
	public String displayPosts(Locale locale, Model model) throws SQLException{
		
		User u = new User();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(1);
		
		PostDal pDal = new PostDal();
		ArrayList<Post> posts = pDal.getPostsForUser(u.getUserId());
		
		model.addAttribute("user", u);
		model.addAttribute("posts", posts);
		
		return "post";
	}

}