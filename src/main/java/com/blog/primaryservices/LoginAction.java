package com.blog.primaryservices;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.blog.dao.LoginActionDAO;
import com.blog.entity.LoginPojo;
import com.blog.secondaryservices.CheckSeasion;
import com.blog.secondaryservices.ParseLoginJson;
import com.blog.secondaryservices.ValidateUser;

public class LoginAction  implements LoginActionDAO {
	JdbcTemplate jdbcTemplate;
	
	public String validateUserAndSession(JSONObject jObj, HttpServletRequest request)
	{
		LoginPojo loginpojo= new ParseLoginJson().parseJsonReturnBlogData(jObj, request.getSession());
		ValidateUser  validateuser =  new ValidateUser(); 
		String userName = validateuser.validate(loginpojo, jdbcTemplate);
		
		if(userName!="Fail")
		{
			CheckSeasion checkSeasion = new CheckSeasion();
			return checkSeasion.validateSeassion(loginpojo, request.getSession(), userName);
		}
		return null;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	

}
