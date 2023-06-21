<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource var="users" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
url="jdbc:sqlserver://localhost:1433;database=java_lab9_Users;encrypt=false;user=sa;password=11"
user="sa" password="11"/>
<sql:query dataSource="${users}" var="allUsers">
    select * from InfoUsers
</sql:query>

<sql:query dataSource="${users}" var="admin" >
    Select * from InfoUsers where role = ?
    <sql:param value="admin"/>
</sql:query>
<table style="border: 1px solid black; width: 40%">
<tr>
    <th>Name</th>
    <th>Password</th>
    <th>Role</th>
</tr>

<c:forEach var = "row" items = "${allUsers.rows}">
    <tr>
        <td> <c:out value = "${row.name}"/></td>
        <td> <c:out value = "${row.password}"/></td>
        <td> <c:out value = "${row.role}"/></td>
    </tr>
</c:forEach>
</table>
<br/><br/><br/>
<table style="border: 1px solid black; width: 25%">
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Role</th>
    </tr>

    <c:forEach var = "row" items = "${admin.rows}">
        <tr>
            <td> <c:out value = "${row.name}"/></td>
            <td> <c:out value = "${row.password}"/></td>
            <td> <c:out value = "${row.role}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
