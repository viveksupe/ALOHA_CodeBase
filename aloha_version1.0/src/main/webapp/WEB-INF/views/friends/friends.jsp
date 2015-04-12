<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>

	<h1>This is the friends page. This page will contain the listing
		of user's friends</h1>

		<table border="1" align="center">
		<tr>
			<th>friendshipId</th>
			<th>user1</th>
			<th>user2</th>
			<th>status</th>
			<th>blocked_by</th>
			<th>req_sent_by</th>
		</tr>
		<c:forEach items="${friends}" var="element">
			<tr>
				<td>${element.friendshipId}</td>
				<td>${element.getUser1().firstName}</td>
				<td>${element.user2}</td>
				<td>${element.status}</td>
				<td>${element.blocked_by}</td>
				<td>${element.req_sent_by}</td>
			</tr>
		</c:forEach>
	</table>
       </jsp:body>
</t:GlobalTemplate>

