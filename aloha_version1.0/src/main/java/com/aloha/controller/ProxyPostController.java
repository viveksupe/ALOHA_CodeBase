package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.model.CommentUI;
import com.aloha.common.model.GetPostsUI;
import com.aloha.common.model.PostUI;
import com.aloha.common.model.ResponseUI;
import com.aloha.common.model.UserUI;

@Controller
public class ProxyPostController implements IPostController {

	private PostController postController;

	@RequestMapping("post")
	public String setup(Locale locale, Model model) throws SQLException{
		
		return "postContainer";
	}
	
	@RequestMapping(value = "post/getAll", method=RequestMethod.POST)
	@Override
	public @ResponseBody GetPostsUI getAllPosts(@RequestParam("searchKey") String searchKey, Model model,
			HttpSession session) {
		// TODO Auto-generated method stub
		GetPostsUI gui = new GetPostsUI();
		boolean isValid = validateSessionUser(session);
		if(isValid){
		postController = new PostController();
		ArrayList<PostUI > posts  = postController.getAllPosts(searchKey, model,session);
		gui.setPosts(posts);
		return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}


	@Override
	public PostUI addPost(String post, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentUI addComment(String comm, int userId, int postId,
			HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePost(int postId, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(int commId, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int likePost(int postId, int userId, int likeType,
			HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dislikePost(int postId, int userId, int dislikeType,
			HttpSession session) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean validateSessionUser(HttpSession session){
		if(null==session.getAttribute("sessionUser")){
			return false;
		}
		return true;
	}
}
