<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>
    <div class="container-main pad-20">
      <div class="app bcol-70">

        <!-- ToolTip -->
        <link
					href="http://feedstack.asia/app/script/tooltip/css/tooltipster.css"
					rel="stylesheet"></link>
          <script
					src="http://feedstack.asia/app/script/tooltip/js/jquery.tooltipster.min.js"></script>

          <script>
            $(function() {
            $('.tooltip').tooltipster();
            });

            function addFriend(userId) {
            console.log(userId);
            $
            .ajax({
            headers : {
            'Accept' : 'application/json'
            },
            method : "POST",
            url : "${pageContext.request.contextPath}/friends/add",
            data : {
            userIdToAdd : userId
            },
            success : function(data) {
            console.log('success');
            $('#addFriendBtn')
            .html('Friend Request Sent')
            },
            error : function(data){
            console.log('error occurred');
            console.log(data);
            }
            });
            };
            
            function unFriend(friendshipId) {
                console.log(friendshipId);
                $
                .ajax({
                headers : {
                'Accept' : 'application/json'
                },
                method : "POST",
                url : "${pageContext.request.contextPath}/friends/remove",
                data : {
                friendshipIdToRemove : friendshipId
                },
                success : function(data) {
                console.log('success');
                $('#unFriendBtn')
                .html('Friend Removed')
                },
                error : function(data){
                console.log('error occurred');
                console.log(data);
                }
                });
                };

          </script>

          <div class="wall">

            <div class="profile">
              <div class="profile-mobile-bg">

                <img src="http://feedstack.asia/img/user.jpg"
								class="profile-image-mobile" />
                </div>
              <div class="bcol-30">
                <div
								class="feed-user mobile-hidden mobile-hidden-main-image">
                  <img src="http://feedstack.asia/img/user.jpg"
									class="profile-image" />
			</div>
              </div>
              <div class="bcol-70">
                <div class="profile-container">
                  <div class="profile-name">
                    <div class="profile-name-span">${user.firstName} ${user.lastName}</div>
                  </div>
                  <div class="profile-desc">
                    Hey people im a new entry to this website..hope to have fun..follow me to get updates.
                  </div>
                  <div class="profile-link">
                    <!-- 					<a href="http://feedstack.asia/milindhg"><i class="fa fa-space profile-fa-link fa-th"></i></a> &nbsp;
					<a href="http://feedstack.asia/milindhg/text"><i class="fa fa-space profile-fa-link fa-bars"></i></a> &nbsp;
					<a href="http://feedstack.asia/milindhg/photo"><i class="fa fa-space profile-fa-link fa-picture-o"></i></a> &nbsp;
					<a href="http://feedstack.asia/milindhg/video"><i class="fa fa-space profile-fa-link fa-video-camera"></i></a> &nbsp;
					<a href="http://feedstack.asia/milindhg/link"><i class="fa fa-space profile-fa-link fa-code"></i></a> -->
                  </div>
                  <div class="mobile-links">
                    <!-- 										<a href="http://feedstack.asia/milindhg/notifications"><span class="mb-link-span">Notifications</span></a>
										<a href="http://feedstack.asia/milindhg/following"><span class="mb-link-span">Following</span></a>
					<a href="http://feedstack.asia/milindhg/followers"><span class="mb-link-span">Followers</span></a> -->
                  </div>
                  <div class="profile-buttons" uid="836"
									liveuser-id="836">
                      <c:choose>
                        <c:when test="${empty friendship}">
                    	<a href="#">
                          <button id="addFriendBtn" class="btn btn-edit"
													onclick="addFriend(${user.userId})">
                            Add Friend
                          </button>
                          </a>
                        </c:when>
                        <c:otherwise>
                          
                            ${friendship.status}
                          <button id="unFriendBtn" class="btn btn-edit" onclick="unFriend(${friendship.friendshipId})"> Unfriend
                          </button>
                        </c:otherwise>
                      </c:choose>
                    
                  </div>
                </div>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="profile-container-box">
            <script src="http://feedstack.asia/app/script/readmore.js"></script>
            <script src="http://feedstack.asia/app/script/popup.js"></script>
            <script src="http://feedstack.asia/app/script/wall.js"></script>
            <style>
.footer {
	background: white;
}

.profile-container-box {
	min-height: 210px;
}
</style>

            <!-- <div class="login-popup popup"><b>Please Login to continue</b> <br>
	<a href="http://feedstack.asia/user/login"><button class="btn btn-login">login</button></a>
	 <button class="btn btn-cancel" type="button">cancel</button>
</div>
<div class="root" root="http://feedstack.asia/" access-token="1429169272g836"></div>
	<div class="profile-card" count="0">Opps ! No  feeds yet </div> -->

            <br />

</div>
        </div>
      <div class="clear"></div>
    </div>
  </jsp:body>
</t:GlobalTemplate>
