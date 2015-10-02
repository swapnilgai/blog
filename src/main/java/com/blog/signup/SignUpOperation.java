package com.blog.signup;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.blog.database.InsertSignUp;

public class SignUpOperation {

	//http://edwin.baculsoft.com/2011/11/how-to-create-a-simple-servlet-to-handle-json-requests/
	
	public SignUpPojo parseJsonToSignUp(HttpServletRequest request)
	{
		// http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax-in-servlet
		
		JSONObject jObj = new JSONObject(request.getParameter("loadProds")); 
		Iterator it = jObj.keys(); //gets all the keys

	
		final String profilePic = "imagesu.jpg";
		
		String key = ""+it.next();
	    String email = jObj.get(key).toString();
	    
		   key = ""+it.next();
	    String password = jObj.get(key).toString();
	    
	    
	    key = ""+it.next();
	    String fName = jObj.get(key).toString();
	    key = ""+it.next();
	    String lName = jObj.get(key).toString();
	    
	    String userName = fName+" "+lName;
	    
	    key = ""+it.next();
	    String bdate = jObj.get(key).toString();
	     	     	  
		return new SignUpPojo(userName, password,email,bdate, profilePic);
	}
	
	public void insertInDatabase(HttpServletRequest request)
	{
     SignUpPojo signuppojo= parseJsonToSignUp(request);
     InsertSignUp signup = new InsertSignUp();
     signup.insertSignUpPojo(request, signuppojo);
	}
}
