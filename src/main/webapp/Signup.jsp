<%@ page language="java" 
    pageEncoding="ISO-8859-1"%>

<!--   HTML AND CSS Referenced from http://blog.miguelgrinberg.com/index  -->

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
		url: "/Blog/SignUp",
		dataType: 'json',
		async: false,
		success: function(js){
			//alert("success");
			var blogs =  JSON.stringify(js["commonBlog"])
			var jsonParsedBlog = $.parseJSON(blogs);
			
			//http://stackoverflow.com/questions/8951810/how-to-parse-json-data-with-jquery-javascript
			
			for (var i = 0; i < jsonParsedBlog.length; i++) { 
			     
				//alert(jsonParsedBlog[i]);
				 var postTitle = jsonParsedBlog[i].postTitle;
				 var date = jsonParsedBlog[i].date;
				 var author = jsonParsedBlog[i].userName;
				 var postDescription = jsonParsedBlog[i].blogText;
				 var postId =  jsonParsedBlog[i].postId;
				 var url = "/Blog/Posts/";
				
				 //http://stackoverflow.com/questions/1983648/replace-space-with-dash-and-make-all-letters-lower-case-using-javascript
				 postTitleEncoded = postTitle.replace(/\s+/g, '-');
				 
				 $("#post").append('<h1 class="post-title"><a href="'+ url+postId +'">'+postTitle+'</a></h1>');
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
	
	//$("#datepicker").datepicker({dateFormat: "mm-dd-yy"});
	  $( "#datepicker" ).datepicker({
      changeMonth: true,//this option for allowing user to select month
      changeYear: true //this option for allowing user to select from year range
    });
	
	$("button#Signup").click(function(){
		
		var email = $("#inputEmail").val();
	    var password = $("#inputPassword").val();
	    var firstName = $("#inputFname").val();
	    var lastName = $("#inputLname").val();
	    var dateBirth = $("#datepicker").val();
		
	    jsonObject = [];
		
	    if(email=='' || password== '' || firstName== '' || lastName== '' || dateBirth== ''){
	    	alert('Please enter details');
	    	if(email==''){$("#inputEmail").focus();}
	    	if(password==''){$("#inputPassword").focus();}
	    	if(firstName==''){$("#inputFname").focus();}
	    	if(lastName==''){$("#inputLname").focus();}
	    	if(dateBirth==''){$("#datepicker").focus();}
	    	
			return false;
	    }
	    
	    
		formData = {}
		formData ["email"] = email;
		formData ["password"] = password;
		formData ["firstName"] = firstName;
		formData ["lastName"] = lastName;
		formData ["dateBirth"] = dateBirth;

		jsonObject.push(formData);
		//alert(JSON.stringify(jsonObject));
		$.ajax({
	    	 	type: "POST",
				url: "/Blog/Login",
				dataType: 'json',
				async: false,
				data: {jsonObject : JSON.stringify(jsonObject)},
				success: function(js){
					//alert("success");
					 window.location= "/Blog/Home.jsp";
					 return false;
					// debugger;
                 },
        		error: function(jqXHR, textStatus, errorMessage){
        			 alert("error");
        			 
            	}
        });
    	return false;
	});	
	
	
});

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
            <li><a href="#about">About</a></li>
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
		        <h2 id="hd" class="form-signin-heading">Create your Account</h2>
		        
		        <label for="inputEmail" class="sr-only">Email address</label>
		        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
		        <br/>
		        
		        <label for="inputPassword" class="sr-only">Password</label>
		        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required >
		   
		       <div id="grp1" >
					<label for="inputFname" class="sr-only">First Name</label>
			        <input type="text" id="inputFname" class="form-control" placeholder="First Name" required>
			        
			        <br/>
			        
			        <label for="inputLname" class="sr-only">Last Name</label>
			        <input type="text" id="inputLname" class="form-control" placeholder="last Name"  required>
			        
		        <br/>
		       <input type="text" id="datepicker" class="form-control" placeholder="Birthdate"  required>
		       
		    <br/><br/>
		    </div>     
		   
		        <button class="btn btn-lg btn-primary btn-block" id="Signup" type="submit">Sign up</button>
		       
		      </form>
		     </div>
		</div>
		
		<div class="col-md-8 col-md-pull-4">
			<div id ="main">
			   <div id="post" class="post">
			   
			   <!--  <p class="date">
					<span class="" data-refresh="0" 
					data-format="format('LL')" data-timestamp="2015-08-09T18:58:46Z">August 9 2015</span>
				 </p>
				 
				 <h1 class="post-title">
					<a href="">Java Web!</a>
				</h1>

				<div class="posted">
					Posted by
					 <span class=""> 
						<a href="">Shankar Tiwari</a>
					 </span>
				</div>	
				 
				 <div class="post_body">
						<p>Some of you know that for the last few weeks  </p>
					</div> --> 
			   
			   </div>
		    </div>
		</div>
    
    </div>
   </div>
   
</body>
</html>