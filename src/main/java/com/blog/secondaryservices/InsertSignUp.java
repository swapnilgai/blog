package com.blog.secondaryservices;

import java.sql.Types;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.SignUpPojo;

public class InsertSignUp {
	@Transactional
	public String insertSignUpPojo(HttpServletRequest request, SignUpPojo signuppojo, EntityManager entityManager) {

		String sql1 = "select count(username) from SignUpPojo where email = '"+signuppojo.getEmail()+"'";

		Query q = entityManager.createQuery(sql1);
         
		if(Integer.parseInt(""+q.getSingleResult())==0)			
		{
            entityManager.getTransaction().begin();
			entityManager.persist(signuppojo);
			entityManager.getTransaction().commit();
			return signuppojo.getUserName();
		}
		else 
			return "Invalid Email";
	}
}
