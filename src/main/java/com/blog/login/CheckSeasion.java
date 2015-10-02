package com.blog.login;

import javax.servlet.http.HttpSession;

import com.blog.database.DataBase;

//ref: https://www.youtube.com/watch?v=21L78DQ7sxc

public class CheckSeasion {

	public String validateSeassion(LoginPojo loginpojo, HttpSession session)
	{
		
	   if(DataBase.season.get(session )!= null)
			   {
		   			//chcek session is already present
		   			return DataBase.season.get(session);
			   }
	   else{
		   			//insert into session
		    		DataBase.season.put(session, loginpojo.getUserName());  
		    		System.out.println("seassion : "+session+"   uname : "+DataBase.season.get(session));
		    		//System.out.println(DataBase.);
		    		return DataBase.season.get(session);
	   }
	}
}
