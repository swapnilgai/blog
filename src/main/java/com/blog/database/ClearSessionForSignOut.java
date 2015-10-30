package com.blog.database;

import javax.servlet.http.HttpSession;

public class ClearSessionForSignOut {

	DataBase database= null;
	public void ClearSession(HttpSession session)
	{
		database= DataBase.getInstance();	
		//database.session.remove(session);
	}
}
