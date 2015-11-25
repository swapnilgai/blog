package com.blog.primaryservices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.TransactionManager;

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
import org.springframework.stereotype.Repository;

public class BlogAction  implements BlogActionDAO {

	//JdbcTemplate jdbcTemplate;
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	public JSONObject getUserBlogById(HttpServletRequest request) {

		JsonOperations jsonoperation = new JsonOperations();
		RetriveData retrive = new RetriveData();

		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);
		setEntityManager();
		return retrive.getUserBlogByID(request.getSession(), postId, entityManager);
	}

	public String insertNewBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		InsertData insertdata = new InsertData();
		setEntityManager();
		if (insertdata.insertBlog(request.getSession(), jObj, entityManager) == "Success")
			return "Success";

		return "Fail";
	}

	public JSONObject getCommonBlogs() {
		RetriveData retriveData = new RetriveData();
		setEntityManager();
		return retriveData.getCommonBlog(entityManager);
	}

	public JSONObject getUserBlogs(HttpServletRequest request) {
		RetriveData retriveData = new RetriveData();
		setEntityManager();
		return retriveData.getUserBlog(request.getSession(), entityManager);
	}

	public String DeleteBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		DeleteBlog deleteblog = new DeleteBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("postId").toString());
		int postId = jsonoperation.parseJsonByID(jObj);
		setEntityManager();
		return deleteblog.DeleteBlogByID(postId, entityManager);
	}

	public String UpdateBlog(HttpServletRequest request) {
		JsonOperations jsonoperation = new JsonOperations();
		com.blog.secondaryservices.UpdateBlog updateblog = new com.blog.secondaryservices.UpdateBlog();
		JSONObject jObj = jsonoperation.formatObject(request.getParameter("jsonObject").toString());
		setEntityManager();
		return updateblog.UpdateBlogByID(jObj, entityManager, request.getSession());
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	private void setEntityManager() {
		// TODO Auto-generated method stub
		entityManager=entityManagerFactory.createEntityManager();
	}
}
