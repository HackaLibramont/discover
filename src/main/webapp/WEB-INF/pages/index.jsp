<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 20-03-15
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value="/resources/Javascript/lists.js" />"></script>
    <title></title>
</head>
<body>

    <ul id="listActivity">
        <c:forEach var = "item" items="${activities}">
                <li>${item.toString()}</li>
        </c:forEach>
    </ul>
</body>
</html>
