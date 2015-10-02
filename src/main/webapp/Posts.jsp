<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/sign.css" rel="stylesheet">


<title>Post</title>

<script type="text/javascript">

function DeletePost(){
	
	var url = window.location.href;
	var pathSplit = window.location.pathname.split( '/' )
	var postId = pathSplit[3];
	
	formData = {}
	formData ["postId"] = postId;
	
	jsonObject = [];
	jsonObject.push(formData);
	
	$.ajax({
		type: "POST",
		url: "/Blog/Delete",
		dataType: 'json',
		data:{postId : JSON.stringify(jsonObject)},
		async: false,
		success: function(js){ 
			//alert("Hi");
			window.location.href = "/Blog/FrontEnd.jsp"
		},
		error: function(jqXHR, textStatus, errorMessage){
    		
    	}
	});
 }

</script>

<script>
$(document).ready(function() {
	
	var session = $("#session").val();
	
	var url = window.location.href;
	var pathSplit = window.location.pathname.split( '/' )
	var postId = pathSplit[3];
	
	formData = {}
	formData ["postId"] = postId;
	
	jsonObject = [];
	jsonObject.push(formData);
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogById",
		dataType: 'json',
		data:{postId : JSON.stringify(jsonObject)},
		async: false,
		success: function(js){
				
				 var blogs =  JSON.stringify(js["userBlogById"])
		         
		         if (jQuery.isEmptyObject(js["userBlogById"]))//referenced from http://stackoverflow.com/questions/14345761/how-to-check-if-json-return-is-empty-with-jquery
		         { 
		        	 window.location.href = "/Blog/FrontEnd.jsp";
		         }
		         else
		         {
			         var jsonParsedBlog = $.parseJSON(blogs);
					 var url = "/Blog/Posts/";
					
					 var postTitle = jsonParsedBlog[0].posttitle;
					 var date = jsonParsedBlog[0].date;
					 var author = jsonParsedBlog[0].userName;
					 var postDescription = jsonParsedBlog[0].blogText;
					 var postId =  jsonParsedBlog[0].postId;
					
					 var editPostUrl = "/Blog/EditPost/"+postId;
					 var Edit = "Edit";
					 
					 $("#post").append('<h1 class="post-title"><a href="'+ url+postId +'">'+postTitle+'</a></h1>');
					 $("#post").append('<p class="date"><span class="" data-refresh="0" data-format="format()" >'+date+'</span></p>');	
					 $("#post").append('<div class="posted">Posted by <span class="label"><a href="">'+author+'</a></span><span id="EditPost" class="label"><a id="Edit" href="'+editPostUrl+'">'+Edit+'</a></span> <span id="DeletePost" class="label"><a id="Delete" onclick="return DeletePost(); return false;" href="">Delete</a></span> </div>');
					 $("#post").append('<div id="postDesc" class="post_body"><p>'+postDescription+' </p></div> ');
				    
					 $("#post").append('<br/>');	
				     $("#post").append('<br/>');
				     $("#post").append('<br/>');
		        }
			//return false;
		 },
		error: function(jqXHR, textStatus, errorMessage){
    		
    	}
	});
	
	if(session == "notSet"){
		$('#EditPost').remove();
		$('#DeletePost').remove();
	}
	
		
});
</script>

</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
        <div id="navbar" class="collapse navbar-collapse">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/FrontEnd.jsp">CS 476 BLOG</a>
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
           <li><a href="/Blog/SignOut">Signout</a></li>
          
          </ul>
        </div>
    	
</nav>


<div class="container" style="max-height: 90vh;">
	 <div class="row">
	 <div class="col-md-4 col-md-push-9 sidebar-bg">
	 </div>
		 <div class="col-md-8 col-md-pull-4">
				<div id="main" >
					<div id="post" class="post">
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