package com.aloha.common.entities;

import java.util.Date;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date dateOfBirth;
	
	public User(int id,String fName,String lName,String email,String pwd, Date dob ){
		this.userId = id;
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.password = pwd; 
		this.dateOfBirth = dob;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public User getUser(int id){
		
		//Add code to get user from DAL layer
		return null;
		
	
	}
	
	public boolean deleteUser(int id){
		//Add code to delete user by calling DAL method
		return false;
	}

	public User updateUser(User user){
		//Add code to call DAL method
		return null;
	}
	
	public User addUser(User user){
		//call to DAL
		return null;
	}
	
	
}
