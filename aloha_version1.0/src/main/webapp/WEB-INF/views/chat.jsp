<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<title>Facebook Style Popup Design</title>


<link href="resources/chat/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="resources/chat/script.js"></script>
</head>
<body>
		<div id="messages"></div>
	<div class="chat_box" onclick=toggleChatBox();>
		<div class="chat_head">Chit Chat</div>
		<div class="chat_body">
			<div class="user" onlick=clickUserBox();>
				<a href="javascript:register_popup(4,8, 'Vivek Supe');">
				
				Vivek Supe
			</a>
			</div>
			<div class="user">
				<a href="javascript:register_popup(5,8, 'Renuka Deshmukh');">
				
				Renuka Deshmukh
			</a>
			</div>

		</div>
	</div>
</body>
</html>