<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 31.05.2019
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file='patterns/panel_user.jsp'%>
    <title>Title</title>
</head>
<body>
<div></div>
<header>
    <selection>
        <div></div>
        <div></div>
        <div></div>
    <c:forEach var="test" items="${tests}">
        <table>
            <p><a href="${pageContext.request.contextPath}/testdemonstration?idTest=<c:out value="${test.idTest}"/>"><c:out value="${test.titleTest}"/></a></p>
        </table>
    </c:forEach>
    </selection>
</header>

</body>
</html>
