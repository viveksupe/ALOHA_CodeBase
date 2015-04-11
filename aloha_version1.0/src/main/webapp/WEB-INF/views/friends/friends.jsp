<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Friends Listing</title>
</head>
<body>
<h1>
	This is the friends page. This page will contain the listing of user's friends  
</h1>
	
<P>   </P>
<P>	 <c:forEach items="${friends}" var="element"> 
  <tr>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
    <th>friendship_id</th>
  </tr>
  <tr>
      <td>${element.friendshipId}</td>
  </tr>
</c:forEach>
</P>
</body>
</html>
