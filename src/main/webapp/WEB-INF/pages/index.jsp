<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ads</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <p>
            <form:form method="post" action="add" commandName="user-entity">
                <form:label path="userName">User name</form:label>
                <form:input path="userName"/>
                <br/>
                <form:label path="userPassword">User password</form:label>
                <form:password path="userPassword"/>
                <br/>
                <input type="submit" value="Submit" class="btn" />
            </form:form>
        </p>

        <c:if test="${!empty users}">
            <h3>Users</h3>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>&nbsp;</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.userName}</td>
                        <td>${user.userPassword}</td>
                        <td>${user.role.roleName}</td>
                        <td>
                            <form action="delete?id=${user.userId}" method="post">
                                <input type="submit"
                                       class="btn btn-danger"
                                       value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
