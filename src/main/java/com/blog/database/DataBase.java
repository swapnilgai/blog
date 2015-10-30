package com.blog.database;

import javax.sql.DataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class DataBase {
	static DataBase database;

	public int postId;
	public DataSource dataSource;
	public JdbcTemplate jdbcTemplate;
	public ApplicationContext context;
	
	public DataBase() {
		// TODO Auto-generated constructor stub
		this.context = new ClassPathXmlApplicationContext("beans.xml");
		this.dataSource = (DataSource) context.getBean("dataSource");
		this.jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
				 
		String sql = "CREATE TABLE IF NOT EXISTS SIGNUP"
				+ "(username varchar(255) NOT NULL,pass varchar(255) NOT NULL,email varchar(255) PRIMARY KEY,"
				+ "dob varchar(255),photourl varchar(255))";

		jdbcTemplate.execute(sql);
			
		 sql = "CREATE TABLE IF NOT EXISTS BLOG "
				+ "(blogtext varchar(255), date1 varchar(255),username varchar (255), email varchar (255),"
				+ "postTitle varchar (255), postId int UNIQUE,FOREIGN KEY (email) REFERENCES SIGNUP (email) )";
	 
		jdbcTemplate.execute(sql);	
	}

	public static DataBase getInstance() {
		if (database == null) {
			database = new DataBase();
		}
		return database;
	}

	
}
