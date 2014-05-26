<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Ads</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
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
    <script type='text/javascript'>

        function changeRole(val) {
            if (val == "Admin") {
                window.location.href = '/admin/user/role/admin/${user.userId}';
            }
        }

        $(document).ready(function () {
            $('[data-toggle=offcanvas]').click(function () {
                $('.row-offcanvas').toggleClass('active');
            });
        });

    </script>
</head>


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
                            <li><a role="button" href="/user"><i
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
                <li><a href="/user">My ads</a></li>
                <li><a href="/user/ad/add">Add ad</a></li>
                <li><a href="/user/info">My info</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="/admin/ads">Advertisements</a></li>
                <li><a href="/admin/categories">Categories</a></li>
                <li class="active"><a href="/admin/users">Users</a></li>
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
                ${user.userName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                    <c:when test="${user.role.id == 1}">
                        <button class="btn-danger btn btn-lg disabled">DELETE</button>
                    </c:when>
                    <c:otherwise>
                        <a href="/admin/user/remove/id/${user.userId}" class="btn-danger btn btn-lg">DELETE</a>
                    </c:otherwise>
                </c:choose>
            </h1>

            <div>
                <p class="lead">Contact</p>
                <c:if test="${not empty user.contact.email}">
                    <b>Email: </b> ${user.contact.email}<br/>
                </c:if>
                <c:if test="${not empty user.contact.phoneNumber}">
                    <b>Phone number: </b> ${user.contact.phoneNumber}<br/>
                </c:if>
                <c:if test="${not empty user.contact.address}">
                    <b>Address: </b> ${user.contact.address}<br/>
                </c:if>
            </div>

            <br/><br/>

            <h1 class="page-header">
                Role
            </h1>

            <div class="form-group">
                <%--<form class="form-signin" role="form">--%>
                <div class="form-center">
                    <%--<form>--%>
                    <c:choose>
                        <c:when test="${user.role.id == 1}">
                            <div class="form-group">
                                <select class="form-control" disabled>
                                    <option>Admin</option>
                                </select>
                            </div>
                            <button class="btn btn-primary disabled" type="submit">Commit</button>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <select class="form-control" id="role_form">
                                    <option>User</option>
                                    <option>Admin</option>
                                </select>
                            </div>
                            <button class="btn btn-primary" onclick="changeRole($('#role_form').val())">Commit
                            </button>
                        </c:otherwise>
                    </c:choose>
                    <%--</form>--%>
                </div>
                <%--</form>--%>
            </div>

            <br/><br/>

            <h1 class="page-header">
                user's ads
            </h1>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Date</th>
                        <th>Category</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user.ads}" var="ad">
                        <tr>
                            <td><a href="/ad/${ad.id}">${ad.title}</a></td>
                            <td>${ad.createDate}</td>
                            <td>${ad.category.categoryName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.userName == pageContext.request.userPrincipal.name}">
                                        <a class="btn-danger btn" href="/admin/ad/id/${ad.id}/remove">DELETE</a>
                                    </c:when>
                                    <c:when test="${user.role.id == 1}">
                                        <a class="btn-danger btn disabled" href="#">DELETE</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn-danger btn" href="/admin/ad/id/${ad.id}/remove">DELETE</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--/.container-->

<script src="${pageContext.request.contextPath}/resources/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>