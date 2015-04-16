package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Dislike;
import com.aloha.common.entities.Like;
import com.aloha.common.entities.LikeDislike;
import com.aloha.common.entities.LikeType;
import com.aloha.common.entities.Post;

public class LikeDislikeDal {
	
	Connection con = null;
	// write the queries for Post table
	private String SELECT;
	private String INSERT;
	private String UNLIKE;
	private String UNDISLIKE;

	public LikeDislikeDal() {
		SELECT = "SELECT likedislike.like_id, likedislike.like_type, likedislike.user_id, likedislike.post_id FROM likedislike";
		INSERT = "INSERT INTO likedislike ( like_type, user_id, post_id) VALUES ( ?, ?, ?);";
		UNLIKE = "UPDATE likedislike SET like_type = ? WHERE like_id ?;";

		con = DatabaseHandlerSingleton.getDBConnection();
		
	}
	
	public LikeDislike GetPostLikeDislike(int postid) throws SQLException {
		
		ArrayList<Like> likes = new ArrayList<Like>();
		ArrayList<Dislike> dislikes = new ArrayList<Dislike>();
		
		
		String getPostById = SELECT + " where likedislike.post_id=? and  likedislike.like_type in (1,2);";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(getPostById);
			ps.setInt(1, postid);
			rSet = ps.executeQuery();

			while (rSet.next()) {
				
				int likeType = rSet.getInt("like_type");
				
				if(likeType == 1){
					Like like = new Like();
					like.setLikeId(rSet.getInt("like_id"));
					like.setPostId(rSet.getInt("post_id"));
					like.setUserId(rSet.getInt("user_id"));
					like.setType(LikeType.Like);
					likes.add(like);
				}
				else if(likeType==2){
					Dislike like = new Dislike();
				like.setDislikeId(rSet.getInt("like_id"));
				like.setPostId(rSet.getInt("post_id"));
				like.setUserId(rSet.getInt("user_id"));
				like.setType(LikeType.Dislike);
				dislikes.add(like);
			}
				}
			return new LikeDislike(likes,dislikes);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rSet != null)
				rSet.close();
			if (ps != null)
				ps.close();
			con.close();
		}
	}


}
