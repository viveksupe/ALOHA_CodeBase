
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
    <jsp:body>

  	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"></link>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.css" rel="stylesheet" type="text/css"></link> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
  	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
    <!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  
  <div class="container">
  
<!-- Start of FORM -->
<form name="sign_up" method="post" onSubmit="return Validate(this)" action="${pageContext.request.contextPath}/login/sign_up">


<div style="margin-left:400px; margin-top:20px; border-color:green" class="span3 hero-unit ">
	 <h2>Sign UP</h2>
	 <label>First Name</label>
	 <input type="text" name="fname" width="100px" size = "20" pattern="[A-Za-z]{1,20}" title = "please enter only characters and length below 20" required/>
	 <label>Last Name</label>
	 <input type="text" name="lname" width="100px" size = "20" pattern="[A-Za-z]{1,20}" title = "please enter only characters and length below 20" required/>
	 <label>Contact Number</label>
	 <input type="text" name="cnum" width="100px" pattern="\d{10}" title="please enter only 10 digits"/>
	 <label>Email-Id</label>
	 <input type="email" name="email" width="100px"  required/>
	 <label>Date of Birth</label><input type="date" name = "dob" min="1915-12-31" max="1997-12-31" title="Minimum age should be 18" required/>
	 <label>Password</label>
	 <input type="password" name="pwd" width="100px" 
	 pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,15}" 
	 title="Please enter at least one uppercase, one lowercase and one numeric. Length should be at least 8 and maximum 15 no special characters allowed" 
	 required/>
	 <label>Confirm Password</label>
	 <input type="password" name="cpwd" width="100px" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,15}" required/>
	 <div id="cpwd_e" class = "error" style = "display:none"><label>Confirm password wrong</label></div>
	 
	<div>
	 By submitting, I agree that all info entered was done accurately & truthfully.<br>
		I accept: <input type="checkbox" value="0" name="agree" required/>
	 </div>
	 
	 			 
	 <input class="btn btn-primary" type="submit" value="Sign Up" name="sign_up" align="middle" >
	 <input class="btn btn-primary" type="reset" value="Cancel" name="Cancel" align="middle"><br/>
	 
	 <a style="font-size:20px" href="${pageContext.request.contextPath}/login/">Already a member? Login</a>
	
	
</div>


</form>
</div>
<!-- End of FORM -->
 
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script>
function Validate(f){
      var in_pswd = document.forms["sign_up"]["pwd"].value;
	  var c_pswd = document.forms["sign_up"]["cpwd"].value;	  
      if(c_pswd.match(in_pswd))
	  {
	    ret = true;
	  }
	  else
	  {
		  $("#cpwd_e").css('display','block');
       	  ret = false;
       	  document.forms["sign_up"].reset();
	  }
      if (f.agree.checked == false )
      {
      	   alert('Please check the box to continue.');
			ret = false;
      }
      else
      {
    	  ret = true;
      }
	  return ret;
}
function ReloadCaptchaImage(captchaImageId) {
	var obj = document.getElementById(captchaImageId);
	var src = obj.src;
	var date = new Date();
	var pos = src.indexOf('&rad=');
	if (pos >= 0) 
	{ 
		src = src.substr(0, pos); 
	}
	obj.src = src + '&rad=' + date.getTime();
	return false; 
	}
</script>
    </jsp:body>
</t:GlobalTemplate>
 