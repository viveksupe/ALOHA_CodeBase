<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>
      <div class="container-main pad-20">
        <div class="app">
<script src="http://feedstack.asia/app/script/members.js"></script>
<div class="member-body">
<div class="header-member">
		<i class="fa fa-space fa-group"></i> Members
		<span class="members-count">827</span>
	<span class="member-seach-span"><input type="text"
							class="member-search" placeholder="search..."
							root="http://feedstack.asia/"></span>
	</div>
<div class="root" root="http://feedstack.asia/" access-token=""></div>
<div class="entry">
						<div class="member-container">
<div class="bcol-member-block">
	<div class="member-image">
		<a href="http://feedstack.asia/renudeshmukh">
		<img src="http://feedstack.asia/img/user.jpg" class="member">		</a>
	</div>
	<div class="member-name">
		<a href="http://feedstack.asia/renudeshmukh">Renuka Deshmukh</a>
	</div>
</div>
<div class="clear"></div>
</div>
					</div>
</div>
			</div>
      </div>

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

