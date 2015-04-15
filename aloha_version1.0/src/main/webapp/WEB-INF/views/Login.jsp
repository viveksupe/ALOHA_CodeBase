<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

  <head>
    <meta charset="utf-8">
    <meta name="generator" content="CoffeeCup HTML Editor (www.coffeecup.com)">
    <meta name="dcterms.created" content="Sat, 11 Apr 2015 22:14:27 GMT">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>ALOHA</title>
	<link href="${pageContext.request.contextPath}/resources/Login/css/bootstrap.css" rel="stylesheet" type="text/css"></link>
	<link href="${pageContext.request.contextPath}/resources/Login/css/bootstrap-responsive.css" rel="stylesheet" type="text/css"></link> 
	
    <!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  <style type="text/css">

 
.container {
		   margin-top:50px;		   
 }
</style>
</head>
  <body>
  <div class="container">
  <h1 align="center"><font color="#9DAEFF">ALoHa</font></h1>
    <hr />

<!-- Start of FORM -->
<form name="login" method="post" action="${pageContext.request.contextPath}/login">


<div style="margin-left:400px; margin-top:20px; border-color:green" class="span3 hero-unit ">
	 <h2>Login</h2><br/>
	 <h6>${headerMessage}</h6>
	 <label>Email-Id</label><input type="email" name="email" width="100px"  required/>
	 <label>Password</label><input type="password" name="pwd" width="100px" required/>
	 <input class="btn btn-primary" type="submit" value="Login" name="Login" align="middle" onClick="checkPassword(document.login.Login)">
	 <input class="btn btn-primary" type="submit" value="Cancel" name="Cancel" align="middle"><br/>
	 <a style="font-size:11px" href="forgotPassword.jsp">Forgot Password?</a> <br/>
	 <a style="font-size:20px" href="${pageContext.request.contextPath}/login/sign_up/">Not a member yet? Join Us</a>
</div>


</form>
</div>
<!-- End of FORM -->
 
  <script src ="http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.request.contextPath}/resources/Login/js/bootstrap.min.js"></script>
<script>
function checkPassword(input){
      var pass = /^(?:([A-Z])*([a-z])*(\d)*(\W)*){8,15}$/;
      if(input.value.match(pass))
      {
       return true;
      }
      else
      {
       alert('Incorrect email or password');
       return false;
      }
}
</script>
  </body>
</html>