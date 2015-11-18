package com.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.dao.BlogActionDAO;
import com.blog.secondaryservices.DataBase;

/**
 * Servlet implementation class UpdateBlog
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// public DataSource dataSource;
	DataBase database = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BlogActionDAO blogaction = (BlogActionDAO) context.getBean("BlogActionDAO");

		// BlogAction blogaction = new BlogAction();
		blogaction.UpdateBlog(request);

		JSONObject json = new JSONObject();
		json.put("user", "success");

		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
}
