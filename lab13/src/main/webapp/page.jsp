<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Основная страница</title>
    <link href="mainStyle.css" rel="stylesheet" type="text/css">
    <style>
    </style>
</head>
<body>
    <div class="container" >
        <div1>
        <jsp:include page="header.jsp"/>
            <c:catch var="Exception">
                <c:set var="currentUser" value="${user}" scope="page"/>
                <c:if test="${not empty user and currentUser eq 'user'}">
                    <c:out value="your role: user"></c:out>
                </c:if>
            </c:catch>
        </div1>
    </div>
    <center>
        <div1 >
            <h3 style="color: red">${error}</h3>
            <table border="2" cellpadding="4">
                <thead>
                <tr>
                    <th> Название канала</th>
                    <th> Численость подписчиков </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="channel" items="${channels}">
                    <tr>
                        <td>${channel.getName()}</td>
                        <td>${channel.getAmount()}</td>
                    </tr>
                </c:forEach>
                <br>
                </tbody>
            </table>
        </div1>


        <fieldset style="width: 30%; margin: 2%">
            <legend>Добавить</legend>
            <form action="${pageContext.request.contextPath}/controller?command=addChannel" method="post"   >
                <h3>Название канала:</h3>
                <input type="text" name="name"/>
                <h3>Количество подписчиков:</h3>
                <input type="text" name="subscribers"/>
                <br/><br/>
                <input type="submit" value="Добавить" ><br/>
            </form>
        </fieldset>


        <fieldset style="width: 30%; margin: 2%">
            <legend>Удалить</legend>
            <form action="${pageContext.request.contextPath}/controller?command=delChannel" method="post" >
                <h3>Удалить по названию:</h3>
                <input type="text" name="channelToDelete"/>
                <input type="submit" value="Удалить" ><br/>
            </form>
        </fieldset>
    </center>
</body>