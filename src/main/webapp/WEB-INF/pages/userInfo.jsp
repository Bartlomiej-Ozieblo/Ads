<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <li><a href="/user/${pageContext.request.userPrincipal.name}">My ads</a></li>
                <li><a href="/user/${pageContext.request.userPrincipal.name}/ad/add">Add ad</a></li>
                <li class="active"><a href="#">My info</a></li>
            </ul>
        </div>
        <!--/span-->

        <div class="col-sm-9 col-md-10 main">

            <!--toggle sidebar button-->
            <p class="visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">
                    <i class="glyphicon glyphicon-chevron-left"></i></button>
            </p>

            <h1 class="page-header">
                Contact info
                <p class="lead">ew. podtytul bo ladnie</p>
            </h1>

            <form:form class="form-signin" role="form" commandName="user_entity" action="info/edit" method="post">
                <div class="form-center">
                    <form>
                        <div class="form-group">
                            <form:label for="inputUsername" path="userName">Username</form:label>
                            <form:input type="text" class="form-control" id="inputUsername" disabled="disabled" path="userName" readonly="true" />
                        </div>
                        <div class="form-group">
                            <form:label for="inputPhoneNumber" path="contact.phoneNumber">Phone number</form:label>
                            <form:input type="text" class="form-control" id="inputPhoneNumber" placeholder="Phone number" path="contact.phoneNumber" />
                        </div>
                        <div class="form-group">
                            <form:label for="inputAddress" path="contact.address">Address</form:label>
                            <form:textarea type="text" class="form-control" id="inputAddress" placeholder="Address" rows="3" path="contact.address"/>
                        </div>
                        <div class="form-group">
                            <form:label for="inputEmail" path="contact.email">E-mail</form:label>
                            <form:input type="email" class="form-control" id="inputEmail" placeholder="E-mail" path="contact.email" />
                        </div>
                        <div class="form-group">
                            <form:label for="inputPassword" path="userPassword">Password</form:label>
                            <form:input path="userPassword" type="password" id="inputPassword" placeholder="Password" />
                        </div>
                        <button class="btn btn-primary" type="submit">Edit</button>
                    </form>
                </div>
            </form:form>
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