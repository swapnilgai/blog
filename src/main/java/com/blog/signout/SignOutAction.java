package com.blog.signout;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.blog.database.ClearSessionForSignOut;

public class SignOutAction extends JdbcDaoSupport implements SignOutActionDAO {

	public String deleteSession(HttpServletRequest request) {
		try {

			ClearSessionForSignOut clearsession = new ClearSessionForSignOut();
			clearsession.ClearSession(request.getSession());

		} catch (Exception e) {
			return "Fail";
		}
		return "Success";
	}
}
