package com.blog.database;

import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import com.blog.blogdata.BlogPojo;
import com.blog.signup.SignUpPojo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class DataBase {

	static public  int postId;
	static HashMap<String, String> userCred = new HashMap<String, String>();
	static public HashMap<HttpSession, String> season = new HashMap<HttpSession, String>();
	static public HashMap<String, String>imageUrl = new HashMap<String, String>(); 
	public static Multimap<String, BlogPojo>userBlog =ArrayListMultimap.create();
	public static HashMap<String, String>timestampPostId = new HashMap<String, String>(); 
	public static HashMap<String, SignUpPojo> userData = new HashMap<String, SignUpPojo>();

	static Multimap<String, String>userTimestamp =ArrayListMultimap.create();
	
 static {
		// TODO Auto-generated constructor stub
		String str = "admin@yahoo.com";
	
	 for (int i = 0; i < 10; i++) {
			Date date = new Date();
			BlogPojo blogtemp = new BlogPojo("heloo blog text heloo blog textheloo blog text"
					+ "heloo blog textheloo blog textheloo blog textheloo blog text"
					+ "heloo blog textheloo blog textheloo blog textheloo blog textheloo blog text"
					+ "heloo blog textheloo blog textheloo blog textheloo blog text"
					+ "heloo blog textheloo blog textheloo blog textheloo blog text"
					+ "heloo blog textheloo blog textheloo blog textheloo blog textheloo blog text"
					+ "heloo blog text"+i, ""+date,
					"postTitle"+i, str, postId);
			userBlog.put(blogtemp.getDate(),blogtemp);
			userTimestamp.put(str, blogtemp.getDate());
			timestampPostId.put(""+postId, ""+date);
			postId++;
			userCred.put(str, "admin");
			if(i>2)
				str = str +i;
			   
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}






