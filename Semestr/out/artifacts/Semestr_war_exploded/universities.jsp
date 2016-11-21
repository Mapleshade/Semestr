
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Университеты</title>
</head>
<body>

<div class="TablesDiv">

    <h1>Список университетов</h1>

    <c:if test="${current_user != null}">
        <a class="UpGoTo" href="/myPage">Личный кабинет</a><br>
        <a class="UpGoTo" href="/myPage?exit=true">Выйти из аккаунта </a><br>
    </c:if>
    <c:if test="${current_user == null}">
        <a class="UpGoTo" href="/login">Войти</a><br>
        <a class="UpGoTo" href="/registration">Зарегистрироваться</a><br>
    </c:if>

    <hr>

    <table>
        <tbody>

        <tr>
            <th>Название</th>
            <th>Страна</th>
            <th>Город</th>
            <th>Адрес</th>
        </tr>

        <c:forEach items="${univs}" var="univ">
            <tr>
                <td><a href="/universities?id=${univ.id}">${univ.name}</a></td>
                <td><c:out value="${univ.country}"></c:out></td>
                <td><c:out value="${univ.city}"></c:out></td>
                <td><c:out value="${univ.address}"></c:out></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>


    <a class="GoTo" href="/index.jsp">Домой</a><br>

</div>
</body>
</html>
