 <%@page import="java.awt.Window"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String url= null;%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>
 
    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="/Blog/Upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>          
        </div>
        <%//"file://"+request.getAttribute("file");%>
        
        <% 
        	if(request.getAttribute("file")!=null)
        	url = request.getAttribute("file").toString(); 
        	else
        		url = "imagesu.jpeg";
        %>
      <img src=<%=url %> alt="Smiley face" width="242" height="242">      
    <% 
//System.out.println("in jsppppp :"+request.getParameter("file"));
   
    
    System.out.println("in jsppppp :"+request.getAttribute("file"));
String path = "";
if(request.getAttribute("file")!=null)
    path = request.getAttribute("file").toString();

%>
    
    </body>
</html>