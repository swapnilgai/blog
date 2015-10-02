<%@ page session="true" language="java" 
    pageEncoding="ISO-8859-1"%>

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
	
	$("button#Signin").click(function(){
		//alert($(this).text());
		 
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
		//alert(JSON.stringify(jsonObject));
	  
	     $.ajax({
	    	 	type: "POST",
				url: "/Blog/Login",
				dataType: 'json',
				async: false,
				 /*  headers: { 
		        		'Accept': 'application/json; charset=utf-8',
		        		'Content-Type': 'application/json; charset=utf-8', 
		    	  }, */
		    	 
		    	data: {jsonObject : JSON.stringify(jsonObject)},
		    	
				beforeSend: function () {
                //$("#at").html("Updating, please wait....");
                //alert(jsonObject[0].email);
                },
				success: function(js){
					//alert("s");
					var json =  JSON.stringify(js["user"])
					//alert(json);
					if(json = "success"){
					 window.location= "/Blog/Home.jsp";
				}
					 return false;
					//debugger;
					
                 },
        		error: function(jqXHR, textStatus, errorMessage){
            		alert(errorMessage);
            		//alert(jqXHR);
            		//alert(textStatus);
										
            	}
              });
	     return false;
	});	
	
	
	
});

//$('#crtLink').click(function(){ Reset(); return false; });
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
           <!--  <li><a href="#about">About</a></li>-->
          </ul>
        </div>
    	
</nav>
<br/>

	<!-- http://stackoverflow.com/questions/9707397/making-a-div-vertically-scrollable-using-css -->
	<div class="container" style="max-height: 90vh;">
	 <div class="row">
	
		<div class="col-md-4 col-md-offset-4 ">
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
		  
		 
	</div>
		
		
    </div>
   
</body>
</html>