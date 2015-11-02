package com.blog.blogdata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.blog.database.DataBase;

/**
 * Servlet implementation class BlogRetrive
 */
@WebServlet("/BlogRetriveCommon")
public class BlogRetriveCommon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public DataSource dataSource;
	DataBase database = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogRetriveCommon() {
        super();
        // TODO Auto-generated constructor stub
        database = DataBase.getInstance();

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
		System.out.println("innnnnn nnnnn nnnnnnnnnn nn  nn n "); 
		ApplicationContext context = database.context;
		 BlogActionDAO blogaction = (BlogActionDAO) context.getBean("BlogActionDAO"); 
		
		//BlogAction blogaction = new BlogAction();
		JSONObject json = blogaction.getCommonBlogs();
		response.setContentType("application/json");
		response.getWriter().write(json.toString());	
	}
}
