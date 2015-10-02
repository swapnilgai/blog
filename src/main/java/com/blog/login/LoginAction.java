package com.blog.login;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.blog.database.ValidateUser;

public class LoginAction {
	
	public String validateUserAndSession(JSONObject jObj, HttpServletRequest request)
	{
		LoginPojo loginpojo= new ParseLoginJson().parseJsonReturnBlogData(jObj, request.getSession());
		ValidateUser  validateuser =  new ValidateUser(); 
		
		
		if(validateuser.validate(loginpojo).equals("Success"))
		{
			CheckSeasion checkSeasion = new CheckSeasion();
			return checkSeasion.validateSeassion(loginpojo, request.getSession());
		}
		return null;
	}
}
