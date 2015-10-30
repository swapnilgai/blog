package com.blog.database;

import java.util.Date;
import org.json.JSONObject;


public class UpdateBlog {

	DataBase database = null;

	public UpdateBlog() {
		// TODO Auto-generated constructor stub
		this.database = DataBase.getInstance();
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

	public String UpdateBlogByID(JSONObject jObj) {
		try {
			String data[] = parseJsonReturnBlogData(jObj);

			System.out.println("in updateeeeeeeeeeeeeeeeeeeeeeeeeee ******************");

			int pId = Integer.parseInt(data[1]);

			System.out.println("pid ::::    " + pId);
			String date = new Date().toString();

			String sql = "UPDATE BLOG " + "SET BLOGTEXT='" + data[2] + "'," + " POSTTITLE='" + data[0] + "', DATE1='"
					+ date + "' WHERE postId='" + pId + "'";

			System.out.println(sql);
			database.jdbcTemplate.update(sql);

		} catch (Exception e) {
			return null;
		}
		return "Success";
	}
}
