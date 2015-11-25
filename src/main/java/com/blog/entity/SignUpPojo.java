package com.blog.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class SignUpPojo implements Serializable {

	@Id
	@Column(nullable = false) 
	private String email;
	@Column(nullable = false) 
	private String userName;
	@Column(nullable = false) 
	private String password;
	@Column(nullable = false) 
	private String bDate;
	@Column(nullable = false) 
	private String photoURl;

	@Column(nullable = false) 
	@OneToMany(mappedBy="signUpPojo",targetEntity=BlogPojo.class, cascade=CascadeType.MERGE)
	private List<BlogPojo> blogs;

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

	public List<BlogPojo> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<BlogPojo> blogs) {
		this.blogs = blogs;
	}
}
