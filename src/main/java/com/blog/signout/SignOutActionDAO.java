package com.blog.signout;

import javax.servlet.http.HttpServletRequest;

public interface SignOutActionDAO {

	public String deleteSession(HttpServletRequest request);

}
