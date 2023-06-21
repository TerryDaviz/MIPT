<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>core</title>
</head>
<body>
<h1>
    Core tags
</h1>
<c:set var="user" scope="page">
    xd
</c:set>
<c:out value="${user}"/>

<p>==========Out output==========</p>
<c:out value="1337 * 420 / 228 + 69"/><c:out value="${1337 * 420 / 228 + 69}"/>
<br/>
<p>==========If-remove condition==========</p>
<c:set var="java" value="love" scope="page"/>
<c:if test="${not empty java and java eq 'love'}">
    I love java
</c:if>
<c:remove var="java"/>
<c:if test="${empty java}">
    (don't trust me it's lie)
</c:if>
<br/>

<p>==========choose/when/otherwise condition============<p>
    <c:set var="number" value="45"/>
    <c:choose>
    <c:when test="${ number > 60 }" >
        <c:out value="число ${ number } больше 60"/>
    </c:when>
    <c:when test="${ number > 40 }" >
        <c:out value="число ${ number } больше 40"/>
    </c:when>
    <c:when test="${ number > 10 }" >
        <c:out value="число ${ number } больше 10"/>
    </c:when>
    <c:otherwise>
        <c:out value="число ${ number } меньше 10"/>
    </c:otherwise>
    </c:choose>
    <br/><br/>
<p>----------------------- Исключения catch -----------------------</p>
<c:catch var="ArithmeticException">
    <% int num = 0 / 0; %>
</c:catch>
Caught exception: ${ArithmeticException}
<br/><br/>


<p>-------------------- Разделители forTokens --------------------</p>
<c:set var="str" value="a,.. b ;:- c :() d .., e ))" />
<c:forTokens var="token" items="${ str }" delims=".,-:);(">
    <c:out value="${ token }" />
</c:forTokens>
<br/><br/>
</body>
</html>