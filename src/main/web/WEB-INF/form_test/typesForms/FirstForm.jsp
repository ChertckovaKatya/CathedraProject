<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 18.05.2019
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <title>Вопрос первого типа</title>
</head>
<body>
    <c:set var="num" scope="request" value="${idTest}" />
    <c:set var="numquest" scope="request" value="${idQuestion}" />
        <form action="testdemonstration?type=1&idTest=<c:out value="${num}"/>" method="post">
            <c:forEach var="res" items="${result}">
                <p><b> <c:out value="${res.question}"/></b></p>
                <c:forEach var="ans" items="${res.answers}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="answer" id="exampleRadios" value="<c:out value="${ans.idAnswer}"/>">
                        <label class="form-check-label" for="exampleRadios">
                            <c:out value="${ans.value}"/>
                        </label>
                    </div>
                </c:forEach>
            </c:forEach>
    <button type="1" value="<c:out value="${numquest}"/>"> Ответить</button>
</form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
