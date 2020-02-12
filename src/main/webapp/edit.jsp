<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<section>
    <h2>Редактирование</h2>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Дата</dt>
            <dd><label>
                <input type="date" name="date" value="${meal.date}">
            </label></dd>
            <dt>Время</dt>
            <dd><label>
                <input type="time" name="time" value="${meal.time}">
            </label></dd>
            <dt>Описание</dt>
            <dd><label>
                <input type="text" name="description" size="20" value="${meal.description}">
            </label></dd>
            <dt>Калории</dt>
            <dd><label>
                <input type="number" name="calories" size="5" value="${meal.calories}">
            </label></dd>
        </dl>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>
