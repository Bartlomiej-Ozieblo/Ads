<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <!-- CSS code from Bootply.com editor -->

    <style type="text/css">
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

<!-- HTML code from Bootply.com editor -->

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
    <div class="page-header">
        <h1>${user.userName}</h1>
    </div>
    <c:choose>
        <c:when test="${not empty user.ads}">
            <c:forEach items="${user.ads}" var="ad">
                <div class="panel panel-default">
                    <div class="panel-heading">${ad.title}</div>
                    <div class="panel-body">${ad.text}</div>
                    <div class="panel-footer"><a href="/ad/${ad.id}">Check this out >></a></div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">There aren't any ads marked with this category yet.</div>
        </c:otherwise>
    </c:choose>

    <footer class="text-center">This Bootstrap 3 dashboard layout is compliments of <a
            href="http://www.bootply.com/85850"><strong>Bootply.com</strong></a></footer>

    <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</div>
</body>
</html>