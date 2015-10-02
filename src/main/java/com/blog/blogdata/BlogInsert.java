package com.blog.blogdata;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Blog to retrive common blogs
 * 
 */
@WebServlet("/BlogInsert")
public class BlogInsert extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogInsert() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	   	System.out.println("innnn ");
		BlogAction blogaction = new BlogAction();
		if(blogaction.insertNewBlog(request)=="Success")
		{		
			JSONObject json= new JSONObject();
			json.put("user", "success");
			System.out.println("send item :::::::   "+json);
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
			
		}
	}
}
