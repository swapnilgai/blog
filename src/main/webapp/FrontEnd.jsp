<%@ page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<head>
<!-- Bootstrap framework(v3.3.5) referenced from (http://getbootstrap.com/), 
JQuery(v1.11.3) referenced from (http://jqueryui.com) used in below script -->
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

<script>
$(document).ready(function() {
	var session = $("#session").val();
	
	/* For hiding signin form */
	if(session != "notSet"){
		$("#f1").hide();
		$("#HomeLink").attr('href', '/Blog/Home.jsp');
	}
	
	/* For hiding signout link */
	if(session == "notSet"){
		$("#HomeLink").hide();	
		$("#signout").hide();
	}
	
	$("#HomeLink").click (function(){
		if(session == "notSet"){
			alert("Please signin first");
		}
	});
	
	
	$.ajax({
		type: "POST",
		url: "/Blog/BlogRetriveCommon",
		dataType: 'json',
		async: false,
		success: function(js){
			var blogs =  JSON.stringify(js["commonBlog"])
			var jsonParsedBlog = $.parseJSON(blogs);
			
			//Json parsing data refered from http://stackoverflow.com/questions/8951810/how-to-parse-json-data-with-jquery-javascript
			//answered by Mohammed Abdelrasoul
			
			for (var i = 0; i < jsonParsedBlog.length; i++) { 
			     
				 var url = "/Blog/Posts/";
				 var postTitle = jsonParsedBlog[i].postTitle;
				 var date = jsonParsedBlog[i].date;
				 var author = jsonParsedBlog[i].userName;
				 var postDescription = jsonParsedBlog[i].blogText;
				 var postId =  jsonParsedBlog[i].postId;
				 var ImageUrl = "/Blog/imagesu.jpeg"
				 
				 $("#post").append('<div style="font-family:Courgette;color:#141466;" class="post_body"><img src="'+ImageUrl+'">');
				 $("#post").append('<h1 style="font-family:Courgette;color:#141466;" class="post-title"><a href="'+ url+ postId +'">'+postTitle+'</a></h1>');
				 $("#post").append('<p class="date"><span class="">'+date+'</span></p>');	
				 $("#post").append('<div style="font-family:Courgette;color:#141466;" class="posted">Posted by <span class="label"><a href="">'+author+'</a></span</div>');
			     $("#post").append('<div style="font-family:Courgette;color:#141466;"  class="post_body"><p>'+postDescription+' </p></div> ');
			     $("#post").append('<div style="font-family:Courgette;color:#141466;" style="clear: both;"><a href="'+ url+ postId+'">Read more...</a></div>');
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
		
		SignupData = {}
		SignupData ["userName"] = email;
		SignupData ["password"] = password;
		
		jsonObject.push(SignupData);
		
	     $.ajax({
	    	 	type: "POST",
				url: "/Blog/Login",
				dataType: 'json',
				async: false,
		   	 
		    	data: {jsonObject : JSON.stringify(jsonObject)},
		    	
				beforeSend: function () {
                },
				success: function(js){
					var json =  JSON.stringify(js["user"]);
					var jsonParsedBlog = $.parseJSON(json);
					if(jsonParsedBlog == "fail"){
						alert("Login Failed,Please enter correct username.. ");
					}else{
						window.location= "/Blog/Home.jsp";
							
					}
					 return false;
					//debugger;
					
                 },
        		error: function(jqXHR, textStatus, errorMessage){
            							
            	}
              });
	     return false;
	});	
	
	
	
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blogger v. 1.0</title>
</head>

<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div id="navbar" class="collapse navbar-collapse">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/FrontEnd.jsp">Blogger
				v. 1.0</a>
			<ul class="nav navbar-nav">
				<li class="active"><a id="HomeLink" href="">Home</a></li>
				<li><a id="signout" href="/Blog/SignOut">Signout</a></li>
				<li><a href="/Blog/About.jsp">About</a></li>
			</ul>
		</div>

	</nav>
	<br />

	<div class="container" style="max-height: 90vh;">
		<div class="row">

			<div class="col-md-4 col-md-push-9">
				<div id="sidebar">
					<form id="f1" class="form-signin">
						<h2 id="hd" class="form-signin-heading">Sign in</h2>

						<input type="email" id="inputEmail" class="form-control"
							placeholder="Email address" required autofocus> <br /> <input
							type="password" id="inputPassword" class="form-control"
							placeholder="Password" required> <br />
						<button class="btn btn-lg btn-primary btn-block" id="Signin"
							type="submit">Sign in</button>
						<div id="link">
							<a id="crtLink" href="/Blog/Signup.jsp">Create account</a>
						</div>
					</form>
				</div>
			</div>

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