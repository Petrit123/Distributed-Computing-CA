package com.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User {
		
	private String userName;
	private String passWord;
	private boolean isLoggedIn;
	private int sessionId;
	
	public User(String userName, String passWord, boolean isLoggedIn, int sessionId) {
		this.userName = userName;
		this.passWord = passWord;
		this.isLoggedIn = isLoggedIn;
		this.sessionId = sessionId;
	}
	
	public User() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

}
