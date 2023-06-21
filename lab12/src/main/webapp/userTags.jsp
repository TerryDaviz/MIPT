<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="MyTag" uri="MyTag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<html>
<head>
    <title>user tags</title>
</head>
<body>
  <h1>user tags</h1>
  <MyTag:submit/>
  <c:set var="items" value="${items}"/>
  <c:set var="iterator" value="${items.iterator()}"/>
  <MyTag:sqlTable tableName="Users" itemsCount="${fn:length(items)}">
    <c:out value="${iterator.next()}"/>
  </MyTag:sqlTable>
</body>
</html>
