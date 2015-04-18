<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>
<link href="http://feedstack.asia/lib/expresscss/style.css"
					rel="stylesheet"></link>
<script src="http://feedstack.asia/app/script/profile.js"></script>
<script src="http://feedstack.asia/app/script/readmore.js"></script>
<script src="http://feedstack.asia/app/script/popup.js"></script>
<script src="http://feedstack.asia/app/script/wall.js"></script>
<!-- ToolTip -->
<link href="http://feedstack.asia/app/script/tooltip/css/tooltipster.css" rel="stylesheet">
<script src="http://feedstack.asia/app/script/tooltip/js/jquery.tooltipster.min.js"></script>
        <script src="http://feedstack.asia/app/script/profile.js"></script>
        <!-- ToolTip -->
        <link
					href="http://feedstack.asia/app/script/tooltip/css/tooltipster.css"
					rel="stylesheet" />
        <script
					src="http://feedstack.asia/app/script/tooltip/js/jquery.tooltipster.min.js"></script>
        <script>
          $(function() {
          $('.tooltip').tooltipster();
          });


          function acceptFriend(userId,acceptorId) {
          console.log("userId" +  userId);
          console.log("acceptorId" + acceptorId);
          $
          .ajax({
          headers : {
          'Accept' : 'application/json'
          },
          method : "POST",
          url : "${pageContext.request.contextPath}/friends/accept",
          data : {
          userIdToAccept : userId,
          acceptor  : acceptorId
          },
          success : function(data) {
          console.log('success');
          $('#acceptFriendBtn')
          .html('Friend Request Accepted')
          },
          error : function(data){
          console.log('error occurred');
          console.log(data);
          }
          });
          };

        </script>


    <div class="container-main pad-20">

      <div class="app bcol-70">
        <div class="wall">

          <div class="profile">
            <div class="profile-mobile-bg">


              <img src=${imgLocation}
                            class="profile-image-mobile" />
            </div>
            <div class="bcol-30">
              <div class="feed-user mobile-hidden mobile-hidden-main-image">
                <img src=${imgLocation} class="profile-image" />

              </div>
            </div>
            <div class="bcol-70">
              <div class="profile-container">
                <div class="profile-name">
                  <div class="profile-name-span">${user.getFirstName()} ${user.getLastName()} ${imgLocation}</div>
                </div>
				<div> </div>

                <div class="profile-buttons" uid="858" liveuser-id="858">
                  <a
										href="${pageContext.request.contextPath}/edit_profile">
                    <button class="btn btn-edit">Edit Profile</button>
                  </a>
                </div>
              </div>
            </div>
            <div class="clear"></div>
          </div>
        </div>
<div class="profile-container-box">
<style>
.footer{
	background: white;
}
.profile-container-box{
	min-height: 210px;
}
.footer {
	background: white;
}

.profile-container-box {
	min-height: 210px;
}
</style>

<div class="container">
			<div class= "feed-block">
				<div class = "feed-title">About Me</div>
			</div>
		</div>
		<div class="container">
			<div class= "feed-block">
				<div class = "feed-title">Education</div>
				<div class = "feed-title">${education.getSchool() } ${education.getArea()}</div>
			</div>
		</div>	

        <a href = "${pageContext.request.contextPath}/chat"><h6>chat</h6>
        <div class="clear"></div>

        </div>

      </div>
      <div class="sidebar bcol-30">

        <div class="notify-block">
          <div>
            <div class="notify-title">
              <a href="http://feedstack.asia/milindhg/notifications">
                Pending Friends Requests <span class="unread"> 0 unread</span>
              </a>
              <c:forEach items="${pendingFriends}" var="friend">

                <div class="bcol-member-block">
                  <%--                   <div class="member-image">
                    <a href="${pageContext.request.contextPath}/profile?userId=${friend.user1.firstName}">
                      <img src="http://feedstack.asia/img/user.jpg" class="member">		</a>
                  </div> --%>
                  <div class="member-name">
                    <a href="${pageContext.request.contextPath}/profile?userId=${friend.user2.userId}">${friend.user2.firstName}</a>
                          <button id="acceptFriendBtn_${friend.user2.userId}"
											class="btn btn-edit"
											onclick="acceptFriend(${friend.user2.userId},${friend.user1.userId})">
                            Accept Friend
                          </button>

                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>

      <div class="clear"></div>

    </div>
  </div>
  </jsp:body>
</t:GlobalTemplate>
