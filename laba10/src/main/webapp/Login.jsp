<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 05.06.2023
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h2><%="Login"%></h2>
  <form method="post" action="Login-Servlet">
    Login
    <input type="text" name="login">
    <br/>
    <br/>
    Password
    <input type="text" name="password">
    <br/>
    <br/>
    <input type="submit" value="Login">
  </form>
</body>
</html>
