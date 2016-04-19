package com.blog.secondaryservices;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.BlogPojo;


public class RetriveData {


	private JSONObject createJsonCommonBlogs(EntityManager entityManager) {

		String sql = "select count(postId) from BlogPojo";
		Query q = entityManager.createQuery(sql);

		if(Integer.parseInt(""+q.getSingleResult())!=0)
		{
			sql = "select b from BlogPojo b ORDER BY DATE DESC";
			return getBlogInJson(sql, "commonBlog", entityManager);
		}
		// Empty blog condition ie no blog present for first time
		return new JSONObject().put("commonBlog", false);
	}

	private JSONObject createJsonUserBlogs(String userName, EntityManager entityManager) {

		String sql = "select count(postId) from BlogPojo b where SignUpPojo_email = '" + userName + "'";

		Query q = entityManager.createQuery(sql);

		if(Integer.parseInt(""+q.getSingleResult())!=0)
		{
			sql = "select b from BlogPojo b where SignUpPojo_email = '" + userName + "' ORDER BY DATE DESC";
			return getBlogInJson(sql, "userBlog",entityManager);
		}
		// Empty blog condition ie no blog present for first time
		return new JSONObject().put("userBlog", false);
	}

	/*
	 * Returning JSON response from Servlet to Javascript/JSP page referenced
	 * from
	 * http://stackoverflow.com/questions/6154845/returning-json-response-from-
	 * servlet-to-javascript-jsp-page Answered By : majestica
	 */
	private JSONObject createJsonBlogByID(int postId, EntityManager entityManager) {
		String sql = "select b from BlogPojo b where postId=  '" + postId + "' ORDER BY DATE DESC";

		return getBlogInJson(sql, "userBlogById", entityManager);
	}

	public JSONObject getBlogInJson(String sql, String operation, EntityManager entityManager) {
		JSONObject userBlog = new JSONObject();
		JSONArray userBlogArray = new JSONArray();
		JSONObject blog = null;
		TypedQuery<BlogPojo> query =entityManager.createQuery(sql, BlogPojo.class);
		List<BlogPojo> results = query.getResultList();
		
		try {
			for (BlogPojo blogObj : results) {

				blog = new JSONObject();
				blog.put("postTitle", blogObj.getPostTitle());
				blog.put("date", blogObj.getDate());
				blog.put("userName", blogObj.getUserName());
				String blogtext = (String) blogObj.getBlogText();

				blog.put("blogText", stringTrimer(blogtext));
				//blog.put("imageUrl", blogObj.ge);
				blog.put("postId", blogObj.getPostId());
				userBlogArray.put(blog);
			}

			System.out.println("operation: " + operation + "json ::::   " + blog);

			if (operation.equals("userBlogById"))
				userBlog.put("userBlogById", userBlogArray);

			else if (operation.equals("commonBlog"))
				userBlog.put("commonBlog", userBlogArray);

			else if (operation.equals("userBlog"))
				userBlog.put("userBlog", userBlogArray);
		} catch (JSONException jse) {
			System.out.println("json parsing error");
		}

		return userBlog;
	}

	public String stringTrimer(String str) {
		int len = 0;
		if (str.length() > 50)
			len = 50;
		else
			len = str.length();

		return str.substring(0, len);
	}

	public JSONObject getCommonBlog(EntityManager entityManager) {
		return createJsonCommonBlogs(entityManager);
	}

	public JSONObject getUserBlog(HttpSession session, EntityManager entityManager) {
		String userName = session.getAttribute("Email").toString();
		System.out.println("user : " + userName);
		return createJsonUserBlogs(userName, entityManager);

	}

	public JSONObject getUserBlogByID(HttpSession session, int postId, EntityManager entityManager) {
		return createJsonBlogByID(postId, entityManager);
	}

}
