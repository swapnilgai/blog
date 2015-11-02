package com.blog.login;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.blog.database.ValidateUser;

public class LoginAction extends JdbcDaoSupport implements LoginActionDAO {
	
	public String validateUserAndSession(JSONObject jObj, HttpServletRequest request)
	{
		LoginPojo loginpojo= new ParseLoginJson().parseJsonReturnBlogData(jObj, request.getSession());
		ValidateUser  validateuser =  new ValidateUser(); 
		String userName = validateuser.validate(loginpojo);
		
		if(userName!="Fail")
		{
			CheckSeasion checkSeasion = new CheckSeasion();
			return checkSeasion.validateSeassion(loginpojo, request.getSession(), userName);
		}
		return null;
	}
}
