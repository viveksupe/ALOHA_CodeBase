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
	<link href="resources/Login/css/bootstrap.css" rel="stylesheet" type="text/css"></link>
	<link href="resources/Login/css/bootstrap-responsive.css" rel="stylesheet" type="text/css"></link> 
	
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
<form name="sign_up" method="post" action="Login.jsp">


<div style="margin-left:400px; margin-top:20px; border-color:green" class="span3 hero-unit ">
	 <h2>Login</h2>
	 <label>First Name</label><input type="text" name="fname" width="100px"  required/>
	 <label>Last Name</label><input type="text" name="lname" width="100px"  required/>
	 <label>Contact Number</label><input type="text" name="cnum" width="100px" />
	 <label>Email-Id</label><input type="email" name="email" width="100px"  required/>
	 <label>Password</label><input type="password" name="pwd" width="100px" required/>
	 <label>Confirm Password</label><input type="password" name="cpwd" width="100px" required/>
	 <input class="btn btn-primary" type="submit" value="Sign Up" name="sign_up" align="middle" onClick="Validate()">
	 <input class="btn btn-primary" type="submit" value="Cancel" name="Cancel" align="middle"><br/>
	 <a style="font-size:20px" href="login/">Already a member? Login</a>

</div>


</form>
</div>
<!-- End of FORM -->
 
  <script src ="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/Login/js/bootstrap.min.js"></script>
<script>
function Validate(){
      var fname = document.forms["sign_up"]["fname"].value;
	  var lname = document.forms["sign_up"]["lname"].value;
	  var cnum = document.forms["sign_up"]["cnum"].value;
	  var in_pswd = document.forms["sign_up"]["pwd"].value;
	  var c_pswd = document.forms["sign_up"]["cpwd"].value;	  
      var pass = /^(?:([A-Z])*([a-z])*(\d)*(\W)*){8,15}$/;
	  var phoneno = /^\d{10}$/; 
	  var letters = /^[A-Za-z]+$/;
	  var ret = false;
	  if(fname.match(letters))
	  {
	   ret = true;
	  }
	  else
	  {
	   return false;
	  }
	  if(lname.match(letters))
	  {
	   ret = true;
	  }
	  else
	  {
	   return false;
	  }
	  if(cnum.match(phoneno))
	  {
	   ret = true;
	  }
	  else
	  {
	   return false;
	  }
      if(in_pswd.match(pass))
      {
       ret = true;
      }
      else
      {
       alert('Incorrect email or password');
       return false;
      }
	  
	  if(c_pswd.match(in_pswd))
	  {
	    ret = true;
	  }
	  else
	  {
	   alert('Confirm Password does not match');
       return false;
	  }
	  return true;
}
</script>
  </body>
</html>