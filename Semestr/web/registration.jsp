
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Регистрация</title>
</head>
<body>

<div class="RegDiv">

<h1>Регистрация</h1>

<p id="whatToDo">Для регистрации заполните форму</p>

<hr>

<form action="/registration" method="post">

    <label for="FirstName">Имя</label> <br>
    <input name="name" type="text" id="FirstName" placeholder="Имя">
    <br>
    <label for="LastName">Фамилия</label> <br>
    <input name="surname" type="text" id="LastName" placeholder="Фамилия">
    <br>
    <label for="Country">Страна</label> <br>
    <input name="country" type="text" id="Country" placeholder="Страна">
    <br>
    <label for="City">Город</label> <br>
    <input name="city" type="text" id="City" placeholder="Город">
    <br>
    <label for="Login">Логин</label> <br>
    <input name="login" type="text" id="Login" placeholder="Логин">
    <br>
    <label for="Password1">Пароль</label> <br>
    <input name="password1" type="password" id="Password1" placeholder="Пароль">
    <br>
    <label for="Password2">Повторите пароль</label> <br>
    <input name="password2" type="password" id="Password2" placeholder="Подтверждение">
    <br>
    <input type="submit" class="SubmitBotton">

    <%--Имя: <input name="name" type="text"><br>--%>
    <%--Фамилия: <input name="surname" type="text"><br>--%>
    <%--Страна: <input name="country" type="text"><br>--%>
    <%--Город: <input name="city" type="text"><br>--%>
    <%--Логин: <input name="login" type="text"><br>--%>
    <%--Пароль: <input name="password1" type="password"><br>--%>
    <%--Повторите пароль: <input name="password2" type="password"><br>--%>
    <%--<input type="submit">--%>
</form>

<a class="GoTo" href="/login">Уже есть аккаунт?</a><br>
<a class="GoTo" href="/index.jsp">Вернуться на главную</a><br>

</div>
</body>
</html>
