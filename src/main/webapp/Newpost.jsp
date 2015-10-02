<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html ">
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
		
		formData = {}
		formData ["postTitle"] = Title;
		formData ["blogText"] = Post;
		
		jsonObject.push(formData);
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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/FrontEnd.jsp">CS 476 BLOG</a>
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/Home.jsp">Home</a></li>
            <li><a href="/Blog/SignOut">Signout</a></li>
          <!--   <li><a href="#about">About</a></li> -->
          </ul>
        </div>
    	
</nav>

<div class="container">
 <br/><br/><br/><br/>


<h2 id="hd" class="form-signin-heading">Hello Shankar!</h2>
<br/><br/>

<div class="row">
      <label class="col-xs-12" for="TextArea" >Title</label>
 	</div>
    <div class="row">
      <div class="col-xs-4	">
        <textarea class="form-control" id="Title" maxlength="30"></textarea>
      </div>
    </div>
    
    <br/> <br/>
 	
 	<div class="row">
      <label class="col-xs-12" for="TextArea">Post</label>
 	</div>
    <div class="row">
      <div class="col-xs-12">
        <textarea class="form-control" id="Post" maxlength="140"></textarea>
      </div>
    </div>
    
    	
    <br/> <br/>
	<button type="button" id="submit" class="btn btn-default">Create</button>
</div>

<% if (session.getAttribute("UserName") == null) { %>
	    <input type="hidden" id="session" value="notSet"/>
	<% } else {%>
	     <input type="hidden" id="session" value="<%=session.getAttribute("UserName") %>"/>
	<% } %>

</body>
</html>