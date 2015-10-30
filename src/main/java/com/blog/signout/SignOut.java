package com.blog.signout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import com.blog.database.DataBase;

/**
 * Servlet implementation class SignOut
 */
@WebServlet("/SignOut")
public class SignOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBase database = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignOut() {
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
		//SignOutAction signoutaction = new SignOutAction();
		ApplicationContext context = database.context;
		SignOutActionDAO signoutaction = (SignOutActionDAO) context.getBean("SignOutActionDAO"); 
		
		request.getSession().invalidate();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		if (signoutaction.deleteSession(request) == "Success")
			response.sendRedirect("/Blog/FrontEnd.jsp");
	}
}
