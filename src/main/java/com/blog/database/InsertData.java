package com.blog.database;

import java.util.Date;
import java.util.concurrent.BlockingDeque;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;
import com.blog.signup.SignUpPojo;

public class InsertData {
	public String userName;
	public String password;
	public String email;
	public String bDate;
	public String photoURl;
	public DataBase data;
	DataBase database= new DataBase();
	
	
	public String insertUserProfileData(SignUpPojo signup)
	{
			if(DataBase.userData.put(signup.getUserName(), signup)!=null)
				  return "Success";
				
		return "Fail";	
	}
	
	public String insertBlog(HttpSession seassion, JSONObject jObj)
	{
		BlogPojo blogpojo = parseJsonReturnBlogData(jObj, seassion);
		blogpojo.setUserName(database.season.get(seassion)); 
		
		if(DataBase.userBlog.put(blogpojo.getDate(), 
				blogpojo)==true);
		if(DataBase.userTimestamp.put(DataBase.season.get(seassion), blogpojo.getDate())==true)
			return "Success";
		
		return "Fail";
	}


	public BlogPojo parseJsonReturnBlogData(JSONObject jObj, HttpSession session) {
		// TODO Auto-generated method stub
		
		java.util.Iterator it = jObj.keys(); //gets all the keys	
		String blogText = null;
		String date = null;
		String postTitle = null;
		 try {

		while(it.hasNext())
		{
		    
			String key = ""+it.next(); // get key
		    postTitle=""+jObj.get(key);
		    
		    key = ""+it.next(); // get key
		   	blogText = (String) jObj.get(key); // get value
		   	
		   	
		    date =  new Date().toString();
		    String userName =  database.season.get(session); 
			}
		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get value
		 database.timestampPostId.put(""+database.postId, ""+date);
		return new BlogPojo(blogText, date, postTitle, DataBase.season.get(session), database.postId++);
	}	
}
