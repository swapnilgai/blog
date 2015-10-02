package com.blog.login;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;
import com.blog.database.DataBase;

public class ParseLoginJson {
	public LoginPojo parseJsonReturnBlogData(JSONObject jObj, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("IN PARSERRRRRRRS : "+jObj);
		java.util.Iterator it = jObj.keys(); //gets all the keys	
        
		String userName = null;
		String password = null;
		
		 try {

		while(it.hasNext())
		{ 
			String key = ""+it.next(); // get key
		    password=""+jObj.get(key);
		 
		    
		     key = ""+it.next(); // get key
		    userName =  ""+jObj.get(key);
		    System.out.println("uname : "+userName+"      pass  :  "+password);
		    
		}
		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get value
		    //session.putValue(key, o); // store in session
		return new LoginPojo(userName, password);
	}	
	
	
	
	
}
