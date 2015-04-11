<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Friends Listing</title>
</head>
<body>
	<h1>This is the friends page. This page will contain the listing
		of user's friends</h1>

	<P></P>
	<P>
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
				<td>${element.getUser().firstName}</td>				
			</tr>
		</c:forEach>
		</table>
	</P>
</body>
</html>
