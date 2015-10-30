<%@ page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<head>
<!-- Bootstrap framework(v3.3.5) referenced from (http://getbootstrap.com/), 
JQuery(v1.11.3) referenced from (http://jqueryui.com) used in below script -->
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Courgette">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Playball" />

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

<script>
$(document).ready(function() {

	var session = $("#session").val();
	if(session == "notSet"){
		alert("Please Sign in first");	
		window.location.href= "FrontEnd.jsp";
	}
	
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogRetriveUser",
		dataType: 'json',
		async: false,
		success: function(js){
		
			var blogs =  JSON.stringify(js["userBlog"])
			var jsonParsedBlog = $.parseJSON(blogs);
			
			//Json parsing data refered from http://stackoverflow.com/questions/8951810/how-to-parse-json-data-with-jquery-javascript
			//answered by Mohammed Abdelrasoul
	
			for (var i = 0; i < jsonParsedBlog.length; i++) { 
			     
				 var url = "/Blog/Posts/";
				 var date = jsonParsedBlog[i].date;
				 var postTitle = jsonParsedBlog[i].postTitle;
				 var author = jsonParsedBlog[i].userName;
				 var postDescription = jsonParsedBlog[i].blogText;
				 var postId =  jsonParsedBlog[i].postId;
				 var editPostUrl = "/Blog/EditPost/"+postId;
				 var Edit = "Edit";
				 var ImageUrl = "/Blog/imagesu.jpeg"
				 
				 $("#post").append('<div  class="post_body"><img src="'+ImageUrl+'">');
				 $("#post").append('<h1 style="font-family:Courgette;color:#141466;" class="post-title"><a href="'+ url+postId+'">'+postTitle+'</a></h1>');
				 $("#post").append('<p class="date"><span class="">'+date+'</span></p>');	
				 $("#post").append('<div class="posted">Posted by <span class="label"><a  href="">'+author+'</a></span></div>');
				 $("#post").append('<div style="font-family:Courgette;color:#141466; font-size: 25px;" class="post_body"><p>'+postDescription+'</p></div> ');
			     $("#post").append('<div style="clear: both;"><a href="'+ url+postId+'">Read more...</a></div>');
			     
			     $("#post").append('<br/>');	
			     $("#post").append('<br/>');
			     $("#post").append('<br/>');
		 
			}
			
			if(jsonParsedBlog.length == 0){
				($("#hd").append("You don't have any post on this website.please use create post link above to have your own post. or check posts by other users on your front page "));
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
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/FrontEnd.jsp">Blogger
				v. 1.0</a>
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/FrontEnd.jsp">Front
						Page</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/Newpost.jsp">Create
						post</a></li>
				<li><a href="/Blog/SignOut">Signout</a></li>

			</ul>
		</div>

	</nav>


	<div class="container" style="max-height: 90vh;">

		<br />
		<h2 id="hd"
			style="font-family: Playball; color: #191975; padding: 20px; font-size: 30px;">
			Welcome
			<%=session.getAttribute("UserName") %>
			<p>Welcome to Blogger v. 1.0 !!!</p>
		</h2>
		<br />
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