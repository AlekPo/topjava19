<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Моя еда</h2>
<br>
<table border="1" cellpadding="8" cellspacing="0" style="margin: auto">
    <tr>
        <th><a href="meals?action=add"><img src="img/add.png" alt="add.png">Добавить еду</a></th>
    </tr>
</table>
<br>
<table border="1" cellpadding="8" cellspacing="0" style="margin: auto">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${mealsTo}" var="mealTo">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
    <tr target=${mealTo.excess}>
        <td>${mealTo.dateTime.toLocalDate()}&nbsp;${mealTo.dateTime.toLocalTime()}
        </td>
        <td>${mealTo.description}
        </td>
        <td>${mealTo.calories}
        </td>
        <td align="center"><a href="meals?id=${mealTo.id}&action=edit"><img src="img/pencil.png" alt="pencil.png"></a>
        </td>
        <td align="center"><a href="meals?id=${mealTo.id}&action=delete"><img src="img/delete.png" alt="delete.png"></a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>