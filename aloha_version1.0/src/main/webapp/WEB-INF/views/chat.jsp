<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<title>Facebook Style Popup Design</title>


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
	<div id="messages"></div>
	<div class="chat_box" onclick=toggleChatBox();>
		<div class="chat_head">Chit Chat</div>
		<div class="chat_body">


			<c:forEach items="${onlineUsers}" var="user">
				<div class="user" onlick=clickUserBox();>
					<a
						href="javascript:register_popup(${user.userId},${sessionUser.userId }, '${user.firstName} ${user.lastName}');">${user.firstName} ${user.lastName}</a>
				</div>
			</c:forEach>



		</div>
	</div>
</body>
</html>