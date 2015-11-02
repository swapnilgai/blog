package com.blog.database;

import java.util.List;
import java.util.Map;

import com.blog.login.LoginPojo;

public class ValidateUser {
	
DataBase database;
	
	public String validate(LoginPojo login)
	{
	
		database = DataBase.getInstance();
		try{
		String sql = "SELECT username FROM SIGNUP WHERE email = '"+login.getUserName()+"' AND pass = '"+login.getPassword()+"'";
			 
		System.out.println(sql);
		
		List<Map<String,Object>> list = database.jdbcTemplate.queryForList(sql);
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
	

