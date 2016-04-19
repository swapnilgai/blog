package com.blog.secondaryservices;

import javax.servlet.http.HttpSession;

import com.blog.entity.LoginPojo;

public class CheckSeasion {


	public String validateSeassion(LoginPojo loginpojo, HttpSession session, String userName) {

		if (session.getAttribute("UserName") != null)
			return loginpojo.getUserName();
		else {
			session.setAttribute("UserName", userName);
			session.setAttribute("Email", loginpojo.getUserName());
			return loginpojo.getUserName();
		}
	}
}
