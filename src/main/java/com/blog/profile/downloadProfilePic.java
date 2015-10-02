package com.blog.profile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.database.DataBase;

public class downloadProfilePic {

	public void downloadImageClientSide(HttpServletRequest request,HttpServletResponse response)
	{
		//ref : http://stackoverflow.com/questions/5509328/display-image-in-jsp-page-from-servlet
		
		try{
	        // String fileName = request.getParameter("image");             
	        
	         File file = new File(DataBase.imageUrl.get(
	        		 DataBase.season.get(request.getSession())));
	         FileInputStream fis = new FileInputStream(file);
	         BufferedInputStream bis = new BufferedInputStream(fis);             
	         response.setContentType("image/jpeg");
	         BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
	         for (int data; (data = bis.read()) > -1;) 
	         {
	        //  System.out.println("avb : "+bis.available());
	        	 output.write(data);
	         }             
	         
//	         byte[] imageBytes = new byte[(int) file.length()];
//
//	         fis.read(imageBytes);
//	         response.setContentType("image/jpeg");
//	         response.setContentLength(imageBytes.length);
//
//	         response.getOutputStream().write(imageBytes);
	         
	         
	      }
	      catch(IOException e){

	      }finally{
	          // close the streams
	      }
	}
	
	
}
