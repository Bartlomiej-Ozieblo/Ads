<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css"/>" />--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>">--%>
    <%--<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>--%>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <style>
        body {
            padding-top: 50px;
            vertical-align: middle;
        }

        .starter-template {
            padding: 40px 15px;
            vertical-align: middle;
            text-align: center;
        }

        .form-center {
            width: 50%;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Ads</a>
        </div>
        <div class="navbar-collapse collapse">
            <form:form class="navbar-form navbar-right" action="/j_spring_security_check">
                <div class="form-group">
                    <input type="text" placeholder="Login" id="j_username" name="j_username" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" id="j_password" name="j_password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Log in</button>
            </form:form>
        </div>
    </div>
</div>

<div class="container">
    <div class="starter-template">
        <form:form class="form-signin" role="form" commandName="register-placeholder" action="register/now" method="post">
            <div class="form-center">
                <h1 class="form-signin-heading">Register</h1> <br/>
                <form>
                    <div class="form-group">
                        <form:label for="inputLogin" path="user.userName">Login</form:label>
                        <form:input type="text" class="form-control" id="inputLogin" placeholder="Login" path="user.userName" />
                    </div>
                    <div class="form-group">
                        <form:label for="inputEmail" path="contact.email">Email</form:label>
                        <form:input type="email" class="form-control" id="inputEmail" placeholder="Email" path="contact.email" />
                    </div>
                    <div class="form-group">
                        <form:label for="inputPassword" path="user.userPassword">Password</form:label>
                        <form:input type="password" class="form-control" id="inputPassword" placeholder="Password" path="user.userPassword"/>
                    </div>
                    <div class="form-group">
                        <form:label for="inputRepeatPassword" path="repeatPassword">Repeat password</form:label>
                        <form:input type="password" class="form-control" id="inputRepeatPassword" placeholder="Repeat password" path="repeatPassword" />
                    </div>
                    <button class="btn btn-primary" type="submit">Register now</button>
                </form>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
