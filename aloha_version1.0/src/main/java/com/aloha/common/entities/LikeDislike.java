package com.aloha.common.entities;

import java.util.ArrayList;

public class LikeDislike {
	private ArrayList<Like> likes;
	private ArrayList<Dislike> dislikes;
	/**
	 * @param likes
	 * @param dislikes
	 */
	public LikeDislike(ArrayList<Like> likes, ArrayList<Dislike> dislikes) {
		super();
		this.likes = likes;
		this.dislikes = dislikes;
	}
	
	public LikeDislike getPostLikeDislike(int postId){
		Like like = new Like();
		ArrayList<Like> likes = like.getLikes(postId);
		
		Dislike dislike = new Dislike();
		ArrayList<Dislike> dislikes = dislike.getDislikes(postId);
		
		return new LikeDislike(likes, dislikes);
	}
}
