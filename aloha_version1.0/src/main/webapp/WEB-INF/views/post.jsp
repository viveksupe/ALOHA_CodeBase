<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
    <jsp:body>

<script>
$(document).on('click','.feed-comment-count',function(){
		var feed_id = $(this).closest('.feed').attr('feed-id');
		var container = $('.comment-block-entry-'+feed_id);
		var comment_count =parseInt($('.comment-count-'+feed_id).html());
		var ele = $(this).next('.loading-likers');

		if(container.is(':visible')){
			container.slideUp();
		}else{
			ele.show();
			//var posturl = $('.root').attr('root')+'feed/commentload.html';
			//var posturl = '/comment.jsp';
			//$.post(posturl,{'feed_id':feed_id},function(data){
			//	container.html(data);
			//	container.slideDown();
			//});
			container.slideDown();
			ele.hide();
		}
	});

</script>


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
            
            <style>
                .footer {
                    background: white;
                }

                .profile-container-box {
                    min-height: 210px;
                }
            </style>

<c:forEach items="${posts}" var="element">
            <div class="feed feed-${element.getPostId()}" liveuser-id="" uid='845' feed-id='${element.getPostId()}'>
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
                                    <a href="http://feedstack.asia/feed/${element.getPostId()}"><i class="fa fa-eye "></i> 1 views </a>
                                </span>
                                <span class="feed-like-count feed-like feed-like-${element.getPostId()}" liveuser-id="" feed-id='${element.getPostId()}'
                                      unlike='0' count='0'>
                                    <i class="fa fa-thumbs-o-up"></i>
                                    <span class="like-count-${element.getPostId()}">0</span>
                                    <span class="likes-word-${element.getPostId()}"> Likes</span>
                                </span>
                                <span class="feed-comment-count" feed-id='${element.getPostId()}'>
                                    <i class="fa fa-comment-o "></i>
                                    <span class="comment-count-${element.getPostId()}">
                                        1
                                    </span> Comments
                                </span>
                                <span class="loading-likers" style="display:none"><img src="http://feedstack.asia/img/loading1.gif" /></span>
                                <div class="feed-time mobile-hidden">${element.getPostDate()}</div>
                            </small>
                        </div>
                        <div class='like-entry-${element.getPostId()}'><span class='you-like-${element.getPostId()}'></span></div>		
                        <div class="comment-block-entry-${element.getPostId()}" style="display:none">
                        
<jsp:include page="comment.jsp" >
    <jsp:param name="comments" value="${element.getComments()}" />
</jsp:include>

    					
                        </div>
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
