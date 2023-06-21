<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <x:parse var="doc">
        <c:import url="bands.xml"/>
    </x:parse>
    <br/>
    <x:set var="firstBand" select="$doc/bands/band[1]/name"/>
    <h1>First band</h1>
    <x:out select="$firstBand/."/>
    <c:catch var="ex">
        <table>
            <tr>
                <th>Name</th>
                <th>Members amount</th>
                <th>Members</th>
            </tr>
            <x:forEach select="$doc/bands/band" var="item">
                <tr>
                    <td><x:out select="$item/name"/></td>
                    <td><x:out select="$item/membersAmount"/></td>
                    <td>
                    <x:forEach select="$item/members/member" var="member">
                       <x:out select="$member" />,
                    </x:forEach>
                    <td></td>
                </tr>
            </x:forEach>
        </table>
    </c:catch>
    <c:if test="${ex != null}">
        <p> Exception: ${ex}</p>
        <p> Message: ${ex.message}</p>
    </c:if>
</body>
</html>
