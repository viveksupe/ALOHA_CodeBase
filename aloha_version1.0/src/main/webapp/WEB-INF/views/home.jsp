<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
 <img alt="ok" src="${pageContext.request.contextPath}/resources/img/glyphicons-halflings-white.png">
 <p> <a href="friends/">Friends List</a>
 <a href="login/">Login</a>
 		</p>
	</jsp:body>
</t:GlobalTemplate>

