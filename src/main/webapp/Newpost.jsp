<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!-- Bootstrap framework(v3.3.5) referenced from (http://getbootstrap.com/), 
JQuery(v1.11.3) referenced from (http://jqueryui.com) used in below script -->

<!DOCTYPE html ">
<html>
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

<script>
$(document).ready(function() {
	
	$("button#submit").click(function(){
	
		var Title = $("#Title").val();
	    var Post = $("#Post").val();
	    
	    if(Title =='' || Post == '' ){
	    	alert('Please enter details');
	    	if(Title==''){$("#Title").focus();}
	    	if(Post==''){$("#Post").focus();}
	    	return false;
	    }
	    
		jsonObject = [];
		
		NewPostData = {}
		NewPostData ["postTitle"] = Title;
		NewPostData ["blogText"] = Post;
		
		jsonObject.push(NewPostData);
		//alert(JSON.stringify(jsonObject));
	  
	     $.ajax({
	    	 	type: "POST",
				url: "/Blog/BlogInsert",
				dataType: 'json',
				async: false,
		    	data: {jsonObject : JSON.stringify(jsonObject)},
		    	
				beforeSend: function () {
                },
				success: function(js){
					var json =  JSON.stringify(js["user"])
					if(json = "success"){
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
				<li><a href="/Blog/SignOut">Signout</a></li>
				<!--   <li><a href="#about">About</a></li> -->
			</ul>
		</div>

	</nav>

	<div class="container">
		<br />
		<br />
		<br />
		<br />


		<h2 id="hd" class="form-signin-heading">
			Welcome
			<%=session.getAttribute("UserName") %></h2>
		<br />
		<br />

		<div class="row">
			<label class="col-xs-12" for="TextArea">Title</label>
		</div>
		<div class="row">
			<div class="col-xs-4	">
				<textarea class="form-control" id="Title" maxlength="30"></textarea>
			</div>
		</div>

		<br /> <br />

		<div class="row">
			<label class="col-xs-12" for="TextArea">Post</label>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<textarea class="form-control" id="Post" maxlength="140"
					style="width: 1185px; height: 155px;"></textarea>
			</div>
		</div>


		<br /> <br />
		<button type="button" id="submit" class="btn btn-default">Create</button>
	</div>

	<% if (session.getAttribute("UserName") == null) { %>
	<input type="hidden" id="session" value="notSet" />
	<% } else {%>
	<input type="hidden" id="session"
		value="<%=session.getAttribute("UserName") %>" />
	<% } %>

</body>
</html>