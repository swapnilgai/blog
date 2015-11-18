package com.blog.primaryservices;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.blog.dao.BlogActionDAO;
import com.blog.secondaryservices.DataBase;
import com.blog.secondaryservices.DeleteBlog;
import com.blog.secondaryservices.InsertData;
import com.blog.secondaryservices.JsonOperations;
import com.blog.secondaryservices.RetriveData;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BlogAction  implements BlogActionDAO {

	JdbcTemplate jdbcTemplate;
	public BlogAction() {
		// TODO Auto-generated constructor stub
		
	}

	public JSONObject getUserBlogById(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		RetriveData retrive = new RetriveData();

		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);
		System.out.println(postId);
		return retrive.getUserBlogByID(request.getSession(), postId, jdbcTemplate);
	}

	public String insertNewBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		InsertData insertdata = new InsertData();
        System.out.println("Jdbc :  "+jdbcTemplate);
		if (insertdata.insertBlog(request.getSession(), jObj, jdbcTemplate) == "Success")
			return "Success";

		return "Fail";
	}

	public JSONObject getCommonBlogs() {
		RetriveData retriveData = new RetriveData();
		return retriveData.getCommonBlog(jdbcTemplate);
	}

	public JSONObject getUserBlogs(HttpServletRequest request) {
		RetriveData retriveData = new RetriveData();
		return retriveData.getUserBlog(request.getSession(), jdbcTemplate);
	}

	public String DeleteBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		DeleteBlog deleteblog = new DeleteBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);

		return deleteblog.DeleteBlogByID(postId, jdbcTemplate);
	}

	public String UpdateBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		com.blog.secondaryservices.UpdateBlog updateblog = new com.blog.secondaryservices.UpdateBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		return updateblog.UpdateBlogByID(jObj, jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
