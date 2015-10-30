package com.blog.blogdata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import com.blog.database.DataBase;

/**
 * Servlet implementation class DeleteBlog
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// public DataSource dataSource;
	DataBase database = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
		super();
		// TODO Auto-generated constructor stub
		database = DataBase.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationContext context = database.context;
		BlogActionDAO blogaction = (BlogActionDAO) context.getBean("BlogActionDAO");

		// BlogAction blogaction = new BlogAction();
		if (blogaction.DeleteBlog(request) == "Success") {
			response.setContentType("application/json");
			response.getWriter().write("Success");
		}
	}
}
