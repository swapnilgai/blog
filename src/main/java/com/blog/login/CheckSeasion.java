package com.blog.login;

import javax.servlet.http.HttpSession;

import com.blog.database.DataBase;

public class CheckSeasion {

	DataBase database = null;

	public String validateSeassion(LoginPojo loginpojo, HttpSession session, String userName) {
		database = DataBase.getInstance();

		if (session.getAttribute("UserName") != null)
			return loginpojo.getUserName();
		else {
			session.setAttribute("UserName", userName);
			session.setAttribute("Email", loginpojo.getUserName());
			return loginpojo.getUserName();
		}
	}
}
