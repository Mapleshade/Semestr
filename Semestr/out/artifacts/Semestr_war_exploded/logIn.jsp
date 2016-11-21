
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Вход</title>
</head>
<body>
<div class="LogInDiv">

    <h1>Вход</h1>

    <p id="whatToDo">Заполните логин и пароль</p>

    <hr>

<form action="/login" method="post">

    <label for="Login">Логин</label> <br>
    <input name="login" type="text" id="Login" placeholder="Логин">
    <br>
    <label for="Password1">Пароль</label> <br>
    <input name="password1" type="password" id="Password1" placeholder="Пароль">
    <br>
    <input type="submit" class="SubmitBotton">
    <%--Логин: <input name="login" type="text"><br>--%>
    <%--Пароль: <input name="password1" type="password"><br>--%>
    <%--<input type="submit"><br>--%>
</form>

<a class="GoTo" href="/registration">Зарегистрироваться</a><br>
<a class="GoTo" href="/index.jsp">Домой</a><br>

</div>
</body>
</html>
