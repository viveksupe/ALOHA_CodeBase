package com.aloha.controller;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.model.GetCommentUI;
import com.aloha.common.model.GetLikeStatusUI;
import com.aloha.common.model.GetPostUI;
import com.aloha.common.model.GetPostsUI;
import com.aloha.common.model.GetStatusUI;

@Controller
public class ProxyPostController implements IPostController {

	private PostController postController;

	@RequestMapping("post")
	public String setup(Locale locale, Model model) throws SQLException {

		return "postContainer";
	}

	@RequestMapping(value = "post/getAll", method = RequestMethod.POST)
	@Override
	public @ResponseBody GetPostsUI getAllPosts(
			@RequestParam("searchKey") String searchKey, Model model,
			HttpSession session) {
		// TODO Auto-generated method stub
		GetPostsUI gui = new GetPostsUI();
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.getAllPosts(searchKey, model, session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="post/add", method=RequestMethod.POST)
	public @ResponseBody GetPostUI addPost(@RequestParam("postData") String post, HttpSession session) {
		// TODO Auto-generated method stub
		GetPostUI gui = new GetPostUI();
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.addPost(post, session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="comm/add", method=RequestMethod.POST)
	public @ResponseBody GetCommentUI addComment(@RequestParam("commentData") String comm,@RequestParam("userId") int userId,@RequestParam("postId") int postId, HttpSession session) {
		// TODO Auto-generated method stub
		GetCommentUI gui = new GetCommentUI();
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.addComment(comm, userId, postId, session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="post/del", method=RequestMethod.POST)
	public @ResponseBody GetStatusUI deletePost(@RequestParam("postId") int postId, HttpSession session) {
		// TODO Auto-generated method stub
		GetStatusUI gui = new GetStatusUI();
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.deletePost(postId,session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="comm/del", method=RequestMethod.POST)
	public @ResponseBody GetStatusUI deleteComment(@RequestParam("commId") int commId, HttpSession session) {
		// TODO Auto-generated method stub
		GetStatusUI gui = new GetStatusUI(); 
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.deleteComment(commId,session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="post/like", method=RequestMethod.POST)
	public @ResponseBody GetLikeStatusUI likePost(@RequestParam("postId") int postId,@RequestParam("userId") int userId,@RequestParam("likeType") int likeType,HttpSession session) {
		// TODO Auto-generated method stub
		GetLikeStatusUI gui = new GetLikeStatusUI();
		
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.likePost(postId, userId, likeType, session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	@Override
	@RequestMapping(value="post/dislike", method=RequestMethod.POST)
	public @ResponseBody GetLikeStatusUI dislikePost(@RequestParam("postId") int postId,@RequestParam("userId") int userId, @RequestParam("likeType") int dislikeType, HttpSession session) {
		// TODO Auto-generated method stub
		GetLikeStatusUI gui = new GetLikeStatusUI();
		boolean isValid = validateSessionUser(session);
		if (isValid) {
			postController = new PostController();
			gui = postController.dislikePost(postId, userId, dislikeType, session);
			return gui;
		}
		gui.setStatusCode(-1);
		return gui;
	}

	public boolean validateSessionUser(HttpSession session) {
		if (null == session.getAttribute("sessionUser")) {
			return false;
		}
		return true;
	}
}
