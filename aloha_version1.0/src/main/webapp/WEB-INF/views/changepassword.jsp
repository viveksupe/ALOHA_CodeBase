<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>

        <h1>Reset Password</h1>
        <form:form method="post" onSubmit="return Validate()" action="${pageContext.request.contextPath}/changepassword?id=${id}"> 
        	<label>Verification Password</label>
			 <input type="password" name="vpwd" width="100px" size="20" required/>		      
            <label>Password</label>
			 <input type="password" name="pwd" width="100px" 
			 pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}" 
			 title="Please enter at least one uppercase, one lowercase and one numeric. Length should be at least 8 and maximum 15" 
			 required/>
			 <label>Confirm Password</label>
			 <input type="password" name="cpwd" width="100px" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}" required/>			 
            <input type="submit"/>       		
        </form:form>
        
<script>
function Validate(){
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
	  
	  return ret;
}
</script>        
	</jsp:body>
</t:GlobalTemplate>
