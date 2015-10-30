package com.blog.blogdata;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import com.blog.database.DataBase;
import com.blog.database.DeleteBlog;
import com.blog.database.InsertData;
import com.blog.database.RetriveData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BlogAction extends JdbcDaoSupport implements BlogActionDAO {

	DataBase database;

	public BlogAction() {
		// TODO Auto-generated constructor stub
		database = DataBase.getInstance();
	}

	public JSONObject getUserBlogById(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		RetriveData retrive = new RetriveData();

		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);
		System.out.println(postId);
		return retrive.getUserBlogByID(request.getSession(), postId);
	}

	public String insertNewBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		InsertData insertdata = new InsertData();

		if (insertdata.insertBlog(request.getSession(), jObj) == "Success")
			return "Success";

		return "Fail";
	}

	public JSONObject getCommonBlogs() {
		RetriveData retriveData = new RetriveData();
		return retriveData.getCommonBlog();
	}

	public JSONObject getUserBlogs(HttpServletRequest request) {
		RetriveData retriveData = new RetriveData();
		return retriveData.getUserBlog(request.getSession());
	}

	public String DeleteBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		DeleteBlog deleteblog = new DeleteBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);

		return deleteblog.DeleteBlogByID(postId);
	}

	public String UpdateBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		com.blog.database.UpdateBlog updateblog = new com.blog.database.UpdateBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		return updateblog.UpdateBlogByID(jObj);
	}

}
