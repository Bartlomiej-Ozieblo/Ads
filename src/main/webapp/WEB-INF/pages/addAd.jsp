<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Bootply.com - Dashboard with Off-canvas Sidebar</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script type='text/javascript' src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <style type="text/css">
        /*
         * Style tweaks
         * --------------------------------------------------
         */
        body {
            padding-top: 50px;
            background-color: #f5f5f5;
        }

        footer {
            padding-left: 15px;
            padding-right: 15px;
            background-color: #fff;
        }

        /*
         * Off Canvas
         * --------------------------------------------------
         */
        @media screen and (max-width: 768px) {
            .row-offcanvas {
                position: relative;
                -webkit-transition: all 0.25s ease-out;
                -moz-transition: all 0.25s ease-out;
                transition: all 0.25s ease-out;
            }

            .row-offcanvas-left
            .sidebar-offcanvas {
                left: -33%;
            }

            .row-offcanvas-left.active {
                left: 33%;
            }

            .sidebar-offcanvas {
                position: absolute;
                top: 0;
                width: 33%;
                margin-left: 10px;
            }
        }

        /* Sidebar navigation */
        .nav-sidebar {
            background-color: #f5f5f5;
            margin-right: -15px;
            margin-bottom: 20px;
            margin-left: -15px;
        }

        .nav-sidebar > li > a {
            padding-right: 20px;
            padding-left: 20px;
        }

        .nav-sidebar > .active > a {
            color: #fff;
            background-color: #428bca;
        }

        /*
         * Main content
         */

        .main {
            padding: 20px;
            background-color: #fff;
        }

        @media (min-width: 768px) {
            .main {
                padding-right: 40px;
                padding-left: 40px;
            }
        }

        .main .page-header {
            margin-top: 0;
        }

    </style>
</head>

<!-- HTML code from Bootply.com editor -->

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-toggle"></span>
            </button>
            <a class="navbar-brand" href="/">Ads</a>
        </div>
        <div class="navbar-collapse collapse">
            <c:if test="${not empty pageContext.request.userPrincipal.name}">
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
            </c:if>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar" role="navigation">
            <ul class="nav nav-sidebar">
                <c:choose>
                    <c:when test="${edit}">
                        <li class="active"><a href="/user/${pageContext.request.userPrincipal.name}">My ads</a></li>
                        <li><a href="/user/${pageContext.request.userPrincipal.name}/ad/add">Add ad</a></li>
                        <li><a href="/user/${pageContext.request.userPrincipal.name}/info">My info</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/user/${pageContext.request.userPrincipal.name}">My ads</a></li>
                        <li class="active"><a href="#">Add ad</a></li>
                        <li><a href="/user/${pageContext.request.userPrincipal.name}/info">My info</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!--/span-->

        <div class="col-sm-9 col-md-10 main">

            <!--toggle sidebar button-->
            <p class="visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">
                    <i class="glyphicon glyphicon-chevron-left"></i></button>
            </p>

            <c:choose>
                <c:when test="${not edit}">
                    <h1 class="page-header">
                        Add advertisement
                    </h1>
                </c:when>
                <c:otherwise>
                    <h1 class="page-header">
                        Edit advertisement
                    </h1>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${not edit}">
                    <form:form class="form-signin" role="form" commandName="ad_entity" method="post" action="add/now">
                        <div class="form-center">
                            <form>
                                <div class="form-group">
                                    <form:label for="inputTitle" path="title">Title</form:label>
                                    <form:input type="text" class="form-control" id="inputTitle" placeholder="Title"
                                                path="title"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="inputCategory" path="category.id">Category</form:label>
                                    <form:select id="inputCategory" class="form-control" path="category.id">
                                        <c:forEach items="${categories}" var="category">
                                            <form:option value="${category.id}">${category.categoryName}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <form:label for="inputText" path="text">Text</form:label>
                                    <form:textarea rows="8" class="form-control" id="inputText" path="text"/>
                                </div>
                                <button class="btn btn-primary" type="submit">Add</button>
                            </form>
                        </div>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form:form class="form-signin" role="form" commandName="ad_entity" method="post"
                               action="/user/${pageContext.request.userPrincipal.name}/ad/edit/${ad_entity.id}/now">
                        <div class="form-center">
                            <form>
                                <div class="form-group">
                                    <form:label for="inputTitle" path="title">Title</form:label>
                                    <form:input type="text" class="form-control" id="inputTitle" placeholder="Title"
                                                path="title" />
                                </div>
                                <div class="form-group">
                                    <form:label for="inputCategory" path="category.id">Category</form:label>
                                    <form:select id="inputCategory" class="form-control" path="category.id">
                                        <c:forEach items="${categories}" var="category">
                                            <c:choose>
                                                <c:when test="${ad.category.categoryName == category.categoryName}">
                                                    <form:option value="${category.id}"
                                                                 selected="true">${category.categoryName}</form:option>
                                                </c:when>
                                                <c:otherwise>
                                                    <form:option
                                                            value="${category.id}">${category.categoryName}</form:option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <form:label for="inputText" path="text">Text</form:label>
                                    <form:textarea rows="8" class="form-control" id="inputText" path="text"/>
                                </div>
                                <button class="btn btn-primary" type="submit">Change</button>
                            </form>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!--/row-->
</div>

<footer>
    <p class="pull-right">©2014 Company</p>
</footer>

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<!-- JavaScript jQuery code from Bootply.com editor -->

<script type='text/javascript'>

    $(document).ready(function () {
        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });
    });

</script>

</body>
</html>