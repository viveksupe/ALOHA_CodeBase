package com.aloha.common;


import java.sql.Date;
import java.util.ArrayList;

import com.aloha.common.dao_manager.dal.PostDal;
import com.aloha.common.entities.Post;

public class TestPostDBHandler {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addPostTest();
		//selectPost();
		//updatePost();
		//getUserPostsTest();
		//deletePost();
	}
	
	public static void addPostTest()
	{
		PostDal pDal = new PostDal();
		
		try{
		Post post = new Post(-1, "This is my new post", new Date(new java.util.Date().getTime()), null, null);
		Post p1 = pDal.insertPost(post, 4);
		if(p1 != null)
			System.out.println(p1.toString());
		else System.out.println("P1 null !!");
		
		post = new Post(-1, "This is my test post", new Date(new java.util.Date().getTime()), null, null);
		Post p2 = pDal.insertPost(post, 5);
		if(p2 != null)
			System.out.println(p2.toString());
		else System.out.println("p2 null");
		
		post = new Post(-1, "This is my third post", new Date(new java.util.Date().getTime()), null, null);
		Post p3 = pDal.insertPost(post, 4);
		if(p3 != null)
			System.out.println(p3.toString());
		else System.out.println("p3 null !!");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void selectPostTest()
	{
		PostDal pDal = new PostDal();
		
		try{
			Post post = pDal.getPostByPrimaryKey(2);
			System.out.println( post.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void updatePostTest(){
		PostDal pDal = new PostDal();
		
		try {
			Post post = new Post(2,"This is update post",new Date(new java.util.Date().getTime()),null,null);
			int success = pDal.updatePost(post);
			if(success == 1)
				System.out.println("Updated");
			else System.out.println("Update failed !!");
			
			post = pDal.getPostByPrimaryKey(2);
			System.out.println( post.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getUserPostsTest(){
		PostDal pDal = new PostDal();
		
		try {
			ArrayList<Post> posts = pDal.getPostsForUser(1);
			
			if(posts != null){
				for (Post post : posts) {
					System.out.println( post.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePost(){
PostDal pDal = new PostDal();
		
		try {
			int success = pDal.deletePost(3);
			
			if(success == 1){
				System.out.println("Deleted !!");
			}
			else System.out.println("Delete failed !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
