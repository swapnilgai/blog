package com.blog.database;

/*
 *  Remove and put back elements from Multimap while iterating
 *  reference from : http://stackoverflow.com/questions/16633985/java-guava-remove-and-put-back-elements-from-multimap-while-iterating
 * 	Asked By: user26372 
 */

public class DeleteBlog {
	DataBase database;

	public String DeleteBlogByID(int postId) {

		database = DataBase.getInstance();
		String sql = " DELETE FROM BLOG " + "WHERE POSTID='" + postId + "'";

		database.jdbcTemplate.execute(sql);

		return "Success";
	}
}