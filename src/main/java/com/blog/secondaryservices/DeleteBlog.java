package com.blog.secondaryservices;

import org.springframework.jdbc.core.JdbcTemplate;

/*
 *  Remove and put back elements from Multimap while iterating
 *  reference from : http://stackoverflow.com/questions/16633985/java-guava-remove-and-put-back-elements-from-multimap-while-iterating
 * 	Asked By: user26372 
 */

public class DeleteBlog {
	DataBase database;

	public String DeleteBlogByID(int postId, JdbcTemplate jdbcTemplate) {

		String sql = "DELETE FROM BLOG " + "WHERE POSTID='" + postId + "'";

		jdbcTemplate.execute(sql);

		return "Success";
	}
}