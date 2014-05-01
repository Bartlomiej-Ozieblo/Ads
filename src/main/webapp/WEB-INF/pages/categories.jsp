<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />--%>
    <%--<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>--%>
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
</div>
<div class="container">
    <div class="row">
        <div class="col-md-3">

            <ul class="nav nav-pills nav-stacked">
                <!--<li class="active"></li> dla aktualnego -->
                <c:if test="${!empty categories}">
                    <c:forEach items="${categories}" var="category">
                        <li><a href="/category/${category.categoryName}">${category.categoryName}</a></li>
                    </c:forEach>
                </c:if>
            </ul>

            <hr/>
        </div>

        <c:if test="${empty pageContext.request.userPrincipal.name}">
            <div class="col-md-9">
                <div class="row">
                    <div class="jumbotron">
                        <h1>Hello, guest!</h1>

                        <p>If you want to join our community please register. This will give you availability to create
                            and add your advertisements. If you want to just read existing ads, please be welcome. </p>

                        <p><a href="/register" class="btn btn-primary btn-lg" role="button">Join now!</a></p>
                    </div>

                </div>
            </div>
        </c:if>
    </div>

    <footer class="text-center">This Bootstrap 3 dashboard layout is compliments of <a
            href="http://www.bootply.com/85850"><strong>Bootply.com</strong></a></footer>
</div>
</body>
</html>
