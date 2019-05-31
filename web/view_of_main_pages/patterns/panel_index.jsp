<%--
  Created by IntelliJ IDEA.
  User: Fox
  Date: 25.05.2019
  Time: 14:22
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
        <div class="container">
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navber-collapse-1" aria-expanded="false">
                            <span class="sr-only"> Открыть навигацию </span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navber-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="/test_page"> Главная </a></li>
                            <li><a href="#"> Новости </a></li>
                            <li><a href="#"> Галерея </a></li>
                            <%--<li><a href="/testdemonstration"> Тест </a></li>--%>
                            <%--<li><a href="/alltest"> Все тесты </a></li>--%>
                        </ul>
                        <form method="post" action="" class="form-inline navbar-form navbar-right">
                            <div class="form-group">
                                <!-- <label for="inputlogin" class="sr-only"></label> -->
                                <input type="login" class="form-control" required="true" name="login" placeholder="Логин">
                            </div>
                            <div class="form-group">
                                <!-- <label for="inputpassword" class="sc-only"></label> -->
                                <input type="password" class="form-control"  required="true" name="password" placeholder="Пароль">
                            </div>
                            <!-- <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in" > </i>Войти
                            </button> -->
                            <button type="btn btn-info"  class="btn btn-primary" data-target="#myModalBox"><i class="fa fa-sign-in" > </i>Войти
                            </button>
                           <!--  <button  class="btn btn-info" data-toggle="modal" data-target="#myModalBox">Войти
                            </button> -->
                        </form>
                    </div>
                </div>
            </nav>
        </div>

        <!-- <button  class="btn btn-info" data-toggle="modal" data-target="#myModalBox">Кнопка
        </button> -->
        <div id="myModalBox" class="modal fade" tabindex="-1" role="document">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <!-- Заголовок модального окна -->
                    <div class="modal-header">
                        <!--  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                        <h4 class="modal-title">Упс, ошибка! </h4>
                    </div>
                    <!-- Основное содержимое модального окна -->
                    <div class="modal-body">
                        Неверный логин или пароль
                    </div>
                    <!-- Футер модального окна -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    </div>
                </div>
            </div>
        </div>

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
