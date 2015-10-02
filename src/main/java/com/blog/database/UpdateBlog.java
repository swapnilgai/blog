package com.blog.database;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;


public class UpdateBlog {
	
	private String[] parseJsonReturnBlogData(JSONObject jObj) {
		// TODO Auto-generated method stub
		
		java.util.Iterator it = jObj.keys(); //gets all the keys	
		
		String data[] = new String[3];
			 while(it.hasNext())
			 {
				 String key = ""+it.next(); // get key
				 data[0]=""+jObj.get(key);
		    
				 key = ""+it.next(); // get key
				 data[1] = (String) jObj.get(key); // get value
		   	
				 key = ""+it.next(); // get key
				 data[2] = (String) jObj.get(key); // get value
			}
		return data;
	}	

		
	public String UpdateBlogByID(JSONObject jObj)
	{	
		try
		{
			String data[]= parseJsonReturnBlogData(jObj);
			System.out.println("post id ::::::::   "+data[2]);
			int postId= Integer.parseInt(data[2]);
			String key = DataBase.timestampPostId.get(""+data[2]);
			//detete blogpojo
			Collection values = DataBase.userBlog.get(key);
			Iterator valueIterator = values.iterator();
			while(valueIterator.hasNext()) 
				{  
					BlogPojo blogPojo = (BlogPojo) valueIterator.next(); 
					if(blogPojo.getPostId()==postId) 
					{
						  blogPojo.setBlogText(data[1]);
						  blogPojo.setPostTitle(data[0]);
						  break;
					}
				}
		}catch(Exception e){
			return null;
		}
			return "Success";
		}
}
