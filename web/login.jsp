<%-- 
    Document   : newlogin
    Created on : 29 Sep, 2018, 7:53:08 PM
    Author     : Harsh Gupta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Student Login</title>
  
  
  
      <link rel="stylesheet" href="css/loginstyle.css">

  
</head>

<body>
 
<div class="container">
	<section id="content">
		  <form action ="login" method ="post">
			<h1>Student Login </h1>
			<div>
				<input type="text" placeholder="Email Address" required="" id="username" name = "email"/ >
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" name = "password"/>
			</div>
			<div>
				<input type="submit" value="Log in"  name = "submit"/>
				 
				<a href = "register.jsp">Register</a>			</div>
		</form>
		<!-- form -->
		<div class="button">
			
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->
</body>
  
    <script  src="js/index.js"></script>


</html>
