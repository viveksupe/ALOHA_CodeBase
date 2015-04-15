package com.aloha.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.dao_manager.dal.FriendshipDal;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.Friendship;
import com.aloha.common.entities.FriendshipStatus;
import com.aloha.common.entities.user.User;

public class TestFriendshipDBHandler {
	static final Logger logger = Logger.getLogger(TestFriendshipDBHandler.class
			.toString());
	UserDal ud;
	FriendshipDal fd;

	public TestFriendshipDBHandler() {
		ud = new UserDal();
		fd = new FriendshipDal();
	}

	public static void main(String[] args) {
		Connection con = DatabaseHandlerSingleton.getDBConnection();

		try {
			TestFriendshipDBHandler test = new TestFriendshipDBHandler();
			//test.insertFriendship();
			//test.selectFriendship(3);
			//test.updateFriendship(3);
			//test.selectFriendship(1);
			//test.deleteFriendship(3);
			test.selectAllFriendships();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void selectAllFriendships() throws SQLException {

		ArrayList<Friendship> flist = fd.selectFriendshipAll();

		for (Friendship friendship : flist) {
			System.out.println(friendship.toString());
		}

	}

	private void selectFriendship(int id) throws SQLException {
		Friendship f = new Friendship();
		f = fd.selectFriendshipByPrimaryKey(id);
		System.out.println(f.toString());

	}

	private void insertFriendship() throws SQLException {
		int res;
		Friendship f = new Friendship();
		User u1 = ud.selectUserByPrimaryKey(6);
		User u2 = ud.selectUserByPrimaryKey(8);
		f.setUser1(u1);
		f.setUser2(u2);
		f.setReq_sent_by(u1);
		f.setStatus(FriendshipStatus.RequestSent);
		
		res = fd.insertFriendship(f);
		if (res == 1)
			System.out.println("row inserted successfully");
	}

	private void updateFriendship(int id) throws SQLException {
		int res;
		Date dob = Date.valueOf("1999-12-31");
		User u = new User();

		u = ud.selectUserByPrimaryKey(id);
		u.setFirstName("Milind");
		u.setLastName("Gokhale");
		u.setContactNumber("8123697654");
		u.setEmail("mgokhale@indiana.edu");
		u.setPassword("root");
		u.setDateOfBirth(dob);
		u.setIsVerified(0);
		u.setIsLocked(0);
		u.setLastActive(dob);
		res = ud.updateUser(u);
		if (res == 1)
			System.out.println("row updated successfully");

	}

	private void deleteFriendship(int id) throws SQLException {
		int res = fd.deleteUser(id);
		if (res == 1)
			System.out.println("row deleted successfully");
	}

}