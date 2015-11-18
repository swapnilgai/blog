package com.blog.secondaryservices;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class DataBase{
	

	public static int postId;
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DataBase(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		
		
		String sql = "CREATE TABLE IF NOT EXISTS SIGNUP"
				+ "(username varchar(255) NOT NULL,pass varchar(255) NOT NULL,email varchar(255) PRIMARY KEY,"
				+ "dob varchar(255),photourl varchar(255))";

		jdbcTemplate.execute(sql);
			
		 sql = "CREATE TABLE IF NOT EXISTS BLOG "
				+ "(blogtext varchar(255), date1 varchar(255),username varchar (255), email varchar (255),"
				+ "postTitle varchar (255), postId int UNIQUE,FOREIGN KEY (email) REFERENCES SIGNUP (email) )";
	 
		jdbcTemplate.execute(sql);		
	}
}
