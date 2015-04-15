<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:GlobalTemplate>
  <jsp:body>
<script>
function savePost(){
	alert($('#txtPost').val());
	//$.post( "${pageContext.request.contextPath}/search/users", { searchKey: "milind"} );
	$.ajax({
		  headers:{'Accept': 'application/json'},
		  method: "POST",
		  url: "${pageContext.request.contextPath}/search/users",
		  data: { searchKey: "milind"},
		  success: function(data) { alert (data); 
		  //$('#memberName').html(data)}
		  $('.member-container').html(data)}
		});
	//window.location.href = "${pageContext.request.contextPath}/search/users?searchKey=" + $('#txtPost').val();
};

</script>  

<!--     <div class="container-main pad-20">
      <div class="app">
        <script src="http://feedstack.asia/app/script/members.js"></script>
        <div class="member-body">
          <div class="header-member">
            <i class="fa fa-space fa-group"></i> Search Users
          </div>
          <input type="email" name="email" width="100px" id="txtPost" placeholder="Search..."  />          
          <button class="post-btn" onclick="savePost()">Search</button>
          <div class="entry">
            <div class="member-container">
              <div class="clear"></div>
            </div>
          </div>

        </div>
      </div>
    </div>
 -->
 
    <div class="container-main pad-20">
      <div class="app">
<!--         <script src="http://feedstack.asia/app/script/members.js"></script> -->
        <div class="member-body">
          <div class="header-member">
            <i class="fa fa-space fa-group"></i> Search Users
            <span class="members-count"></span>
            <span class="member-seach-span">
              <input type="text"
							class="member-search" placeholder="search..."
							root="http://feedstack.asia/" id="txtPost">
							<button class="post-btn" onclick="savePost()">Search</button>
            </span>
          </div>
          <div class="root" root="http://feedstack.asia/" access-token=""></div>
          <p id="memberName">fefe </p>
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
              <div class="clear"></div>
            </div>
          </div>
        </div>
      </div>
    </div>


  </jsp:body>
</t:GlobalTemplate>

