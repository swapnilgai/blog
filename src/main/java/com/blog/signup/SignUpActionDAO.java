package com.blog.signup;

import javax.servlet.http.HttpServletRequest;

public interface SignUpActionDAO
{

	public SignUpPojo parseJsonToSignUp(HttpServletRequest request);
	
	public String insertInDatabase(HttpServletRequest request);
	
}
