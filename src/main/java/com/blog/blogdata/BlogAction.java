package com.blog.blogdata;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import com.blog.database.DataBase;
import com.blog.database.DeleteBlog;
import com.blog.database.InsertData;
import com.blog.database.RetriveData;
import com.blog.database.retriveProfile;
import com.google.gson.JsonObject;

public class BlogAction {

	DataBase database = new DataBase();
	
	
	public JSONObject getUserBlogById(HttpServletRequest request)
	{
		JsonOperations jsonoperation = new JsonOperations();
		RetriveData retrive = new RetriveData();
		System.out.println(request.getParameter("postId"));
		JSONObject jObj= jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId= jsonoperation.parseJsonByID(jObj);
		
		return retrive.getUserBlogByID(request.getSession(), postId);
	}
	
	public String insertNewBlog(HttpServletRequest request)
	{
		JsonOperations jsonoperation = new JsonOperations();
		JSONObject jObj= jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		InsertData insertdata= new InsertData();
		
			if(insertdata.insertBlog(request.getSession(), jObj)=="Success")
				return "Success";	
		
	    return "Fail";
	}
	
	
	public JSONObject getCommonBlogs()
	   {
		   RetriveData retriveData = new RetriveData();
		   return retriveData.getCommonBlog();
	   }
	   
	   public JSONObject getUserBlogs(HttpServletRequest request)
	   {
		   RetriveData retriveData = new RetriveData();
		   return retriveData.getUserBlog(request.getSession());
	   }
	   
		
	   public String DeleteBlog(HttpServletRequest request)
	   {
		    JsonOperations jsonoperation = new JsonOperations();
	        DeleteBlog deleteblog = new DeleteBlog();
	        JSONObject jObj= jsonoperation.formatObject(request.getParameter("postId").toString());
	        int postId= jsonoperation.parseJsonByID(jObj);
	        
	        return deleteblog.DeleteBlogByID(""+DataBase.season.get(request.getSession()), postId);
	   }
	   
	   public String UpdateBlog(HttpServletRequest request)
	   {
		    JsonOperations jsonoperation = new JsonOperations();
	        com.blog.database.UpdateBlog updateblog = new    com.blog.database.UpdateBlog();
	        JSONObject jObj= jsonoperation.formatObject(request.getParameter("jsonObject").toString());
	        return  updateblog.UpdateBlogByID(jObj);
	   }
}
