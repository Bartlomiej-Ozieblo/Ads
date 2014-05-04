<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Ads</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="../../resources/bootstrap/js/bootstrap.min.js" ></script>

    <style type="text/css">
        .starter-template {
            padding: 40px 15px;
            vertical-align: middle;
            text-align: center;
        }

        .form-center {
            width: 50%;
            vertical-align: middle;
        }

        .navbar-static-top {
            margin-bottom: 20px;
        }

        i {
            font-size: 16px;
        }

        .nav > li > a {
            color: #787878;
        }

        footer {
            margin-top: 20px;
            padding-top: 20px;
            padding-bottom: 20px;
            background-color: #efefef;
        }

        /* count indicator near icons */
        .nav > li .count {
            position: absolute;
            bottom: 12px;
            right: 6px;
            font-size: 10px;
            font-weight: normal;
            background: rgba(51, 200, 51, 0.55);
            color: rgba(255, 255, 255, 0.9);
            line-height: 1em;
            padding: 2px 4px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            -ms-border-radius: 10px;
            -o-border-radius: 10px;
            border-radius: 10px;
        }

        /* indent 2nd level */
        .list-unstyled li > ul > li {
            margin-left: 10px;
            padding: 8px;
        }
    </style>
</head>

<body>

<!-- Header -->
<div id="top-nav" class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-toggle"></span>
            </button>
            <a class="navbar-brand" href="/">Ads</a>
        </div>
        <div class="navbar-collapse collapse">
            <c:choose>
                <c:when test="${not empty pageContext.request.userPrincipal.name}">
                    <div class="container">
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a role="button" href="/user/${pageContext.request.userPrincipal.name}"><i
                                        class="glyphicon glyphicon-user"></i> ${pageContext.request.userPrincipal.name}
                                </a></li>
                                <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i
                                        class="glyphicon glyphicon-lock"></i> Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <form:form class="navbar-form navbar-right" action="/j_spring_security_check">
                        <div class="form-group">
                            <input type="text" placeholder="Login" id="j_username" name="j_username"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" id="j_password" name="j_password"
                                   class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Log in</button>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /Header -->

<!-- Main -->
<div class="container">
    <div class="starter-template">
        <c:choose>
            <c:when test="${not empty pageContext.request.userPrincipal.name}">
                <div class="alert alert-danger">You are currently logged in. Cannot register.</div>

                <form class="form-signin" role="form">
                    <div class="form-center">
                        <h1 class="form-signin-heading">Register</h1> <br/>
                        <form>
                            <div class="form-group">
                                <label for="disableLogin">Login</label>
                                <input type="text" class="form-control" id="disableLogin" placeholder="Login" disabled>
                            </div>
                            <div class="form-group">
                                <label for="disableEmail">Email</label>
                                <input type="email" class="form-control" id="disableEmail" placeholder="Email" disabled>
                            </div>
                            <div class="form-group">
                                <label for="disablePassword">Password</label>
                                <input type="password" class="form-control" id="disablePassword" placeholder="Password" disabled>
                            </div>
                            <div class="form-group">
                                <label for="disableRpPassword">Repeat password</label>
                                <input type="password" class="form-control" id="disableRpPassword" placeholder="Repeat password" disabled>
                            </div>
                            <button class="btn btn-primary disabled" type="submit">Register now</button>
                        </form>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <c:if test="${not empty param.error}">
                    <c:choose>
                        <c:when test="${param.error == 0}">
                            <div class="alert alert-warning">Username is not inserted.</div>
                        </c:when>
                        <c:when test="${param.error == 1}">
                            <div class="alert alert-warning">This username is already taken.</div>
                        </c:when>
                        <c:when test="${param.error == 2}">
                            <div class="alert alert-warning">Passwords not match.</div>
                        </c:when>
                        <c:when test="${param.error == 3}">
                            <div class="alert alert-warning">Password is length is less than 5 characters.</div>
                        </c:when>
                        <c:when test="${param.error == 4}">
                            <div class="alert alert-warning">E-mail is not inserted.</div>
                        </c:when>
                    </c:choose>
                </c:if>
                <form:form class="form-signin" role="form" commandName="register-placeholder" method="post" action="register/now">
                    <div class="form-center">
                        <h1 class="form-signin-heading">Register</h1> <br/>
                        <form>
                            <div class="form-group">
                                <form:label for="inputLogin" path="user.userName">Login</form:label>
                                <form:input type="text" class="form-control" id="inputLogin" placeholder="Login" path="user.userName" />
                            </div>
                            <div class="form-group">
                                <form:label for="inputEmail" path="user.contact.email">Email</form:label>
                                <form:input type="email" class="form-control" id="inputEmail" placeholder="Email" path="user.contact.email" />
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
            </c:otherwise>
        </c:choose>

    </div>
    <!-- /Main -->

    <footer class="text-center">This Bootstrap 3 dashboard layout is compliments of <a
            href="http://www.bootply.com/85850"><strong>Bootply.com</strong></a></footer>

    <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</div>
</body>
</html>