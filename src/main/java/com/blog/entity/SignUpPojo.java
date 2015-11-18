package com.blog.entity;

public class SignUpPojo {
	private String userName;
	private String password;
	private String email;
	private String bDate;
	private String photoURl;
	
	public SignUpPojo(String userName, String password, String email, String bDate, String photoURl) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.bDate=bDate;
		this.photoURl=photoURl;
	}
	
	public SignUpPojo() {
		// TODO Auto-generated constructor stub
		}
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public String getPhotoURl() {
		return photoURl;
	}
	public void setPhotoURl(String photoURl) {
		this.photoURl = photoURl;
	}
}
