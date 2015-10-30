package com.blog.signup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.blog.database.DataBase;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBase database = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		//SignUpAction signupoperation = new SignUpAction();		
		ApplicationContext context = database.context;
		SignUpActionDAO signupoperation = (SignUpActionDAO) context.getBean("SignUpActionDAO"); 
		
		
		HttpSession session = request.getSession();
		JSONObject json;
		json= new JSONObject();
		
		String temp = signupoperation.insertInDatabase(request);
		
		
		if(temp!="Fail" && temp !="Invalid Email")
		{		
			json.put("user", "success");
			session.setAttribute("UserName", temp);
			session.setMaxInactiveInterval(90);
		}
		else 
		{
			if(temp == "Fail"){
				json.put("user", "fail");
			}
			else{
				json.put("user", "InvalidEmail");
			}
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
}
