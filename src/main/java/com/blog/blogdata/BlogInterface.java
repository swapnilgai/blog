package com.blog.blogdata;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public interface BlogInterface
{

	public JSONObject getUserBlogById(HttpServletRequest request);
	
	public String insertNewBlog(HttpServletRequest request);
	
	public JSONObject getCommonBlogs();
	   
	public JSONObject getUserBlogs(HttpServletRequest request);
	   	
	public String DeleteBlog(HttpServletRequest request);
	   
	public String UpdateBlog(HttpServletRequest request);
}
