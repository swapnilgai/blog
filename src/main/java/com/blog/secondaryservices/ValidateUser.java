package com.blog.secondaryservices;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.BlogPojo;
import com.blog.entity.LoginPojo;
import com.blog.entity.SignUpPojo;

public class ValidateUser {
		
	public String validate(LoginPojo login, EntityManager entityManager)
	{
		try{
			
		String sql = "SELECT b FROM SignUpPojo b WHERE email = '"+login.getUserName()+"' AND password = '"+login.getPassword()+"'";
		System.out.println("sql :: "+sql);
		//entityManager.getTransaction().begin();
		TypedQuery<SignUpPojo> query =entityManager.createQuery(sql, SignUpPojo.class);
		List<SignUpPojo> results = query.getResultList();
		
		String userName = null;
	    System.out.println("em :::"+entityManager);    
		
	      for(SignUpPojo usern : results)
	      { 
	    	  userName=(String) usern.getUserName();
	    	  System.out.println("result :   "+userName);
	      }
		if(userName!=null){			
		    return userName;	
		}
		else 
			return "Fail";

		}catch(Exception E)
		{
			return "Fail";
		}
	}	  
}
	

