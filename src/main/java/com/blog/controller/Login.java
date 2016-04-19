package com.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.dao.BlogActionDAO;
import com.blog.dao.LoginActionDAO;
import com.blog.primaryservices.BlogAction;
import com.blog.secondaryservices.DataBase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBase database = null;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * Returning JSON response from Servlet to Javascript/JSP page
		 * referenced from http://stackoverflow.com/questions/6154845/returning-json-response-from-servlet-to-javascript-jsp-page
		 * Answered By : majestica
		 */
		String userCred = request.getParameter("jsonObject");
		
		userCred= userCred.replace("[", "");
		userCred= userCred.replace("]", "");
		
		JSONObject json = new JSONObject(userCred);
		
		//LoginAction loginaction = new LoginAction();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		LoginActionDAO loginaction = (LoginActionDAO) context.getBean("LoginActionDAO"); 
		
		String temp= loginaction.validateUserAndSession(json,request);
		
		if(temp!=null)
		{	
			json= new JSONObject();
			json.put("user", "success");
			new BlogAction();
		
			HttpSession session = request.getSession();
			//session.setAttribute("UserName", temp);
			session.setMaxInactiveInterval(90);
			
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}
		else
		{
			json= new JSONObject();
			json.put("user", "fail");
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}
	}
}
