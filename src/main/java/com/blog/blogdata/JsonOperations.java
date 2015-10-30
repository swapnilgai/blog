package com.blog.blogdata;

import org.json.JSONObject;

public class JsonOperations {

	public JSONObject formatObject(String string)
	{
		String jsonTemp = string;
		jsonTemp=jsonTemp.replace("[", "");
		jsonTemp=jsonTemp.replace("]", "");	
		return new JSONObject(jsonTemp);
	}	
	

	/*
	 * How to read json sent by ajax in servlet
	 * reference from : http://stackoverflow.com/questions/19568142/how-to-read-json-sent-by-ajax-in-servlet
	 * Answered by : Rakesh Soni
	 */
	
	public int parseJsonByID(JSONObject jObj)
	{
		java.util.Iterator it = jObj.keys(); //gets all the keys	
        int postId;
		
		String key = ""+it.next(); // get key
		postId=Integer.parseInt(""+jObj.get(key));
		    
		System.out.println("JSon blogT : "+postId);
	
		return postId;
	}
}
