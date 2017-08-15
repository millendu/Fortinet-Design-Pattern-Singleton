package com.potlocker.object;

public class User {
	private int userID;
	private String userName;
	private char userGender;
	private String userAddress;
	private long userContact;
	private String userEmail;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public char getUserGender() {
		return userGender;
	}
	public void setUserGender(char userGender) {
		this.userGender = userGender;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public long getUserContact() {
		return userContact;
	}
	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}