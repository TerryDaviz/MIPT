<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Functions</title>
</head>
<body>
  <h1>Functions</h1>
<%--  <c:set var="sampleString" value="I love java (eto obman)"/>--%>
<%--  <c:set value="Input string: "/><c:out value="${sampleString}"/>--%>
<%--  <c:if test="${fn:contains(sampleString,'java')}">--%>
<%--      String contains java (cringe)--%>
<%--  </c:if>--%>
<%--  <c:if test="${fn:containsIgnoreCase(sampleString,'a')}">--%>
<%--    String contains 'a'--%>
<%--  </c:if>--%>
<%--  <c:if test="${fn:endsWith(sampleString,')')}">--%>
<%--    String ends with ')'--%>
<%--  </c:if>--%>
  <c:set var = "string1" value = "I love studiyng Java."/>
  <c:set var = "string2" value = "I actually don't like studiyng Java."/>
  Index of 'java': ${fn:indexOf(string1, "Java")}<br/>
  Index of 'don't': ${fn:indexOf(string2, "don't")}<br/>

  <c:set var = "string1" value = "I love studiyng Java."/>
  <c:set var = "string2" value = "${fn:split(string1, ' ')}" />
  <c:set var = "string3" value = "${fn:join(string2, '-')}" />
  ${string3}<br/><br/>

  <c:set var = "string1" value = "I love studiyng Java."/>
  Length of this string: ${fn:length(string1)}<br/><br/>

  <c:set var = "string1" value = "I love studiyng Java."/>
  <c:set var = "string2" value = "${fn:replace(string1, 'Java', 'Haskell')}" />
  ${string2}<br>
</body>
</html>