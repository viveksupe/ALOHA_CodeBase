package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.user.User;


/**
 * @author Milind
 *
 */
public class UserDal {

	Connection con = null;
	// write the queries for User table
	private String SELECT;
	private String INSERT_USER;
	private String UPDATE_USER;
	private String DELETE_USER;
	private String SELECT_LOGIN;
	/**
	 * Constructor
	 */
	public UserDal() {
		SELECT = "SELECT user.user_id, user.fname, user.lname, user.contact_number, user.email, user.password, user.bdate, user.isVerified, user.isLocked, user.lastactive FROM user";
		INSERT_USER = "INSERT INTO user(fname, lname, contact_number, email, password, bdate, isVerified, isLocked, lastactive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UPDATE_USER = "UPDATE user SET fname = ?, lname = ?, contact_number = ?, email = ?, password = ?, bdate = ?, isVerified = ?, isLocked = ?, lastactive = ? WHERE user_id = ?;";
		DELETE_USER = "DELETE FROM user WHERE user_id = ?;";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	/**
	 * @return List of users
	 * @throws SQLException
	 */
	public ArrayList<User> selectUserAll() throws SQLException {
		String SelectUsersAllStatement = SELECT;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersAllStatement);
			rSet = ps.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			while (rSet.next()) {
				User u = new User();
				u.setUserId(rSet.getInt("user_id"));
				u.setFirstName(rSet.getString("fname"));
				u.setLastName(rSet.getString("lname"));
				u.setContactNumber(rSet.getString("contact_number"));
				u.setEmail(rSet.getString("email"));
				u.setPassword(rSet.getString("password"));
				u.setDateOfBirth(rSet.getDate("bdate"));
				u.setIsVerified(rSet.getInt("isVerified"));
				u.setIsLocked(rSet.getInt("isLocked"));
			    u.setLastActive(rSet.getDate("lastActive"));
				users.add(u);
			}
			return users;
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

	public User selectUserByPrimaryKey(int id) throws SQLException {
		String SelectUsersByPrimaryKeyStatement = SELECT
				+ " where user.user_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(SelectUsersByPrimaryKeyStatement);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			if (rSet.next()) {
				User u = new User();
				u.setUserId(rSet.getInt("user_id"));
				u.setFirstName(rSet.getString("fname"));
				u.setLastName(rSet.getString("lname"));
				u.setContactNumber(rSet.getString("contact_number"));
				u.setEmail(rSet.getString("email"));
						u.setPassword(rSet.getString("password"));
				u.setDateOfBirth(rSet.getDate("bdate"));
						u.setIsVerified(rSet.getInt("isVerified"));
						u.setIsLocked(rSet.getInt("isLocked"));
						u.setLastActive(rSet.getDate("lastActive"));
				return u;
			} else
				return null;
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

	public int insertUser(User u) throws SQLException {
		con = DatabaseHandlerSingleton.getDBConnection();
		String insertUserStatement = INSERT_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserStatement);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getContactNumber());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setDate(6, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(7, u.getIsVerified());
			ps.setInt(8, u.getIsLocked());
			ps.setDate(9, (java.sql.Date) u.getLastActive());

			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int updateUser(User u) throws SQLException {
		String updateUserStatement = UPDATE_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateUserStatement);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getContactNumber());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setDate(6, (java.sql.Date) u.getDateOfBirth());
			ps.setInt(7, u.getIsVerified());
			ps.setInt(8, u.getIsLocked());
			ps.setDate(9, (java.sql.Date) u.getLastActive());
			ps.setInt(10, u.getUserId());
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int deleteUser(int id) throws SQLException {
		String updateUserStatement = DELETE_USER;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(updateUserStatement);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}
	public User getPasswordByEmail(String email, String pwd) throws SQLException{
		String query = "select * from user where user.email = ? and user.password = ?;";
		PreparedStatement ps = null;
		ResultSet res = null;
		User u = null;
		try{
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pwd);
			res = ps.executeQuery();
			if(res.first())
			{
				u = new User();
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("fname"));
				u.setLastName(res.getString("lname"));
				u.setContactNumber(res.getString("contact_number"));
				u.setEmail(res.getString("email"));
				u.setPassword(res.getString("password"));
				u.setDateOfBirth(res.getDate("bdate"));
						u.setIsVerified(res.getInt("isVerified"));
						u.setIsLocked(res.getInt("isLocked"));
						u.setLastActive(res.getDate("lastActive"));
			}
			else
				u = null;
		}
		catch(SQLException ex){
			u = null;
			System.out.println(""+ex.getMessage());
			throw ex;
		}
		finally{
			if (ps != null)
				ps.close();
			con.close();
		}
		
		return u;
	}
}
