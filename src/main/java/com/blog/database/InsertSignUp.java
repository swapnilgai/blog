package com.blog.database;

import javax.servlet.http.HttpServletRequest;
import com.blog.signup.SignUpPojo;

public class InsertSignUp {

	public void insertSignUpPojo(HttpServletRequest request, SignUpPojo signuppojo)
	{
		DataBase.userData.put(signuppojo.getUserName(), signuppojo);
		DataBase.season.put(request.getSession(), signuppojo.getUserName());
		DataBase.imageUrl.put(signuppojo.getUserName(), signuppojo.getPhotoURl());
		DataBase.userCred.put(signuppojo.getUserName(), signuppojo.getPassword());
	}
}
