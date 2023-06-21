<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:root version="1.2" xmlns:fmt= "http://java.sun.com/jsp/jstl/fmt">--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:setLocale value="en-EN"/>
    English format date:<br/>
    Today: <fmt:formatDate value="${now}"/><br/>
    <fmt:setLocale value="ru-RU"/>
    Russian format date:<br/>
    Today: <fmt:formatDate value="${now}"/> <br/>
    Time style:
    short: <fmt:formatDate value="${now}" type="time" timeStyle="short"/><br/>
    medium: <fmt:formatDate value="${now}" type="time" timeStyle="medium"/><br/>
    long: <fmt:formatDate value="${now}" type="time" timeStyle="long"/><br/>

<h3>Number Format:</h3>
<c:set var = "balance" value = "120000.2309" />
<p>Formatted Number (1): <fmt:formatNumber value = "${balance}"
                                           type = "currency"/></p>
<p>Formatted Number (2): <fmt:formatNumber type = "number"
                                           maxIntegerDigits = "3" value = "${balance}" /></p>
<p>Formatted Number (3): <fmt:formatNumber type = "number"
                                           maxFractionDigits = "3" value = "${balance}" /></p>
<p>Formatted Number (4): <fmt:formatNumber type = "number"
                                           groupingUsed = "false" value = "${balance}" /></p>
<p>Formatted Number (5): <fmt:formatNumber type = "percent"
                                           maxIntegerDigits="3" value = "${balance}" /></p>
<p>Formatted Number (6): <fmt:formatNumber type = "percent"
                                           minFractionDigits = "10" value = "${balance}" /></p>
<p>Formatted Number (7): <fmt:formatNumber type = "percent"
                                           maxIntegerDigits = "3" value = "${balance}" /></p>
<p>Formatted Number (8): <fmt:formatNumber type = "number"
                                           pattern = "###.###E0" value = "${balance}" /></p>
<p>Currency in USA :
    <fmt:setLocale value = "en_US"/>
    <fmt:formatNumber value = "${balance}" type = "currency"/>
</p>
</body>
</html>
