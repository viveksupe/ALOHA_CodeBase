<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
    <jsp:body>



<div class="container-main pad-20">
    <div class="app bcol-70">
        <script src="http://feedstack.asia/app/script/profile.js"></script>
        <!-- ToolTip -->
        <link href="http://feedstack.asia/app/script/tooltip/css/tooltipster.css" rel="stylesheet">
        <script src="http://feedstack.asia/app/script/tooltip/js/jquery.tooltipster.min.js"></script>
        <script>
            $(function () {
                $('.tooltip').tooltipster();
            });
        </script>
        <div class="wall">

            <div class="profile">
                

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

<c:forEach items="${posts}" var="element">
            <div class="feed feed-637" liveuser-id="" uid='845' feed-id='637'>
                <div class="bcol-15">
                    <div class="feed-user mobile-hidden">
                        <a href="http://feedstack.asia/renudeshmukh">
                            <img src="http://feedstack.asia/img/user.jpg" class="feed-user-image" />
                        </a>
                    </div>
                </div>
                <div class="bcol-85">
                    <div class="feed-container">
                        <div class="feed-block">
                            <div class="bcol-10x mobile-visible">
                                <div class="feed-user">
                                    <a href="http://feedstack.asia/renudeshmukh">
                                        <img src="http://feedstack.asia/img/user.jpg" class="feed-user-image-mobile" />
                                    </a>
                                </div>
                            </div>
                            <div class="bcol-90x">
                                <div class="feed-title">
                                    <b><a href="http://feedstack.asia/renudeshmukh">${element.getUserName()}</a></b>
                                    <small class="feed-time-mobile mobile-visible">${element.getPostDate()} </small>
                                </div>
                            </div>

                            <div class="clear"></div>
                            <div class="feed-content">
                                <div class='article'>${element.getPostData()}</div>
                            </div>
                            <small>
                                <span class="feed-view-count">
                                    <a href="http://feedstack.asia/feed/637"><i class="fa fa-eye "></i> 1 views </a>
                                </span>
                                <span class="feed-like-count feed-like feed-like-637" liveuser-id="" feed-id='637'
                                      unlike='0' count='0'>
                                    <i class="fa fa-thumbs-o-up"></i>
                                    <span class="like-count-637">0</span>
                                    <span class="likes-word-637"> Likes</span>
                                </span>
                                <span class="feed-comment-count" feed-id='637'>
                                    <i class="fa fa-comment-o "></i>
                                    <span class="comment-count-637">
                                        1
                                    </span> Comments
                                </span>
                                <span class="loading-likers" style="display:none"><img src="http://feedstack.asia/img/loading1.gif" /></span>
                                <div class="feed-time mobile-hidden">5 hours ago </div>
                            </small>
                        </div>
                        <div class='like-entry-637'><span class='you-like-637'></span></div>		
                        <div class="comment-block-entry-637" style="display:none"></div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
</c:forEach>

            <div class="bcol-15">&nbsp;</div>
            <div class="bcol-85">
                <div class="no-feeds">No more Feeds</div>
            </div>
            <div class="clear"></div>
            <br>

        </div>
    </div>
       <div class="clear"></div>
</div>


    </jsp:body>
</t:GlobalTemplate>
