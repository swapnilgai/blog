package com.blog.dao;

import javax.servlet.http.HttpServletRequest;

import com.blog.entity.SignUpPojo;

public interface SignUpActionDAO
{
	public SignUpPojo parseJsonToSignUp(HttpServletRequest request);
	
	public String insertInDatabase(HttpServletRequest request);
	
}
