package com.aloha.common.entities;

import java.util.Date;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String password;
	private Date dateOfBirth;
	private int isVerified;
	private int isLocked;
	private Date lastActive;

	public User() {
		this.userId = 0;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.password = null;
		this.dateOfBirth = null;
		this.isVerified = -1;
		this.isLocked = -1;
		this.lastActive = null;
	}
	
	public User(int id, String fName, String lName, String email, String pwd,
			String contactNumber, Date dob, int isVerified, int isLocked, Date lastActive) {
		this.userId = id;
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.password = pwd;
		this.dateOfBirth = dob;
		this.isVerified = isVerified;
		this.isLocked = isLocked;
		this.lastActive = lastActive;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}


	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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


	/**
	 * @return the isVerified
	 */
	public int getIsVerified() {
		return isVerified;
	}


	/**
	 * @param isVerified the isVerified to set
	 */
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}


	/**
	 * @return the isLocked
	 */
	public int getIsLocked() {
		return isLocked;
	}


	/**
	 * @param isLocked the isLocked to set
	 */
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}


	/**
	 * @return the lastActive
	 */
	public Date getLastActive() {
		return lastActive;
	}


	/**
	 * @param lastActive the lastActive to set
	 */
	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}


	public User getUser(int id) {

		// Add code to get user from DAL layer
		return null;

	}

	public boolean deleteUser(int id) {
		// Add code to delete user by calling DAL method
		return false;
	}

	public User updateUser(User user) {
		// Add code to call DAL method
		return null;
	}

	public User addUser(User user) {
		// call to DAL
		return null;
	}

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(new StringBuilder().append("[userId=")
				.append(userId).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[firstName=")
				.append(firstName).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[lastName=")
				.append(lastName).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[contactNumber=")
				.append(contactNumber).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[email=").append(email)
				.append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[password=")
				.append(password).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[dateOfBirth=")
				.append(dateOfBirth).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[isVerified=")
				.append(isVerified).append(" items]\n").toString());
		stringBuffer.append(new StringBuilder().append("[isLocked=")
				.append(isLocked).append("]\n").toString());
		stringBuffer.append(new StringBuilder().append("[lastActive=")
				.append(lastActive).append("]\n").toString());
		return stringBuffer.toString();
	}

}
