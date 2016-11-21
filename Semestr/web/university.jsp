
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Об университете</title>
</head>
<body>
<div class="TablesDiv">

<c:if test="${current_user != null}">
    <a class="UpGoTo" href="/myPage">Личный кабинет</a><br>
    <a class="UpGoTo" href="/myPage?exit=true" >Выйти из аккаунта </a><br>
</c:if>
<c:if test="${current_user == null}">
    <a class="UpGoTo" href="/login">Войти</a><br>
    <a class="UpGoTo" href="/registration">Зарегистрироваться</a><br>
</c:if>

<h1>Университет: ${univ.name}</h1><br>
<hr>
<p class="labelLK">Страна: ${univ.country}<br>
Город: ${univ.city}<br>
<c:if test="${univ.address != null}">
Адрес: ${univ.address}<br>
</c:if>
<c:if test="${univ.about !=null}">
Об университете: ${univ.about}<br>
</c:if>
Специальности:</p><br>

<table>
    <tbody>
    <tr> <th>Название</th> <th>Предметы</th> <th>Баллы<br>(бюджет)</th> <th>Баллы<br>(платное дневное)</th> <th>Стоимость<br>(платное дневное)</th>
        <th>Баллы<br>(заочное)</th> <th>Стоимость<br>(заочное)</th>
        <th>Баллы<br>(вечернее)</th> <th>Стоимость<br>(вечернее)</th>
        <c:if test="${current_user != null}" >
            <th>Ваша сумма</th>
        </c:if>
    </tr>

    <c:forEach items="${univ.specialities}" var="spec">
    <tr>
        <td><a href="/specialities?id=${spec.id}">${spec.name}</a> </td>
        <td><c:out value="${spec.getSubjToString()}"></c:out> </td>
        <td><c:out value="${spec.points[0]}"></c:out> </td>
        <td><c:out value="${spec.points[1]}"></c:out> </td>
        <td><c:out value="${spec.costs[0]}"></c:out> </td>
        <td><c:out value="${spec.points[2]}"></c:out> </td>
        <td><c:out value="${spec.costs[1]}"></c:out> </td>
        <td><c:out value="${spec.points[3]}"></c:out> </td>
        <td><c:out value="${spec.costs[2]}"></c:out> </td>
        <c:if test="${user != null}" >
            <td>${userS.getPointInSpec(user.id,univ.id,spec.id)}</td>
        </c:if>
    </tr>

    </c:forEach>

    </tbody>
    </table>
    <br>

<a class="GoTo" href="/universities"> Вернуться к списку университетов</a><br>
<a class="GoTo" href="/index.jsp">Домой</a><br>

</div>
</body>
</html>
