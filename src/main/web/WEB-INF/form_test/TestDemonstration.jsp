<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 25.05.2019
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
        <title>Title</title>
</head>
<body>

    <c:set var="num" scope="request" value="${id}" />
    <%--<c:out value="${num}"/>--%>
    <c:choose>
        <c:when test="${num == 1}">
            <c:import url='typesForms/FirstForm.jsp' charEncoding="utf-8"  />
        </c:when>
        <c:when test="${num == 2}">
            <c:import url='typesForms/SecondForm.jsp' charEncoding="utf-8"  />
        </c:when>
        <c:when test="${num == 3}">
            <c:import url='typesForms/ThirdForm.jsp' charEncoding="utf-8" />
        </c:when>
        <c:otherwise>
            <c:set var="proc" scope="request" value="${procent}" />
            <p>Тест завершен!!! Вы ответили верно на  <c:out value="${proc}"/> %</p>
            <li><a href="/alltestuser"> Вернуться к тестам </a></li>
        </c:otherwise>
    </c:choose>

</body>
</html>
