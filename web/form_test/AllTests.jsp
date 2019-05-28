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
    <title>Title</title>
</head>
<body>
<c:forEach var="test" items="${tests}">
    <table>
        <tr>
            <p><a href="/testdemonstration?idTest=<c:out value="${test.idTest}"/>"><c:out value="${test.titleTest}"/></a></p>
        </tr>
    </table>

</c:forEach>

</body>
</html>
