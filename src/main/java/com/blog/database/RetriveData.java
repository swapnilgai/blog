package com.blog.database;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RetriveData {

	DataBase database;

	public RetriveData() {
		// TODO Auto-generated constructor stub
		database = DataBase.getInstance();
	}

	/*
	 * Returning JSON response from Servlet to Javascript/JSP page referenced
	 * from
	 * http://stackoverflow.com/questions/6154845/returning-json-response-from-
	 * servlet-to-javascript-jsp-page Answered By : majestica
	 */

	private JSONObject createJsonCommonBlogs() {
		String sql = "select count(postId) from blog";
		System.out.println(sql);

		if (database.jdbcTemplate.queryForInt(sql) != 0) {
			sql = "select * from BLOG ORDER BY DATE1 DESC";
			return getBlogInJson(sql, "commonBlog");
		}
		// Empty blog condition ie no blog present for first time
		return new JSONObject().put("commonBlog", false);
	}
	/*
	 * Returning JSON response from Servlet to Javascript/JSP page referenced
	 * from
	 * http://stackoverflow.com/questions/6154845/returning-json-response-from-
	 * servlet-to-javascript-jsp-page Answered By : majestica
	 */

	private JSONObject createJsonUserBlogs(String userName) {

		String sql = "select count(postId) from blog where email = '" + userName + "'";

		if (database.jdbcTemplate.queryForInt(sql) != 0) {
			sql = "select * from BLOG where email = '" + userName + "' ORDER BY DATE1 DESC";
			return getBlogInJson(sql, "userBlog");
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
	private JSONObject createJsonBlogByID(int postId) {
		String sql = "select * from BLOG where postId=  '" + postId + "' ORDER BY DATE1 DESC";

		return getBlogInJson(sql, "userBlogById");
	}

	public JSONObject getBlogInJson(String sql, String operation) {
		database = DataBase.getInstance();
		JSONObject userBlog = new JSONObject();
		JSONArray userBlogArray = new JSONArray();
		JSONObject blog = null;
		List<Map<String, Object>> list = database.jdbcTemplate.queryForList(sql);
		try {

			for (Map<String, Object> blogObj : list) {

				blog = new JSONObject();
				blog.put("postTitle", blogObj.get("postTitle"));
				System.out.println("pos titile :   " + blogObj.get("postTitle"));
				blog.put("date", blogObj.get("date1"));
				blog.put("userName", blogObj.get("username"));
				String blogtext = (String) blogObj.get("blogtext");

				blog.put("blogText", stringTrimer(blogtext));
				blog.put("imageUrl", blogObj.get("photourl"));
				blog.put("postId", blogObj.get("postId"));
				userBlogArray.put(blog);
				System.out.println(blogObj.get("username"));
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

	public JSONObject getCommonBlog() {
		return createJsonCommonBlogs();
	}

	public JSONObject getUserBlog(HttpSession session) {
		String userName = session.getAttribute("Email").toString();
		System.out.println("user : " + userName);
		return createJsonUserBlogs(userName);

	}

	public JSONObject getUserBlogByID(HttpSession session, int postId) {
		return createJsonBlogByID(postId);
	}

}
