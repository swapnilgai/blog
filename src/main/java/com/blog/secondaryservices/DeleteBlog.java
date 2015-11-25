package com.blog.secondaryservices;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.JdbcTemplate;

/*
 *  Remove and put back elements from Multimap while iterating
 *  reference from : http://stackoverflow.com/questions/16633985/java-guava-remove-and-put-back-elements-from-multimap-while-iterating
 * 	Asked By: user26372 
 */

public class DeleteBlog {
	DataBase database;

	public String DeleteBlogByID(int postId, EntityManager entityManager) {

		String sql = "DELETE FROM BlogPojo " + "WHERE POSTID='" + postId + "'";
		entityManager.getTransaction().begin();
		int row=entityManager.createQuery(sql).executeUpdate(); 
		entityManager.getTransaction().commit();
		if(row!=0)
			return "Success";

		return "Fail";
	}
}