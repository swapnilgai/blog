package com.blog.secondaryservices;

import java.sql.Types;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;

import com.blog.entity.SignUpPojo;

public class InsertSignUp {

	public String insertSignUpPojo(HttpServletRequest request, SignUpPojo signuppojo, JdbcTemplate jdbcTemplate) {
		DataBase database;
		
		String sql1 = "select count(username) from signup where email = '"+signuppojo.getEmail()+"'";
		
		if(jdbcTemplate.queryForInt(sql1)==0)
		{
		 sql1 = "INSERT INTO SIGNUP" + "(" + " username, " + " pass, " + " email, " + " dob," + " photourl)"
				+ "VALUES (?, ?, ?, ?, ?)";
		
		Object username = signuppojo.getUserName();
		Object pass = signuppojo.getPassword();
		Object email = signuppojo.getEmail();
		Object dob = signuppojo.getbDate();
		Object photourl = signuppojo.getPhotoURl();
		Object[] params = new Object[] { username, pass, email, dob, photourl };

		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		int row = jdbcTemplate.update(sql1, params, types);
		System.out.println(row + " row inserted.");

		return signuppojo.getUserName();
		}
		else 
			return "Invalid Email";
		}
}
