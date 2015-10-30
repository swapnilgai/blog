<%@ page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<!-- Bootstrap framework(v3.3.5) referenced from (http://getbootstrap.com/), 
JQuery(v1.11.3) referenced from (http://jqueryui.com) used in below script -->
<head>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Courgette">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery-ui.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<!-- Sign.css adjusts width of signin buttons, also draws border line  -->
<link href="${pageContext.request.contextPath}/bootstrap/css/sign.css"
	rel="stylesheet">

<style>
body {
	font-family: 'Courgette', serif;
}
</style>

<title>Post</title>

<script type="text/javascript">

function DeletePost(){
	
	var url = window.location.href;
	var pathSplit = window.location.pathname.split( '/' )
	var postId = pathSplit[3];
	
	PostData = {}
	PostData ["postId"] = postId;
	
	jsonObject = [];
	jsonObject.push(PostData);
	
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
	if(session == "notSet"){
		$("#HomeLink").hide();	
		 $("#signout").hide();
	}
	
	var url = window.location.href;
	
	// below line for getting url path refered from http://stackoverflow.com/questions/2668005/jquery-get-url-path
	//answered by Mario Menger
	var pathSplit = window.location.pathname.split( '/' )
	var postId = pathSplit[3];
	
	PostData = {}
	PostData ["postId"] = postId;
	
	jsonObject = [];
	jsonObject.push(PostData);
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogById",
		dataType: 'json',
		data:{postId : JSON.stringify(jsonObject)},
		async: false,
		success: function(js){
				
				 var blogs =  JSON.stringify(js["userBlogById"])
		        
				 //below code for checking if json is empty is refered from http://stackoverflow.com/questions/14345761/how-to-check-if-json-return-is-empty-with-jquery
		         // answered by Arun Pratap Singh
				 if (jQuery.isEmptyObject(js["userBlogById"]))
		         { 
		        	 window.location.href = "/Blog/FrontEnd.jsp";
		         }
		         else
		         {
			         var jsonParsedBlog = $.parseJSON(blogs);
					 var url = "/Blog/Posts/";
					
					 var postTitle = jsonParsedBlog[0].postTitle;
					 var date = jsonParsedBlog[0].date;
					 var author = jsonParsedBlog[0].userName;
					 var postDescription = jsonParsedBlog[0].blogText;
					 var postId =  jsonParsedBlog[0].postId;
					
					 var editPostUrl = "/Blog/EditPost/"+postId;
					 var Edit = "Edit";
					 
					 $("#post").append('<h1 style="font-family:Courgette;color:#141466;" class="post-title"><a href="'+ url+postId +'">'+postTitle+'</a></h1>');
					 $("#post").append('<p class="date"><span class="" >'+date+'</span></p>');	
					 $("#post").append('<div style="font-family:Courgette;color:#141466;" class="posted">Posted by <span class="label"><a href="">'+author+'</a></span><span id="EditPost" class="label"><a id="Edit" href="'+editPostUrl+'">'+Edit+'</a></span> <span id="DeletePost" class="label"><a id="Delete" onclick="return DeletePost(); return false;" href="">Delete</a></span> </div>');
					 $("#post").append('<div style="font-family:Courgette;color:#141466;" id="postDesc" class="post_body"><p>'+postDescription+' </p></div> ');
				    
					 $("#post").append('<br/>');	
				     $("#post").append('<br/>');
				     $("#post").append('<br/>');
				     

				 	if(session != author){
				 		$('#EditPost').remove();
				 		$('#DeletePost').remove();
				 	}
		        }
			//return false;
		 },
		error: function(jqXHR, textStatus, errorMessage){
    		
    	}
	});
	
	//alert(session);
	
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
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/FrontEnd.jsp">Blogger
				v. 1.0</a>
			<ul class="nav navbar-nav">
				<li id="HomeLink" class="active"><a
					href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
				<li><a id="signout" href="/Blog/SignOut">Signout</a></li>
			</ul>
		</div>

	</nav>


	<div class="container" style="max-height: 90vh;">
		<div class="row">
			<div class="col-md-4 col-md-push-9 sidebar-bg"></div>
			<div class="col-md-8 col-md-pull-4">
				<div id="main">
					<div id="post" class="post"></div>
				</div>

			</div>
		</div>
	</div>


	<% if (session.getAttribute("UserName") == null) { %>
	<input type="hidden" id="session" value="notSet" />
	<% } else {%>
	<input type="hidden" id="session"
		value="<%=session.getAttribute("UserName") %>" />
	<% } %>

</body>
</html>