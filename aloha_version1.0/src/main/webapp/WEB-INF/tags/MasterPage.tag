 <%@tag description="put the tag description here" pageEncoding="UTF-8"%>
 
<%@attribute name="header" fragment="true" %>
 
<head>
    <link href="../../Content/Site2.css" rel="stylesheet" type="text/css" />
    <link href="../../Content/jquery-ui/css/cupertino/jquery-ui-1.8.17.custom.css"  rel="stylesheet" type="text/css" />
 
    <script src="../../Content/Scripts/jquery-1.5.1.min.js"></script>
    <script src="../../Content/Scripts/jquery-ui-1.8.11.min.js"></script>
    <script src="../../Content/Scripts/commonScript.js"></script>
</head>
 
<html>
  <body style="border:0px solid red;">
    <div id="pageheader" align="center">
      <jsp:invoke fragment="header"/>
      <p><a href="/common/">Home</a> | <a href="friends/">Friends List</a></p>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter" align="center" style=" background-image:url('../../Content/images/lgrey035.jpg');">
      <p id="copyright">**** Copyright 2015, Aloha Inc. ***</p>
      <!--jsp:invoke fragment="footer"/-->
    </div>
  </body>
</html>