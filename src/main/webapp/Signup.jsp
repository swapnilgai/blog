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

<script>
$(document).ready(function() {
	
	var session = $("#session").val();
	if(session == "notSet"){
		$("#HomeLink").hide();	
	}
		
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
					 
			     $("#post").append('<div class="post_body"><img src="'+ImageUrl+'">');
				 $("#post").append('<h1 class="post-title"><a href="'+ url+ postId +'">'+postTitle+'</a></h1>');
				 $("#post").append('<p class="date"><span class="">'+date+'</span></p>');	
				 $("#post").append('<div class="posted">Posted by <span class="label"><a href="">'+author+'</a></span</div>');
			     $("#post").append('<div class="post_body"><p>'+postDescription+' </p></div> ');
			     $("#post").append('<div style="clear: both;"><a href="'+ url+ postId+'">Read more...</a></div>');
			     $("#post").append('<br/>');	
			     $("#post").append('<br/>');
			     $("#post").append('<br/>');
		
			}
			
		 },
		error: function(jqXHR, textStatus, errorMessage){
    		//alert(errorMessage);
    	}
	});
		  
	$( "#datepicker" ).datepicker({  maxDate: new Date() });
    
	$("#crtLink").click(function(){
		window.location.href = '/Blog/FrontEnd.jsp';
		function setFocusToTextBox(){
		    document.getElementById("crtLink").focus();
		}
	});
	
	
	    //below regex method for email refered from http://stackoverflow.com/questions/46155/validate-email-address-in-javascript
		//answered by sectrean
	    function EmailValid(email) {
			  var regexValidator = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			  return regexValidator.test(email);
		}
			
		
	
		//for validating email on blur event
		$('#inputEmail').blur(function(){
			var email = $('#inputEmail').val();
			 if(!EmailValid(email)){

				$("#labelError").text("Please Enter correct email");
				$("#Signup").attr("disabled", true);
		        
		    }else{
		    	$("#Signup").attr("disabled", false);
		    	$("#labelError").text("");
				
		    }
 		});
	
	
	$("button#Signup").click(function(){
		
		var email = $("#inputEmail").val();
	    var password = $("#inputPassword").val();
	    var firstName = $("#inputFname").val();
	    var lastName = $("#inputLname").val();
	    var dateBirth = $("#datepicker").val();
		//debugger;
		
	    jsonObject = [];
		
	    if(email=='' || password== '' || firstName== '' || lastName== '' || dateBirth== ''){
	    	alert('Please enter details');
	    	if(email==''){$("#inputEmail").focus();}
	    	if(password==''){$("#inputPassword").focus();}
	    	if(firstName==''){$("#inputFname").focus();}
	    	if(lastName==''){$("#inputLname").focus();}
	    	if(dateBirth==''){$("#datepicker").focus();}
	    	//if(Image == 0){$("#Image").focus();}
			return false;
	    }
	    
	    
		SignupData = {}
		SignupData ["email"] = email;
		SignupData ["password"] = password;
		SignupData ["firstName"] = firstName;
		SignupData ["lastName"] = lastName;
		SignupData ["dateBirth"] = dateBirth;
		//SignupData ["Image"] = Image;
		
		jsonObject.push(SignupData);
		$.ajax({
	    	 	type: "POST",
				url: "/Blog/SignUp",
				dataType: 'json',
				async: false,
				data: {jsonObject : JSON.stringify(jsonObject)},
				success: function(js){
					//alert(js);
					var blogs =  JSON.stringify(js["user"])
					var jsonParsedBlog = $.parseJSON(blogs);
					
					if(jsonParsedBlog == "fail"){
						alert("Email-id already present.. ");
					}
					else if( jsonParsedBlog == "InvalidEmail"){
						alert("Please enter valid email id");
					}else{
						window.location= "/Blog/Home.jsp";
					}
					 return false;
					// debugger;
                 },
        		error: function(jqXHR, textStatus, errorMessage){
        			// alert("error");
        			 
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
				<li class="active"><a id="HomeLink"
					href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
				<li><a href="/Blog/About.jsp">About</a></li>
			</ul>
		</div>
	</nav>
	<br />

	<div class="container" style="max-height: 90vh;">
		<div class="row">

			<div class="col-md-4 col-md-push-9 sidebar-bg">
				<div id="sidebar">
					<form id="f1" class="form-signin">
						<h2 id="hd" class="form-signin-heading">Create your Account</h2>

						<input type="email" id="inputEmail" class="form-control"
							placeholder="Email address" required autofocus> <label
							id="labelError" class="control-label" for="inputEmail"></label> <br />

						<input type="password" id="inputPassword" class="form-control"
							placeholder="Password" required> <br /> <input
							type="text" id="inputFname" class="form-control"
							placeholder="First Name" required> <br /> <input
							type="text" id="inputLname" class="form-control"
							placeholder="last Name" required> <br /> <input
							type="text" id="datepicker" class="form-control"
							placeholder="Birthdate" required> <br /> <br />

						<button class="btn btn-lg btn-primary btn-block" id="Signup"
							type="submit">Sign up</button>

						<div id="link">
							<a id="crtLink" href="/Blog/FrontEnd.jsp">Back to Signin</a>
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