<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <c:if test="${!empty ads}">
        <c:forEach items="${ads}" var="ad">
            <thead>
            <tr>
                <th>Title</th>
                <th>Text</th>
                <th>Category</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${ad.title}</td>
                <td>${ad.text}</td>
                <td>${ad.category.categoryName}</td>
            </tr>
            </tbody>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
