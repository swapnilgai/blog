package com.blog.primaryservices;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.blog.dao.SignUpActionDAO;
import com.blog.entity.SignUpPojo;
import com.blog.secondaryservices.EmailValidate;
import com.blog.secondaryservices.InsertSignUp;
import com.blog.secondaryservices.JsonOperations;
public class SignUpAction implements SignUpActionDAO{

	JdbcTemplate jdbcTemplate;
	public SignUpPojo parseJsonToSignUp(HttpServletRequest request)
	{
		/*
		 * How to read json sent by ajax in servlet
		 * reference from : http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax-in-servlet
		 * Answered by : Rakesh Soni
		 */
		
		JsonOperations jsonoperation = new JsonOperations();
		System.out.println(request.getParameter("jsonObject").toString());
		JSONObject jObj= jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		
		Iterator it = jObj.keys(); //gets all the keys

		String profilePic = "imagesu.jpg";
		
		String key = ""+it.next();
		  String fName = jObj.get(key).toString();
		    
	    
		key = ""+it.next();
		String lName = jObj.get(key).toString();
	    
	    
	    key = ""+it.next();
	    String password = jObj.get(key).toString();
	    
	    key = ""+it.next();
	    String bdate = jObj.get(key).toString();
	    
	    String userName = fName+" "+lName;
	    
	    key = ""+it.next();
	    String email = jObj.get(key).toString();
	    
		return new SignUpPojo(userName, password,email,bdate, profilePic);
	}
	
	public String insertInDatabase(HttpServletRequest request)
	{
	  SignUpPojo signuppojo = parseJsonToSignUp(request);
	  EmailValidate emailValidate = new EmailValidate();
	  
	  String email  = signuppojo.getEmail();
	  Boolean validateEmail =  emailValidate.validate(email);
	  
	  if(validateEmail == true){
		  InsertSignUp signup = new InsertSignUp();
		  
		  HttpSession session = request.getSession();
		  session.setAttribute("UserName", signuppojo.getUserName());
		  session.setAttribute("Email", signuppojo.getEmail());
		  
		  return signup.insertSignUpPojo(request, signuppojo, jdbcTemplate);
	  }else{
		  return "Invalid Email";
	  }
	  
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
