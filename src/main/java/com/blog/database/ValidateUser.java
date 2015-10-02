package com.blog.database;

import com.blog.login.LoginPojo;

public class ValidateUser {


	
	public String validate(LoginPojo login)
	{
		try{
		if(DataBase.userCred.get(login.getUserName()).equals(login.getPassword()))
		    return "Success";	
		 
		}catch(Exception E)
		{
			return "Fail";
		}
		return "Fail";
	}
	
	//ref : http://stackoverflow.com/questions/15951032/jsonobject-classnotfoundexception	  

		  
}
	

