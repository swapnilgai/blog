package com.blog.secondaryservices;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.LoginPojo;

public class ValidateUser {
	
DataBase database;
	
	public String validate(LoginPojo login, JdbcTemplate jdbcTemplate)
	{

		try{
		String sql = "SELECT username FROM SIGNUP WHERE email = '"+login.getUserName()+"' AND pass = '"+login.getPassword()+"'";
			 
		System.out.println(sql);
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String userName = null;
	        
	      for(Map<String, Object> usern : list)
	      { 
	    	  userName=(String) usern.get("username");
	      	  System.out.println("name  :  "+userName);
	      }
		if(userName!=null){
			
		    return userName;	
		}
		else 
			return "Fail";

		}catch(Exception E)
		{
			return "Fail";
		}
	}	  
}
	

