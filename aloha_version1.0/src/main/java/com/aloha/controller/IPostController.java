package com.aloha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.user.User;
import com.aloha.common.model.CommentUI;
import com.aloha.common.model.DislikeUI;
import com.aloha.common.model.LikeUI;
import com.aloha.common.model.PostUI;

public interface IPostController {

	
	public ArrayList<PostUI> getAllPosts(String searchKey, Model model, HttpSession session);
	
	
	
	public PostUI addPost(String post, HttpSession session);
	
	public CommentUI addComment(String comm, int userId, int postId, HttpSession session) ;
	
	public boolean deletePost( int postId, HttpSession session);
	
	public boolean deleteComment(int commId, HttpSession session);
	
	public int likePost( int postId, int userId, int likeType, HttpSession session );

	public int dislikePost( int postId, int userId,  int dislikeType, HttpSession session);
}
