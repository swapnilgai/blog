package com.blog.login;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * How to read json sent by ajax in servlet
 * reference from : http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax-in-servlet
 * Answered by : Rakesh Soni
 */
public class ParseLoginJson {
	public LoginPojo parseJsonReturnBlogData(JSONObject jObj, HttpSession session) {
		// TODO Auto-generated method stub
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
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get value
		return new LoginPojo(userName, password);
	}		
}
