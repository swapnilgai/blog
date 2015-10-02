<%@ page language="java"  pageEncoding="ISO-8859-1"
import="com.google.gson.JsonIOException"
import= "com.google.gson.JsonObject"
import= "com.google.gson.JsonParser"
import="com.google.gson.JsonArray"
    %>

<!--  HTML AND CSS Referenced from http://blog.miguelgrinberg.com/index -->
    
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

	
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogRetriveUser",
		dataType: 'json',
		async: false,
		success: function(js){
		
			var blogs =  JSON.stringify(js["userBlog"])
			var jsonParsedBlog = $.parseJSON(blogs);
			//http://stackoverflow.com/questions/8951810/how-to-parse-json-data-with-jquery-javascript
			
			for (var i = 0; i < jsonParsedBlog.length; i++) { 
			     
				 var url = "/Blog/Posts/";
				 var date = jsonParsedBlog[i].date;
				 var postTitle = jsonParsedBlog[i].posttitle;
				 var author = jsonParsedBlog[i].userName;
				 var postDescription = jsonParsedBlog[i].blogText;
				 var postId =  jsonParsedBlog[i].postId;
				 var editPostUrl = "/Blog/EditPost/"+postId;
				 var Edit = "Edit";
				 
				 $("#post").append('<h1 class="post-title"><a href="'+ url+postId+'">'+postTitle+'</a></h1>');
				 $("#post").append('<p class="date"><span class="" data-refresh="0" data-format="format()" data-timestamp="2015-08-09T18:58:46Z">'+date+'</span></p>');	
				 $("#post").append('<div class="posted">Posted by <span class="label"><a  href="">'+author+'</a></span></div>');
				 $("#post").append('<div class="post_body"><p>'+postDescription+' </p></div> ');
			     $("#post").append('<div style="clear: both;"><a href="'+ url+postId+'">Read more...</a></div>');
			     
			     $("#post").append('<br/>');	
			     $("#post").append('<br/>');
			     $("#post").append('<br/>');
		 
			}
			
		 },
		error: function(jqXHR, textStatus, errorMessage){
    		//alert(errorMessage);
    	}
	});
		
});
</script>


</head>
<body>

<nav class="navbar navbar-inverse" role="navigation">
        <div id="navbar" class="collapse navbar-collapse">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/FrontEnd.jsp">CS 476 BLOG</a>
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/Newpost.jsp">Create post</a></li>
            <li><a href="/Blog/SignOut">Signout</a></li>
            
          </ul>
        </div>
    	
</nav>


<div class="container" style="max-height: 90vh;">
	 <div class="row">
	 <div class="col-md-4 col-md-push-9 sidebar-bg">
	 </div>
		 <div class="col-md-8 col-md-pull-4">
			<div id ="main">
			   <div id="post" class="post">
			   </div>
		    </div>
		</div>
	</div>
</div>

</body>
</html>