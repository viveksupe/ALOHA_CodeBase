package com.aloha.controller;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.Comment;
import com.aloha.common.entities.Post;
import com.aloha.common.entities.user.User;
import com.aloha.common.model.CommentUI;
import com.aloha.common.model.PostUI;


@Controller
public class PostController {
	
	@RequestMapping(value = "post/getPost", method=RequestMethod.POST)
	public @ResponseBody ArrayList<PostUI> displayPosts(@RequestParam("searchKey") String searchKey, Model model) {
		
		User u = new User();

		// fetching my first user from the db to start adding friends
		UserDal ud = new UserDal();
		try {
			u = ud.selectUserByPrimaryKey(1);
			PostUI pui = new PostUI();
			
			ArrayList<PostUI> posts = pui.getPostsForUser(u);
			return posts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<PostUI>();
		//posts.get(1).getPostData()
		//model.addAttribute("posts", posts);
		//PostListUI plui = new PostListUI(posts);
	}
	
	@RequestMapping("post")
	public String setup(Locale locale, Model model) throws SQLException{
		
		return "postContainer";
	}
	
	@RequestMapping(value="post/add", method=RequestMethod.POST)
	public @ResponseBody PostUI addPost(@RequestParam("postData") String post) throws SQLException{
		
		User u = new User();
		UserDal ud = new UserDal();
		u = ud.selectUserByPrimaryKey(1);
		
		PostUI pui = new PostUI();
		pui = pui.addPost(post, u);
		return pui;
	}
	
	@RequestMapping(value="comm/add", method=RequestMethod.POST)
	public void addComment(@RequestBody  CommentUI comm) throws SQLException{
		
		
		CommentUI cui = new CommentUI();
		cui.addComment(comm);
	}
	
	@RequestMapping(value="post/del", method=RequestMethod.POST)
	public @ResponseBody boolean deletePost(@RequestParam("postId") int postId){
		PostUI pui = new PostUI();
		boolean result = false;
		try {
			result = pui.deletePost(postId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
