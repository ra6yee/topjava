<%--
  Created by IntelliJ IDEA.
  User: Привет
  Date: 05.06.2022
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>MEAL</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>MEAL-TOTAL1</h2>
<table border="2">
    <tr>
<%--<jsp:useBean id="list"  type="ru.javawebinar.topjava.model.Meal"/>--%>
<c:forEach  var="list" items="${list}" >
   <tr>
    <td> ${list.description} </td>
    <td> ${list.calories} </td>
    <td> ${list.dateTime} </td>
    <td>${list.excess}</td>
     </tr>
</c:forEach>
    </tr>
</table>
</body>
</html>
