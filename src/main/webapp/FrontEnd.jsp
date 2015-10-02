<%@ page language="java" 
    pageEncoding="ISO-8859-1" %>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!--  HTML AND CSS Referenced from http://blog.miguelgrinberg.com/index  -->

<!DOCTYPE html >
<html>
<head>
  
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/sign.css" rel="stylesheet">

<script>

$(document).ready(function() {
	var session = $("#session").val();
	
	if(session != "notSet"){$("#f1").hide()}else{}
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogRetriveCommon",
		dataType: 'json',
		async: false,
		success: function(js){
			var blogs =  JSON.stringify(js["commonBlog"])
			var jsonParsedBlog = $.parseJSON(blogs);
			
			//http://stackoverflow.com/questions/8951810/how-to-parse-json-data-with-jquery-javascript
			
			for (var i = 0; i < jsonParsedBlog.length; i++) { 
			     
				 var url = "/Blog/Posts/";
				 var postTitle = jsonParsedBlog[i].postTitle;
				 var date = jsonParsedBlog[i].date;
				 var author = jsonParsedBlog[i].userName;
				 var postDescription = jsonParsedBlog[i].blogText;
				 var postId =  jsonParsedBlog[i].postId;
				 
				 $("#post").append('<h1 class="post-title"><a href="'+ url+ postId +'">'+postTitle+'</a></h1>');
				 $("#post").append('<p class="date"><span class="" data-refresh="0" data-format="format()" data-timestamp="2015-08-09T18:58:46Z">'+date+'</span></p>');	
				 $("#post").append('<div class="posted">Posted by <span class="label"><a href="">'+author+'</a></span</div>');
			     $("#post").append('<div class="post_body"><p>'+postDescription+' </p></div> ');
			     $("#post").append('<div style="clear: both;"><a href="'+ url+ postId+'">Read more...</a></div>');
			     $("#post").append('<br/>');	
			     $("#post").append('<br/>');
			     $("#post").append('<br/>');
		
			}
			
		 },
		error: function(jqXHR, textStatus, errorMessage){
    		alert(errorMessage);
    	}
	});
	
	$("button#Signin").click(function(){
		 
		var email = $("#inputEmail").val();
	    var password = $("#inputPassword").val();
	    
	    if(email=='' || password== '' ){
	    	alert('Please enter details');
	    	if(email==''){$("#inputEmail").focus();}
	    	if(password==''){$("#inputPassword").focus();}
	    	return false;
	    }
	    
		jsonObject = [];
		
		formData = {}
		formData ["userName"] = email;
		formData ["password"] = password;
		
		jsonObject.push(formData);
		
	     $.ajax({
	    	 	type: "POST",
				url: "/Blog/Login",
				dataType: 'json',
				async: false,
		   	 
		    	data: {jsonObject : JSON.stringify(jsonObject)},
		    	
				beforeSend: function () {
                },
				success: function(js){
			         
					var json =  JSON.stringify(js["user"])
					 window.location= "/Blog/Home.jsp";
					 return false;
					//debugger;
					
                 },
        		error: function(jqXHR, textStatus, errorMessage){
            							
            	}
              });
	     return false;
	});	
	
	
	
});
function Reset() {
   window.location= "/Blog/Signup.jsp";
}
</script>
    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS 476</title>
</head>

<body>
<nav class="navbar navbar-inverse" role="navigation">
       
        
        <div id="navbar" class="collapse navbar-collapse">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/FrontEnd.jsp">CS 476 BLOG</a>
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
            <li><a href="/Blog/SignOut">About</a></li>
          </ul>
        </div>
    	
</nav>
<br/>

	<!-- http://stackoverflow.com/questions/9707397/making-a-div-vertically-scrollable-using-css -->
	<div class="container" style="max-height: 90vh;">
	 <div class="row">
	
		<div class="col-md-4 col-md-push-9 sidebar-bg">
			<div id="sidebar">
			  <form id="f1" class="form-signin">
		        <h2 id="hd" class="form-signin-heading">Sign in</h2>
		        
		        <label for="inputEmail" class="sr-only">Email address</label>
		        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
		        <br/>
		        
		        <label for="inputPassword" class="sr-only">Password</label>
		        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
		   		 <button class="btn btn-lg btn-primary btn-block" id="Signin" type="submit">Sign in</button>
		        <div id="link">
		        	<a id="crtLink" onclick="Reset();">Create account</a>
		        </div>
		      </form>
		     </div>      
		  </div>
		  
		  <div class="col-md-8 col-md-pull-4">
			<div id ="main">
			   <div id="post" class="post">
			   </div> 
			   <div class="page">
					<ul class="pager">
					<li class="previous disabled">
					<a href="#">Newer Posts</a>
					</li>
					<li class="next">
					<a href="#">Older Posts</a>
					</li>
					</ul>
				</div>
		    </div>
		</div>
	</div>
		
		
    </div>
 
	<% if (session.getAttribute("UserName") == null) { %>
	    <input type="hidden" id="session" value="notSet"/>
	<% } else {%>
	     <input type="hidden" id="session" value="<%=session.getAttribute("UserName") %>"/>
	<% } %>

</body>
</html>