<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/15
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <title>用户</title>
</head>
<body>
        <sf:form method="POST" modelAttribute="user" action="/spittr/user/saveUser" >
            name : <sf:input path="name"></sf:input><br/>
            age:<sf:input path="age"></sf:input><br/>
            <input type="submit" value="提交">
        </sf:form>
</body>
</html>
