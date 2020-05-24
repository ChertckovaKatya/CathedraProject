<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 25.05.2019
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
        <header>
            <div class="container-fluid">
                <%@include file='patterns/panel_index.jsp'%>

            </div>
        </header>
        <section class="a">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-lg-4 a1">A1</div>
                    <div class="col-sm-6 col-lg-4 a2">A2</div>
                    <div class="clearfix visible-sm-block visible-md-block"></div>
                    <div class="col-sm-6 col-lg-4 a3">A3</div>
                    <div class="clearfix visible-lg-block"></div>
                    <div class="col-sm-6 col-lg-4 a4">A4</div>
                    <div class="clearfix visible-sm-block visible-md-block"></div>
                    <div class="col-sm-6 col-lg-4 a5">A5</div>
                    <div class="col-sm-6 col-lg-4 a6">A6</div>
                </div>
            </div>
        </section>
        <section class="b">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-lg-3 b1">B1</div>
                    <div class="col-sm-6 col-lg-3 b2">B2</div>
                    <div class="clearfix visible-sm-block visible-md-block"></div>
                    <div class="col-sm-6 col-lg-3 b3">B3</div>
                    <div class="col-sm-6 col-lg-3 b4">B4</div>
                </div>
            </div>
        </section>
        <footer>
            <div class="container-fluid">
                FOOTER
            </div>
        </footer>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <% if (request.getAttribute("result")!=null){
            System.out.println((request.getAttribute("result")));
            if(request.getAttribute("result").equals("Error")){%>
        <script>
            $("#myModalBox").modal();
        </script>
        <%}}%>
</body>
</html>
