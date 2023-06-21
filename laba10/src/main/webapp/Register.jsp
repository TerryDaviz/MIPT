<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1><%="Registration"%></h1>
    <form action="Register-Servlet" method="post">
        <p>${ServletResponse}</p>
        <h1>Username</h1>
        <input type="text" value="username" name="login">
        <h1>Password</h1>
        <input type="text" value="password" name="password">
        <input type="submit" value="register">
    </form>
</body>
</html>
