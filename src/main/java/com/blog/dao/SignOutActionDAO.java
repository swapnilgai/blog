package com.blog.dao;

import javax.servlet.http.HttpServletRequest;

public interface SignOutActionDAO {

	public String deleteSession(HttpServletRequest request);

}
