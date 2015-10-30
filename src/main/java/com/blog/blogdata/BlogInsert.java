package com.blog.blogdata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.blog.database.DataBase;

/**
 * Servlet implementation class Blog to retrive common blogs
 * 
 */
@WebServlet("/BlogInsert")
public class BlogInsert extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DataBase database;
	public DataSource dataSource;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BlogInsert() {
    	database = DataBase.getInstance();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		// 
		 //BlogAction dao = new BlogAction();
		 ApplicationContext context = database.context;
		 BlogActionDAO dao = (BlogActionDAO) context.getBean("BlogActionDAO"); 
		
		if(dao.insertNewBlog(request)=="Success")
		{		
			JSONObject json= new JSONObject();
			json.put("user", "success");
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}
	}
}
