<%@ page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
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
    			$("#signout").hide();
    		}
    		
    	});

    </script>
</head>

<body background="pic.jpg">
	<nav class="navbar navbar-inverse" role="navigation">
		<div id="navbar" class="collapse navbar-collapse">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/FrontEnd.jsp">back to
				Blogger v. 1.0</a>
			<ul class="nav navbar-nav">
				<li><a id="signout" href="/Blog/SignOut">Signout</a></li>

			</ul>
		</div>

	</nav>
	<div style="color: white; padding: 20px; font-size: 25px;">
		<h1>
			Blogger V. 1.0
			<p>Making the web beautiful</p>
		</h1>
		Devloped and designed by:

		<div style="padding: 30px">
			<p>Swapnil Gaidhankar</p>
			<p>Shankar Tiwari</p>
			<p>Rohan Chinchwade</p>
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
