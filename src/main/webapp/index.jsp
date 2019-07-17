<%--
  Created by IntelliJ IDEA.
  User: a8243
  Date: 2019/7/17
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试上传图片界面</title>
</head>
<body>
<%--用于测试文件上传功能页面--%>
<form action="${pageContext.request.contextPath}/pic/uploadPic" method="post" enctype="multipart/form-data">
    <input type="text" name="name"><br>
    <input type="file" name="imageFile"><br>
    <input type="submit">
</form>

</body>
</html>
