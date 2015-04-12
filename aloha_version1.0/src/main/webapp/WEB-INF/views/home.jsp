<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>
           <h1>Views Home Index</h1>
           <h1>
<P>  The time on the server is ${serverTime}. </P>
 <P>	 <c:forEach items="${users}" var="element"> 
   <tr>
    <td>${element}</td>
   </tr>
 </c:forEach>
 </P>
 
 <p> <a href="friends/">Friends List</a>
 <a href="Login.jsp">Login</a>
 		</p>
        
	</jsp:body>
</t:GlobalTemplate>

