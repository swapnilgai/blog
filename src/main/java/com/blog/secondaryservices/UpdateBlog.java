package com.blog.secondaryservices;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.BlogPojo;
import com.blog.entity.SignUpPojo;


public class UpdateBlog {

	public UpdateBlog() {
		// TODO Auto-generated constructor stub

	}

	/*
	 * How to read json sent by ajax in servlet reference from :
	 * http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax
	 * -in-servlet Answered by : Rakesh Soni
	 */

	private String[] parseJsonReturnBlogData(JSONObject jObj) {
		// TODO Auto-generated method stub

		System.out.println("json in update : " + jObj);
		java.util.Iterator it = jObj.keys(); // gets all the keys

		String data[] = new String[3];
		while (it.hasNext()) {
			String key = "" + it.next(); // get key
			data[0] = "" + jObj.get(key);

			key = "" + it.next(); // get key
			data[1] = (String) jObj.get(key); // get value

			key = "" + it.next(); // get key
			data[2] = (String) jObj.get(key); // get value
		}
		return data;
	}

	public String UpdateBlogByID(JSONObject jObj, EntityManager entityManager, HttpSession session) {
		try {
			String data[] = parseJsonReturnBlogData(jObj);

			int pId = Integer.parseInt(data[1]);

			String date = new Date().toString();

			SignUpPojo signUpPojoTemp = getSignUpPojo(entityManager, session);

			BlogPojo blogpojo = new BlogPojo(data[2], date, data[0], pId,
					session.getAttribute("UserName").toString(), signUpPojoTemp);

			entityManager.getTransaction().begin();
			entityManager.merge(blogpojo);
			entityManager.getTransaction().commit();	
			
		} catch (Exception e) {
			return null;
		}
		return "Success";
	}

	public SignUpPojo getSignUpPojo(EntityManager entityManager, HttpSession session )
	{
		String sql="select s from SignUpPojo s where email='"+session.getAttribute("Email").toString()+"'";
		TypedQuery<SignUpPojo> query =entityManager.createQuery(sql, SignUpPojo.class);
		List<SignUpPojo> results = query.getResultList();

		for(SignUpPojo signUpPojo : results)
			return signUpPojo;

		return null;		
	}
}
