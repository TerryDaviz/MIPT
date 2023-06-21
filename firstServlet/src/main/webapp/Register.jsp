<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div style="margin: 0 auto" align="center">
    <h1><%="Registration"%></h1>
    <form action="Register-Servlet" method="post">
        <p>${ServletResponse}</p>
        <p>Username</p>
        <input type="text" value="username" name="login">
        <p>Password</p>
        <input type="text" value="password" name="password">
        <input type="submit" value="register">
    </form>
</div>
</body>
</html>
