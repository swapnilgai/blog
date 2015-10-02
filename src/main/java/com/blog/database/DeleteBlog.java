package com.blog.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;

public class DeleteBlog {

	public String DeleteBlogByID(String userName, int postId)
	{
		
		try
		{
			String key = DataBase.timestampPostId.get(""+postId);
			//detete blogpojo
			Collection values = DataBase.userBlog.get(key);
			Iterator valueIterator = values.iterator();
			while(valueIterator.hasNext()) 
				{  
					BlogPojo blogPojo = (BlogPojo) valueIterator.next(); 
					if(blogPojo.getPostId()==postId) {
						valueIterator.remove();
						// keyIterator.remove();
				}
		  
		  //detete usertimestamp
		  Collection values1 = DataBase.userTimestamp.get(userName);
		  Iterator valueIterator1 = values.iterator();
		  while(valueIterator1.hasNext()) 
		  	{
			  	String time =  (String) valueIterator1.next();
			  	if(DataBase.timestampPostId.get(""+postId)==time) {
			  		valueIterator1.remove();
			  		// keyIterator.remove();
			  	}
		  	}
		  DataBase.timestampPostId.remove(""+postId);
	
		}
}catch(Exception e){
  	   return null;
}
		return "Success";
}
}