var FriendJS = new function() {

	this.root;	
	
	
	this.init = function(root){ 
		this.root=root;
		this.inviteFriendEvent();
		this.acceptFriendEvent();
	}
	
	this.inviteFriendEvent=function(){
		$('#inviteFriendBtn').click(function () {
			FriendJS.inviteFriend();
		});
	};
	
	this.acceptFriendEvent=function(){
		$('.acceptFriendBtn').click(function () {
			var userId = $(this).attr('userID');
			var acceptorId = $(this).attr('acceptorID');
			FriendJS.acceptFriend(userId, acceptorId);
		});
	};

	this.inviteFriend = function() {
		var emailAddr = $('#email').val();
		if (emailAddr != '') {
			$('#email').text('');
			console.log(emailAddr);
			$.ajax({
				headers : {
					'Accept' : 'application/json'
				},
				method : "POST",
				url : FriendJS.root + "/friends/invite",
				data : {
					email : emailAddr
				},
				success : function(data) {
					console.log(data);
					$('#email').val('');
					$('#inviteFriendStatus').html('Friend Invited');
				},
				error : function(data) {
					console.log('error occurred');
					console.log(data);
				}
			});
		}
	};

	this.addFriend = function(userId) {
		console.log(userId);
		$.ajax({
			headers : {
				'Accept' : 'application/json'
			},
			method : "POST",
			url : FriendJS.root + "/friends/add",
			data : {
				userIdToAdd : userId
			},
			success : function(data) {
				console.log('success');
				$('#addFriendBtn').html('Friend Request Sent')
			},
			error : function(data) {
				console.log('error occurred');
				console.log(data);
			}
		});
	};

	this.acceptFriend = function(userId, acceptorId) {
		console.log("userId" + userId);
		console.log("acceptorId" + acceptorId);
		$.ajax({
			headers : {
				'Accept' : 'application/json'
			},
			method : "POST",
			url : FriendJS.root + "/friends/accept",
			data : {
				userIdToAccept : userId,
				acceptor : acceptorId
			},
			success : function(data) {
				console.log('success');
				$('#acceptFriendBtn_' + userId).html('Friend Request Accepted');
			},
			error : function(data) {
				console.log('error occurred');
				console.log(data);
			}
		});
	};

	this.unFriend = function(friendshipId) {
		console.log(friendshipId);
		$.ajax({
			headers : {
				'Accept' : 'application/json'
			},
			method : "POST",
			url : FriendJS.root + "/friends/remove",
			data : {
				friendshipIdToRemove : friendshipId
			},
			success : function(data) {
				console.log('success');
				$('#unFriendBtn').html('Friend Removed')
			},
			error : function(data) {
				console.log('error occurred');
				console.log(data);
			}
		});
	};

};