 <%@tag description="put the tag description here" pageEncoding="UTF-8"%>
 
<%@attribute name="header" fragment="true" %>
<html lang="en">
<head>
    <title>Aloha</title>
    <link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/bottle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/feed-menu.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/userlog.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/members.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/wall.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/profile.css" rel="stylesheet">
   
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/feed-menu.js"></script>
   
</head>
<body>
    <div class="header">
        <div class="container-main pad-header">
            <div class="bcol-25 logo">
                <a href="${pageContext.request.contextPath}">
                    <img width="250px" height="42px"  src="${pageContext.request.contextPath}/resources/img/logo.png">
                </a>
            </div>
            <div class="bcol-75 menu">
                <span class="menu-text">+ menu</span>

                <ul class="menu-bar">

                    <a href="${pageContext.request.contextPath}/search/users"><li><i class="fa fa-space fa-search"></i>Search</li></a>
                    <a href="${pageContext.request.contextPath}/post"><li><i class="fa fa-space fa-comments"></i>Posts</li></a>
                    <a href="${pageContext.request.contextPath}/user_profile">
                        <li>
                            <i class="fa fa-space fa-user">
                            </i>Profile
                        </li>
                    </a>
                    <a href="${pageContext.request.contextPath}/friends/index"><li><i class="fa fa-space fa-group"></i>Friends</li></a>
                    <a href="http://feedstack.asia/user/logout"><li><i class="fa fa-space fa-sign-out"></i>Logout</li></a>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="body">
          <jsp:doBody/>
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
                <div class="bcol-30">
                   
                </div>
                <div class="clear"></div>
            </div>
            <div class="ftr">Aloha - A social networking portal | a <a href="http://localhost:8080/common/">Aloha Inc.</a> production </div>
        </div>
    </div>
</body>
</html>