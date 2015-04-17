<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
	<jsp:body>
<script>
	$(document).on('keyup','.member-search',function (event) { searchUsers()});
	/* $(function() {
		$('.member-search').keyup(alert('oh yes'))
	});
 */
	function searchUsers() {
		//alert($('#txtPost').val());
		var searchKeyValue = $('#txtPost').val();
		if(searchKeyValue != ''){
		//$.post( "${pageContext.request.contextPath}/search/users", { searchKey: "milind"} );
		$
				.ajax({
					headers : {
						'Accept' : 'application/json'
					},
					method : "POST",
					url : "${pageContext.request.contextPath}/search/users",
					data : {
						searchKey : searchKeyValue
					},
					success : function(data) {
						//alert(data.length);
						$('.member-container').html('');
						for (i = 0; i < data.length; i++) {
							$('.member-container')
									.append(
											"<div class=\"bcol-member-block\"> <div class=\"member-image\"> <a href=\"${pageContext.request.contextPath}/profile?userId=" + data[i].userId + "\"> <img src=\"http://feedstack.asia/img/user.jpg\" class=\"member\"> </a> </div> <div class=\"member-name\"> <a href=\"${pageContext.request.contextPath}/profile?userId=" + data[i].userId + "\">"
													+ data[i].firstName
													+ " "
													+ data[i].lastName
													+ "</a> </div> </div>");
						}
						$('.member-container').append(
								"<div class=\"clear\"></div>");
					}
				});
		}
		else{
			$('.member-container').html('');
		}
		//window.location.href = "${pageContext.request.contextPath}/search/users?searchKey=" + $('#txtPost').val();
	};
</script>  

 
    <div class="container-main pad-20">
      <div class="app">
<!--         <script src="http://feedstack.asia/app/script/members.js"></script> -->
        <div class="member-body">
          <div class="header-member">
            <i class="fa fa-space fa-group"></i> Search Users
            <span class="members-count"></span>
            <span class="member-seach-span">
              <input type="text" class="member-search"
							placeholder="search..." root="http://feedstack.asia/"
							id="txtPost">
							<button class="post-btn" onclick="searchUsers()">Search</button>
            </span>
          </div>
          <div class="root" root="http://feedstack.asia/"
						access-token=""></div>
          <div class="entry">
            <div class="member-container">
<%--               <c:forEach items="${users}" var="element">

                <div class="bcol-member-block">
                  <div class="member-image">
                    <a href="http://feedstack.asia/renudeshmukh">
                      <img src="http://feedstack.asia/img/user.jpg" class="member">		</a>
                  </div>
                  <div class="member-name">
                    <a href="http://feedstack.asia/renudeshmukh">${element.firstName}</a>
                  </div>
                </div>
              </c:forEach> --%>
<!--               <div class="clear"></div> -->
            </div>
          </div>
        </div>
      </div>
    </div>


  </jsp:body>
</t:GlobalTemplate>

