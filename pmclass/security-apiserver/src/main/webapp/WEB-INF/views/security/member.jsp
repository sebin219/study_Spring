<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 25. 6. 23.
  Time: 오후 3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>/security/member page</h1>
<h2> 회원, 관리자 모두 접근 가능</h2>
<form action="/security/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="로그아웃"/>
</form>
</body>
</html>
