package com.blog.secondaryservices;

import java.sql.Types;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.BlogPojo;
import com.blog.entity.SignUpPojo;

public class InsertData {

	DataBase database = null;
	EntityManager entityManager;

	public String insertUserProfileData(SignUpPojo signup) {

		return "Fail";
	}
	@Transactional
	public String insertBlog(HttpSession seassion, JSONObject jObj, EntityManager entityManager) {

		BlogPojo blogpojo = parseJsonReturnBlogData(jObj, seassion, entityManager);

		String blogtext = blogpojo.getBlogText();

		// server validation
		if (blogtext.toString().length() <= 140) {

			entityManager.getTransaction().begin();
			entityManager.merge(blogpojo);
			entityManager.getTransaction().commit();			
			return "Success";
		}
		return "Fail";
	}

	/*
	 * How to read json sent by ajax in servlet reference from :
	 * http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax
	 * -in-servlet Answered by : Rakesh Soni
	 */

	public BlogPojo parseJsonReturnBlogData(JSONObject jObj, HttpSession session, EntityManager entityManager) {
		// TODO Auto-generated method stub

		java.util.Iterator it = jObj.keys(); // gets all the keys
		String blogText = null;
		String date = null;
		String postTitle = null;
		try {
			while (it.hasNext()) {
				String key = "" + it.next();
				postTitle = "" + jObj.get(key);

				key = "" + it.next();
				blogText = (String) jObj.get(key);

				date = new Date().toString();

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "select max(postId) from BlogPojo";
		Query q =entityManager.createQuery(sql);
		database.postId=q.getMaxResults();

		SignUpPojo signUpPojoTemp = getSignUpPojo(entityManager, session);


		return new BlogPojo(blogText, date, postTitle, ++database.postId,
				session.getAttribute("UserName").toString(), signUpPojoTemp);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
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
