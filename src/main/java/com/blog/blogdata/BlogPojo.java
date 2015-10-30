package com.blog.blogdata;

public class BlogPojo {
	
	private String blogText;
	private String date;
	private String userName;
	private String email;
    private String postTitle;
	private int postId;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
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
		
	public BlogPojo(String blogText, String date, String postTitle, String email, int postId, String userName) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.blogText=blogText;
		this.date= date;
		this.postTitle=postTitle;
		this.postId=postId;
		this.email = email;
	}	
}
