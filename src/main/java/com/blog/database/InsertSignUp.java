package com.blog.database;

import java.sql.Types;
import javax.servlet.http.HttpServletRequest;
import com.blog.signup.SignUpPojo;

public class InsertSignUp {

	public String insertSignUpPojo(HttpServletRequest request, SignUpPojo signuppojo) {
		DataBase database;
		database = DataBase.getInstance();

		String sql1 = "INSERT INTO SIGNUP" + "(" + " username, " + " pass, " + " email, " + " dob," + " photourl)"
				+ "VALUES (?, ?, ?, ?, ?)";

		Object username = signuppojo.getUserName();
		Object pass = signuppojo.getPassword();
		Object email = signuppojo.getEmail();
		Object dob = signuppojo.getbDate();
		Object photourl = signuppojo.getPhotoURl();
		Object[] params = new Object[] { username, pass, email, dob, photourl };

		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		int row = database.jdbcTemplate.update(sql1, params, types);
		System.out.println(row + " row inserted.");

		return signuppojo.getUserName();
	}
}
