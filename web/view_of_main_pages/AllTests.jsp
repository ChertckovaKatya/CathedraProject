<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 27.05.2019
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file='patterns/panel_admin.jsp'%>
    <title>Title</title>
</head>
<body>

<selection>

<c:forEach var="test" items="${tests}">
    <table>
    <p><a href="/testdemonstration?idTest=<c:out value="${test.idTest}"/>"><c:out value="${test.titleTest}"/></a></p>
    </table>
</c:forEach>

</selection>
</body>
</html>
