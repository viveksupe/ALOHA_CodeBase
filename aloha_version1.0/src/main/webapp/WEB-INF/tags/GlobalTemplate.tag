
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="header" fragment="true"%>
<html lang="en">
<head>
<title>Aloha</title>
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bottle.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/feed-menu.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/menu.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/index.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/userlog.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/members.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/wall.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/css/profile.css"
	rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/feed-menu.js"></script>
<link href="resources/chat/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>var userID=${sessionUser.userId};</script>
<script src="resources/chat/script.js"></script>
<script>
$( document ).ready(function(){
	appRoot = "${pageContext.request.contextPath}";
	console.log(appRoot);
	
});
</script>
</head>
<body>
	<div class="header">
		<div class="container-main pad-header">
			<div class="bcol-25 logo">
				<a href="${pageContext.request.contextPath}"> <img height="50px"
					src="${pageContext.request.contextPath}/resources/img/logo.png">
				</a>
			</div>
			<div class="bcol-75 menu">
				<span class="menu-text">+ menu</span>

				<ul class="menu-bar">

					<a href="${pageContext.request.contextPath}/search/users"><li><i
							class="fa fa-space fa-search"></i>Search</li></a>
					<a href="${pageContext.request.contextPath}/post"><li><i
							class="fa fa-space fa-comments"></i>Posts</li></a>
					<a href="${pageContext.request.contextPath}/user_profile">
						<li><i class="fa fa-space fa-user"> </i>Profile</li>
					</a>
					<a href="${pageContext.request.contextPath}/friends"><li><i
							class="fa fa-space fa-group"></i>Friends</li></a>
					<a href="${pageContext.request.contextPath}/${globalstatuslink}"><li><i
							class="fa fa-space fa-sign-out"></i>${globalstatus }</li></a>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="body">
		<jsp:doBody />
	</div>
	<div class="chat_box" onclick=toggleChatBox();>
		<div class="chat_head">Chit Chat</div>
		<div class="chat_body">


			<c:forEach items="${onlineUsers}" var="user">
				<div class="user" onlick=clickUserBox();>
					<a
						href="javascript:register_popup(${user.userId},${sessionUser.userId }, '${user.firstName} ${user.lastName}');">${user.firstName}
						${user.lastName}</a>
				</div>
			</c:forEach>



		</div>
	</div>
	<div class="footer">
		<div class="container-main pad-20">
			<div class="ftrr">
				<div class="bcol-70">
					<ul class="ftr_menu">
						<a href="http://feedstack.asia/"><li>Home</li></a>
						<a href="http://feedstack.asia/about"><li>About</li></a>
						<a href="post"><li>Scribble</li></a>
						<a href="http://feedstack.asia/terms"><li>Terms of use</li></a>
						<a href="http://feedstack.asia/contact"><li>contact</li></a>
					</ul>
				</div>
				<div class="bcol-30"></div>
				<div class="clear"></div>
			</div>
			<div class="ftr">
				Aloha - A social networking portal | a <a
					href="http://localhost:8080/common/">Aloha Inc.</a> production
			</div>
		</div>
	</div>
</body>
</html>