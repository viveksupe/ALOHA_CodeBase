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
		<i class="fa fa-space fa-group"></i> Friends
		<span class="members-count">${pageContext.request.contextPath})</span>
	<span class="member-seach-span"><input type="text"
							class="member-search" placeholder="search..."
							root="http://feedstack.asia/"></span>
	</div>
<div class="root" root="http://feedstack.asia/" access-token=""></div>
<div class="entry">
						<div class="member-container">
								<c:forEach items="${users}" var="element">
						
<div class="bcol-member-block">
	<div class="member-image">
		<a href="http://feedstack.asia/renudeshmukh">
		<img src="http://feedstack.asia/img/user.jpg" class="member">		</a>
	</div>
	<div class="member-name">
		<a href="http://feedstack.asia/renudeshmukh">${element.firstName}</a>
	</div>
</div>
</c:forEach>
<div class="clear"></div>
</div>
					</div>
</div>
			</div>
      </div>

       </jsp:body>
</t:GlobalTemplate>

