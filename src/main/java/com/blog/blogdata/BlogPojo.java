package com.blog.blogdata;

import java.util.Date;



public class BlogPojo {
	
	private String blogText;
	private String date;
	private String userName;
	private String postTitle;
	private int postId;
	
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String posttitle) {
		this.postTitle = posttitle;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBlogText() {
		return blogText;
	}

	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
		
	public BlogPojo(String blogText, String date, String postTitle, String userName, int postId) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.blogText=blogText;
		this.date= date;
		this.postTitle=postTitle;
		this.postId=postId;
	}	
}
