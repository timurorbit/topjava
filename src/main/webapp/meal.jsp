<%--
  Created by IntelliJ IDEA.
  User: tholl
  Date: 11.02.2019
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">
<input type="text" readonly="readonly" name="mealid" hidden="hidden"
                 value="<c:out value="${meal.id}" />" /> <br />
DateTime : <input type="text" name="mealDate"
                  value="<c:out value="${f:formatLocalDateTime(meal.dateTime)}"/>" /> <br />
Description : <input type="text" name="mealdescription"
                  value="<c:out value="${meal.description}" />" /> <br />
Calories : <input type="text" name="mealcalories"
                 value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
