package com.blog.secondaryservices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

public class ClearSessionForSignOut {

	public void ClearSession(HttpSession session)
	{		
		session.removeAttribute("Email");
     	session.removeAttribute("UserName");
     	session.invalidate();
	}
}
