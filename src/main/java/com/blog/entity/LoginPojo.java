package com.blog.entity;

import org.springframework.beans.factory.annotation.Required;

public class LoginPojo {

	private String userName;
	private String password;
	
	public LoginPojo() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginPojo(String userName, String password)
	{
		this.userName=userName;
		this.password=password;
	}
	@Required
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
