var PostManager = new function() {

	this.Posts = [];
	this.Root = '';

	this.init = function(root) {
		// alert('in init');
		this.Root = root;
		this.commentEnterEvent();
		this.getPosts();
		// this.savePost();
	};

	this.commentEnterEvent = function() {
		$('.feed-comment-count').click(
				function() {
					var feed_id = $(this).closest('.feed').attr('feed-id');
					var container = $('.comment-block-entry-' + feed_id);
					var comment_count = parseInt($('.comment-count-' + feed_id)
							.html());
					var ele = $(this).next('.loading-likers');

					if (container.is(':visible')) {
						container.slideUp();
					} else {
						ele.show();
						container.slideDown();
						ele.hide();
					}
				});
	};

	this.isInvalidEntry = function(value) {

		var pattern = new RegExp(
				/<[a-zA-Z]*script[\s\S]*?>[\s\S]*?<\/[a-zA-Z]*script>/g);
		return pattern.test(value);
	};

	this.savePost = function() {
		svar
		value = $('#txtPost').val();
		// var value = 'renuka';
		if (!this.isInvalidEntry(value)) {

			var json = {
				postData : value
			};

			$.ajax({
				method : "POST",
				url : "http://localhost:8080/common/post/add",
				data : {
					postData : "milind"
				},
				success : function(data) {
					console.log(data);
				}
			});

		} else {
			alert("Scripts not allowed !!");
		}
	};

	this.getPosts = function() {

		$.ajax({
			headers : {
				'Accept' : 'application/json'
			},
			method : "POST",
			url : this.Root + "/post/getPost",
			data : {
				searchKey : "milind"
			},
			success : function(data) {
				console.log('success');
				$('#postContainer').setTemplateURL(
						this.Root + '/WEB-INF/views/postTemplate.jsp');
				$('#postContainer').processTemplate(data.posts);
			},
			error : function(data) {
				// alert(data);
				console.log(data);
			}
		})
	};
}