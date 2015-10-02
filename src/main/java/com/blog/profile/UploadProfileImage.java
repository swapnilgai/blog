package com.blog.profile;



import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.blog.database.DataBase;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */


//ref: http://www.javacodegeeks.com/2013/08/file-upload-example-in-servlet-and-jsp.html
//ref : http://stackoverflow.com/questions/585534/what-is-the-best-way-to-find-the-users-home-directory-in-java

public class UploadProfileImage {
	
	    private final String UPLOAD_DIRECTORY = ""+System.getProperty("user.home")+"/blogs";
	 
	    private String imageUrl;
	    
	    public String uploadImage(HttpServletRequest request){  
	        //process only if its multipart content
	    	new File(UPLOAD_DIRECTORY).mkdir();
	    	System.out.println("working dir ::::    "+System.getProperty("user.home"));
	    	
	    	if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List<FileItem> multiparts = new ServletFileUpload(
	                                         new DiskFileItemFactory()).parseRequest(request);
	              File file = null;
	                
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                        imageUrl=UPLOAD_DIRECTORY + File.separator + "images.gif";
	                        file = new File(imageUrl);
	                        item.write(file);
	                    }
	                }
	           
	               //File uploaded successfully
	              
	            } catch (Exception ex) {
	              // request.setAttribute("FrofilePic", "File Upload Failed due to " + ex);
	            }          
	        }else{
	            //request.setAttribute("message",
	              //                   "Sorry this Servlet only handles file upload request");
	        }
//	        request.getRequestDispatcher("/result.jsp").forward(request, response);
	        SetUploadImagePath(imageUrl, request.getSession());
	    	return imageUrl;
	    }
	    
	    public void SetUploadImagePath(String imageUrl, HttpSession session)
	    {
	    	DataBase.imageUrl.put(DataBase.season.get(session), imageUrl);
	    }
}
	
	

