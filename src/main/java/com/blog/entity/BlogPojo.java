package com.blog.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Synchronization;

import org.hibernate.SynchronizeableQuery;

@Entity
@Table(name="Blog")

public class BlogPojo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false) 
	private int postId;
	
	@Column(nullable = false) 	
	private String blogText;
	@Column(nullable = false) 
	private String date;
	@Column(nullable = false) 
	private String userName;
	@Column(nullable = false) 
	private String postTitle;
    
    @ManyToOne 
    @JoinColumn(referencedColumnName="email")
    private SignUpPojo signUpPojo;
	
    public BlogPojo() {
		// TODO Auto-generated constructor stub
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
		
	public BlogPojo(String blogText, String date, String postTitle, int postId, String userName, SignUpPojo signUpPojo) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.blogText=blogText;
		this.date= date;
		this.postTitle=postTitle;
		this.postId=postId;
		this.signUpPojo=signUpPojo;
	}


	public SignUpPojo getSignUpPojo() {
		return signUpPojo;
	}


	public void setSignUpPojo(SignUpPojo signUpPojo) {
		this.signUpPojo = signUpPojo;
	}	
}
