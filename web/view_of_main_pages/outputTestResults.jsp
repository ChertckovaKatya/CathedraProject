<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 31.05.2019
  Time: 14:27
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
<div></div>
<header>
</header>
<selection>
    <div></div>
    <c:forEach var="results" items="${res}">
        <table>
            <p> Название теста: <c:out value="${results.titleTest}"/> Количество правильных/неправильных ответов: <c:out value="${results.NumCorr}"/> :  <c:out value="${results.NumIncorr}"/></p>
        </table>
    </c:forEach>
</selection>

</body>
</html>
