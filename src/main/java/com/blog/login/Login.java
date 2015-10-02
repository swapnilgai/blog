package com.blog.login;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.blog.blogdata.BlogAction;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//ref : http://stackoverflow.com/questions/6154845/returning-json-response-from-servlet-to-javascript-jsp-page
		
		String userCred = request.getParameter("jsonObject");
		
		userCred= userCred.replace("[", "");
		userCred= userCred.replace("]", "");
		
		JSONObject json = new JSONObject(userCred);
		
		LoginAction loginaction = new LoginAction();
		if(loginaction.validateUserAndSession(json,request)!=null)
		{	
			json= new JSONObject();
			json.put("user", "success");
			BlogAction blogaction = new BlogAction();
		
			HttpSession session = request.getSession();
			session.setAttribute("UserName", "success");
			session.setMaxInactiveInterval(30);
			
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
			
		   
			System.out.println("uname : : : : "+ request.getAttribute("userName"));

		}
	}
}
