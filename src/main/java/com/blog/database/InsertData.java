package com.blog.database;

import java.sql.Types;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.blog.blogdata.BlogPojo;
import com.blog.signup.SignUpPojo;

public class InsertData {

	DataBase database = null;

	public String insertUserProfileData(SignUpPojo signup) {
		database = DataBase.getInstance();
		return "Fail";
	}

	public String insertBlog(HttpSession seassion, JSONObject jObj) {
		database = DataBase.getInstance();

		System.out.println("insert json :: " + jObj);
		BlogPojo blogpojo = parseJsonReturnBlogData(jObj, seassion);

		String sql = "INSERT INTO BLOG " + "(blogtext ,date1 ,username, email ,"
				+ "postTitle, postId) VALUES (?,?,?,?,?,?)";
		Object blogtext = blogpojo.getBlogText();

		// server validation
		if (blogtext.toString().length() <= 140) {

			Object date1 = new Date().toString();
			Object username = blogpojo.getUserName();
			Object email = blogpojo.getEmail();
			Object posttitile = blogpojo.getPostTitle();
			Object postid = blogpojo.getPostId();

			Object[] params = new Object[] { blogtext, date1, username, email, posttitile, postid };

			System.out.println("username : " + blogpojo.getUserName());

			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.INTEGER };
			int row = database.jdbcTemplate.update(sql, params, types);
			System.out.println(row + " row inserted.");

			if (row != 0)
				return "Success";
		} else
			return "Fail";

		return "Fail";
	}

	/*
	 * How to read json sent by ajax in servlet reference from :
	 * http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax
	 * -in-servlet Answered by : Rakesh Soni
	 */

	public BlogPojo parseJsonReturnBlogData(JSONObject jObj, HttpSession session) {
		// TODO Auto-generated method stub
		database = DataBase.getInstance();
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

		String sql = "select max(postId) from blog";

		database.postId = database.jdbcTemplate.queryForInt(sql);
		return new BlogPojo(blogText, date, postTitle, session.getAttribute("Email").toString(), ++database.postId,
				session.getAttribute("UserName").toString());
	}


}
