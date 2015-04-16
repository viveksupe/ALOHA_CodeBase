<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
  <jsp:body>
    <div class="container-main pad-20">
      <div class="app bcol-70">
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
        </script>
        <div class="wall">

          <div class="profile">
            <div class="profile-mobile-bg">

              <img src="http://feedstack.asia/img/user.jpg"
                            class="profile-image-mobile" />
              </div>
            <div class="bcol-30">
              <div class="feed-user mobile-hidden mobile-hidden-main-image">
                <img src="http://feedstack.asia/img/user.jpg" class="profile-image" />
			</div>
            </div>
            <div class="bcol-70">
              <div class="profile-container">
                <div class="profile-name">
                  <div class="profile-name-span">${user.getFirstName()} ${user.getLastName()}</div>
                </div>
                <div class="profile-desc">
                  Hey people im a new entry to this website..hope to have fun..follow me to get updates.
                </div>


                <div class="profile-buttons" uid="858" liveuser-id="858">
                  <a href="${pageContext.request.contextPath}/edit_profile">
                    <button class="btn btn-edit">Edit Profile</button>
                  </a>
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


          <br></br>

        </div>
      </div>
      <a href="${pageContext.request.contextPath}/chat">
      <h6>chat</h6></a>
      <div class="clear"></div>

    </div>
  </jsp:body>
</t:GlobalTemplate>
