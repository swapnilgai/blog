package com.blog.login;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public interface LoginActionDAO
{
	public String validateUserAndSession(JSONObject jObj, HttpServletRequest request);
}

