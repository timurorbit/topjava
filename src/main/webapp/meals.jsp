<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
        }
        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
        }
        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
        }
    </style>
</head>
<body >
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h3>All meals</h3>
<table class="tg">
<tr>
    <th width="150">ID</th>
    <th width="250">LocalDateTime</th>
    <th width="250">Description</th>
    <th width="250">Calories</th>
</tr>

<c:forEach var="meal" items="${requestScope.meals}">


    <c:choose>
    <c:when test="${meal.excess}">
            <tr style="background-color: red">
                <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy  HH:mm')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
                <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>
    </c:when>
        <c:otherwise>                                                                                           <%-- ill deal with this repeating next time ;=) --%>
                <tr style="background-color: green">
                    <td><c:out value="${meal.id}"/></td>
                <td><c:out value="${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy  HH:mm')}"/></td>
                <td><c:out value="${meal.description}"/></td>
                <td><c:out value="${meal.calories}"/></td>
                    <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
                    <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
                </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</table>
<p><a href="meal?action=add">Add Meal</a></p>

</body>
</html>