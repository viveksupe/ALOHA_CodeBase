var webSocket = new WebSocket('ws://' + location.hostname + ':' + location.port
		+ '/common/websocket');

webSocket.onerror = function(event) {
	onError(event)
};

webSocket.onopen = function(event) {
	onOpen(event)
};

webSocket.onmessage = function(event) {
	onMessage(event)
};

function onMessage(event) {
	var obj = new Object();
	obj = JSON.parse(event.data);
	document.getElementById('messages').innerHTML += '<br />Received message: '
			+ obj.chatMsg;
	$('<div class="msg_a">' + obj.chatMsg + '</div>').insertBefore('.msg_push_'+obj.userID);
}

function onOpen(event) {
	document.getElementById('messages').innerHTML = 'Connection established';

}

function onError(event) {
	alert(event.data);
}

/*
 * function send() { var txt = document.getElementById('inputmessage').value;
 * webSocket.send(txt); document.getElementById('messages').innerHTML += '<br />Sent
 * message: ' + txt; return false; }
 */
function toggleBox(toid) {
	$(".msg_wrap_"+toid).slideToggle('slow');
}
function toggleChatBox(event) {
	$('.chat_body').slideToggle('slow');
}
function closeBox(toid) {
	$("#"+toid).hide();
}
function clickUserBox(event) {
	//$('.msg_wrap').show();
	$('.msg_box').show();
}

function SendMsg(message, toid, fromid) {
	// alert(message);
	var obj = new Object();
	obj.userID = fromid;
	obj.toUserID = toid;
	obj.chatMsg = message;
	var jsonString = JSON.stringify(obj);
	// json = JSON.stringify(msg);
	// alert(jsonString);
	$(".msg_input"+toid).val('');
	if (obj.chatMsg != '' && obj.chatMsg != "\n") {
		$('<div class="msg_b">' + obj.chatMsg + '</div>').insertBefore('.msg_push_'+toid);
		webSocket.send(jsonString);
		document.getElementById('messages').innerHTML += '<br />Sent message: '
				+ obj.chatMsg;
	}
	$('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);

}

function InitSendMsg(toid, fromid) {
	var obj = new Object();
	obj.userID = fromid;
	obj.toUserID = toid;
	obj.chatMsg = "Initialtion Message";
	var jsonString = JSON.stringify(obj);
	webSocket.send(jsonString);
	alert("Sent Init");
};

// this function can remove a array element.
Array.remove = function(array, from, to) {
	var rest = array.slice((to || from) + 1 || array.length);
	array.length = from < 0 ? array.length + from : from;
	return array.push.apply(array, rest);
};

// this variable represents the total number of popups can be displayed
// according to the viewport width
var total_popups = 0;

// arrays of popups ids
var popups = [];

// this is used to close a popup
function close_popup(id) {
	for (var iii = 0; iii < popups.length; iii++) {
		if (id == popups[iii]) {
			Array.remove(popups, iii);

			document.getElementById(id).style.display = "none";

			calculate_popups();

			return;
		}
	}
}

// displays the popups. Displays based on the maximum number of popups that can
// be displayed on the current viewport width
function display_popups() {
	var right = 320;

	var iii = 0;
	for (iii; iii < total_popups; iii++) {
		if (popups[iii] != undefined) {
			var element = document.getElementById(popups[iii]);
			element.style.right = right + "px";
			right = right + 320;
			element.style.display = "block";
		}
	}

	for (var jjj = iii; jjj < popups.length; jjj++) {
		var element = document.getElementById(popups[jjj]);
		element.style.display = "none";
	}
}

// creates markup for a new popup. Adds the id to popups array.
function register_popup(toid, fromid, name) {

	for (var iii = 0; iii < popups.length; iii++) {
		// already registered. Bring it to front.
		if (toid == popups[iii]) {
			Array.remove(popups, iii);

			popups.unshift(toid);

			calculate_popups();

			return;
		}
	}

	$.ajax({
		method : "POST",
		url : appRoot + "/chathistory",
		data : {
			touser : toid,
			fromuser : fromid
		},
	success : function(data) {
		for (var ke in data) {
		       if (data.hasOwnProperty(ke)) {
		            // console.log(data[ke].chatContent);
		    	   if(data[ke].userID1==toid&&data[ke].userID2==fromid){
		    		   $('<div class="msg_b">' + data[ke].chatContent + '</div>').insertBefore('.msg_push_'+data[ke].userID1); 
		    	   }
		    	   if(data[ke].userID1==fromid&&data[ke].userID2==toid){
		    		   $('<div class="msg_a">' + data[ke].chatContent + '</div>').insertBefore('.msg_push_'+data[ke].userID2); 
		    	   }
		       }
		} 
	}
	
});
	
	/*).done(function(msg) {
		
		var obj = new Object();
		obj = JSON.parse(msg);
		alert("Data Saved: " + obj.chatContent);
		
		$('<div class="msg_a">' + obj.chatContent + '</div>').insertBefore('.msg_push_'+obj.userID1);
	});*/

	var element = '<div class="msg_box" id="' + toid + '">';
	element = element
			+ '<div class="msg_head" onclick=toggleBox('+toid+');>'
			+ name
			+ '<div class="close" onclick=closeBox('+toid+');>X</div></div><div class="msg_wrap_' + toid + '" >';
	name = name.replace(/\s+/g, '');
	element = element
			+ '<div class="msg_body" > <div class="msg_a">This is from A</div> <div class="msg_b">This is from B</div><div class="msg_push_' + toid + '"></div></div>'
	element = element
			+ '<div class="msg_footer"><table><tr><td width=80%><textarea id="'
			+ name
			+ '" class="msg_input'+toid+'" rows="2"></textarea></td><td width=20%><button class="sendBtn" onclick=SendMsg(document.getElementById("'
			+ name + '").value,' + toid + ',' + fromid
			+ ')>Send</button></td></tr></table></div>';
	// element = element + '<div class="popup-head-right"><a
	// href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
	element = element + '</div>';

	document.getElementsByTagName("body")[0].innerHTML = document
			.getElementsByTagName("body")[0].innerHTML
			+ element;

	popups.unshift(toid);

	calculate_popups();

}

// calculate the total number of popups suitable and then populate the
// toatal_popups variable.
function calculate_popups() {
	var width = window.innerWidth;
	if (width < 540) {
		total_popups = 0;
	} else {
		width = width - 200;
		// 320 is width of a single popup box
		total_popups = parseInt(width / 320);
	}

	display_popups();

}

// recalculate when window is loaded and also when window is resized.
window.addEventListener("resize", calculate_popups);
window.addEventListener("load", calculate_popups);