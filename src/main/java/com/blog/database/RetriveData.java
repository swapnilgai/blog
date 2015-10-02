package com.blog.database;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;
import com.blog.signup.SignUpPojo;




public class RetriveData {

	
	//ref: http://stackoverflow.com/questions/6154845/returning-json-response-from-servlet-to-javascript-jsp-page
	
	private JSONObject createJsonCommonBlogs()
	{
		JSONObject userBlog      = new JSONObject();
		JSONArray  userBlogArray = new JSONArray();
		JSONObject blog = null;
	
		try
		{
			Set<String> keysTemp = DataBase.userBlog.keySet();
			
			SortedSet<String> keys=new TreeSet<String>();
			keys.addAll(keysTemp);
			
			System.out.println("keys : "+keys);
		   for (String key: keys)
		   {
		       Collection<BlogPojo> blogp=DataBase.userBlog.get(key);
		       for(BlogPojo blogpojo :  blogp)
		    		   {
		    	   blog = new JSONObject();
		    	   blog.put("postTitle", blogpojo.getPostTitle());
		    	   blog.put("date", blogpojo.getDate());
		    	   blog.put("userName", blogpojo.getUserName());
		    	   String blogtext = blogpojo.getBlogText();
		    	   
		    	   blog.put("blogText", stringTrimer(blogtext));
		    	   blog.put("imageUrl", DataBase.imageUrl.get(blogpojo.getUserName()));
		    	   blog.put("postId", blogpojo.getPostId());
		      userBlogArray.put(blog);  
		//    System.out.println("blog : "+blog);  
		    		 }
		   }
		   userBlog.put("commonBlog", userBlogArray);
		 
		}
		catch (JSONException jse)
		{ 
			System.out.println("json parsing error");
		}
		
		return userBlog;
	}
	
	
	private JSONObject createJsonUserBlogs(String userName)
	{
		
		JSONObject userBlog      = new JSONObject();
		JSONArray  userBlogArray = new JSONArray();
		JSONObject blog = null;
		ArrayList<String> keys = new ArrayList<String>();	
		
		try
		{
			for(String date : DataBase.userTimestamp.get(userName))
			{
				if(date!=null)
					keys.add(date);
			}
			System.out.println("keys for user : "+keys);
		   for (String key: keys)
		   {  
		       Collection<BlogPojo> blogp=DataBase.userBlog.get(key);
		       for(BlogPojo blogpojo :  blogp)
		       {
		    	   if(userName.equals(blogpojo.getUserName()))
		    	   {
		    		   blog = new JSONObject();
		    		   blog.put("posttitle", blogpojo.getPostTitle());
		    		   blog.put("date", blogpojo.getDate());
		    		   blog.put("userName", blogpojo.getUserName());
		    		   String blogtext = blogpojo.getBlogText();
		    		   blog.put("blogText", stringTrimer(blogtext));
		    		   blog.put("imageUrl", DataBase.imageUrl.get(blogpojo.getUserName()));
		    		   blog.put("postId", blogpojo.getPostId());
		    		   userBlogArray.put(blog);  
		    	   }
		    	}
		   }
		   userBlog.put("userBlog", userBlogArray);
		}
		catch (JSONException jse)
		{ 
			System.out.println("json parsing error");
		}
		
		return userBlog;
	}
	
	
	
	private JSONObject createJsonBlogByID(String userName, int postId)
	{
		
		JSONObject userBlog      = new JSONObject();
		JSONArray  userBlogArray = new JSONArray();
		JSONObject blog = null;
		ArrayList<String> keys = new ArrayList<String>();	
		
		try
		{
			String key=DataBase.timestampPostId.get(""+postId);
		       Collection<BlogPojo> blogp=DataBase.userBlog.get(key);
		       for(BlogPojo blogpojo :  blogp)
		       {
		    	  if(blogpojo.getPostId()==postId)
		    	  {
		    	   blog = new JSONObject();
		    	   blog.put("posttitle", blogpojo.getPostTitle());
		    	   blog.put("date", blogpojo.getDate());
		    	   blog.put("userName", blogpojo.getUserName());
		    	   blog.put("blogText", blogpojo.getBlogText());
		    	   blog.put("imageUrl", DataBase.imageUrl.get(blogpojo.getUserName()));
		    	   blog.put("postId", blogpojo.getPostId());
		    	   userBlogArray.put(blog);  
		    	  }
		       }
		   userBlog.put("userBlogById", userBlogArray);
		}
		catch (JSONException jse)
		{ 
			System.out.println("json parsing error");
		}
		
		return userBlog;
	}
	
	private JSONObject createJsonUserData(String userName)
	{
		JSONObject userData  = new JSONObject();	
		try
		{		     
		       userData = new JSONObject();
		       SignUpPojo signup= DataBase.userData.get(userName); 
		       userData.put("userName", signup.getUserName());
		       userData.put("email", signup.getEmail());
		       userData.put("photoUrl", signup.getPhotoURl());
		       userData.put("bDate", signup.getbDate());
		}
		catch (JSONException jse)
		{ 
			System.out.println("json parsing error");
		}
		return userData;
	}
	
	
	
	public String stringTrimer(String str)
	{
		return str.substring(0, 50);
	}
	
	public JSONObject getCommonBlog()
	{
		return createJsonCommonBlogs(); 	
	}
	
	
	public JSONObject getUserBlog(HttpSession session)
	{
	  	String userName =DataBase.season.get(session);
		System.out.println("user : "+userName);	
	  	return createJsonUserBlogs(userName);
	    
	}
	
	public JSONObject getUserData(HttpSession session)
	{
		return createJsonUserData(DataBase.season.get(session));
		
	}
  
	public JSONObject getUserBlogByID(HttpSession session, int postId)
	{
	    return createJsonBlogByID(DataBase.season.get(session), postId);
	}

}
