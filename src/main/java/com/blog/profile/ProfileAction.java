package com.blog.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.blog.database.DataBase;
import com.blog.database.RetriveData;

public class ProfileAction {

	RetriveData retrivedata= new RetriveData();
	
    public JSONObject getUserData(HttpSession session)
    {
    	return retrivedata.getUserData(session);
    }
    
    public String checkOs(String imageUrl)
    {
    	String temp = System.getProperty("os.name");
    	if(temp.contains("Mac"));
    	   imageUrl="file://"+imageUrl;
    	
    	return imageUrl;
    }
    
    public String uploadProfileImage(HttpServletRequest request)
    {
    	UploadProfileImage uploadimage = new UploadProfileImage();
    	String imageUrl = uploadimage.uploadImage(request);
    	DataBase.imageUrl.put(DataBase.season.get(request.getSession()), imageUrl);
    	imageUrl = checkOs(imageUrl);
    	
    	return imageUrl;
    }
    
    public void downlodProfileImage(HttpServletRequest request, HttpServletResponse response)
    {
    	downloadProfilePic download =new downloadProfilePic();
    	download.downloadImageClientSide(request, response);
    } 
}
