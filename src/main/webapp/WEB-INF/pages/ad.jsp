<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <c:forEach items="${ads}" var="ad">
        <thead>
        <tr>
            <th>Title</th>
            <th>Text</th>
            <th>Data</th>
            <th>Category</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${ad.title}</td>
                <td>${ad.text}</td>
                <td>${ad.createDate}</td>
                <td>${ad.category.categoryName}</td>
            </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
