package com.blog.signout;

import javax.servlet.http.HttpServletRequest;

import com.blog.database.ClearSessionForSignOut;

public class SignOutAction {

	public String deleteSession(HttpServletRequest request)
	{
       try{
    	   
    	   ClearSessionForSignOut clearsession = new ClearSessionForSignOut();
    	   clearsession.ClearSession(request.getSession());
       
       }catch(Exception e)
       {
    	   return "Fail";
       }
	return "Success";
	}	
}
