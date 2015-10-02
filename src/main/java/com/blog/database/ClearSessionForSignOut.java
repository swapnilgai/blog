package com.blog.database;

import javax.servlet.http.HttpSession;

public class ClearSessionForSignOut {

	public void ClearSession(HttpSession session)
	{
		DataBase.season.remove(session);
	}
}
