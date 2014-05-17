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
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet"/>
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
                <li class="active"><a href="/admin/ads">Advertisements</a></li>
                <li><a href="/admin/categories">Categories</a></li>
                <li><a href="/admin/users">Users</a></li>
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
                Advertisements
            </h1>

            <div class="form-group">
                <form class="form-signin" role="form">
                    <div class="form-center">
                        <form>
                            <div class="form-group">
                                <label for="inputCategory">Choose category: </label>
                                <select id="inputCategory" class="form-control">
                                    <c:choose>
                                        <c:when test="${categoryFilter == true}">
                                            <option value="ALL">All</option>
                                            <c:forEach items="${categories}" var="category">
                                                <c:choose>
                                                    <c:when test="${category.id == currentCategory.id}">
                                                        <option value="${category.id}" selected>${category.categoryName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${category.id}">${category.categoryName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="ALL">All</option>
                                            <c:forEach items="${categories}" var="category">
                                                <option value="${category.id}">${category.categoryName}</option>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div>
                                <div class="form-group">
                                    <label for="inputUser">Choose user: </label>
                                    <select id="inputUser" class="form-control">
                                        <c:choose>
                                            <c:when test="${userFilter == true}">
                                                <option value="ALL">All</option>
                                                <c:forEach items="${users}" var="user">
                                                    <c:choose>
                                                        <c:when test="${currentUsers[0].userId == user.userId}">
                                                            <option value="${user.userId}" selected>${user.userName}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${user.userId}">${user.userName}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="ALL">All</option>
                                                <c:forEach items="${users}" var="user">
                                                    <option value="${user.userId}">${user.userName}</option>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                </form>
                <button class="btn btn-primary" onclick="filter()">Filter</button>
            </div>

            <br/><br/>

            <p class="lead">
                Category:
                <c:choose>
                    <c:when test="${not empty currentCategory}">
                        ${currentCategory.categoryName}
                    </c:when>
                    <c:otherwise>
                        all
                    </c:otherwise>
                </c:choose>
            </p>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Title</th>
                        <th>Date</th>
                        <th>Category</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${currentUsers}" var="user">
                        <c:choose>
                            <c:when test="${empty currentCategory}">
                                <c:forEach items="${user.ads}" var="ad">
                                    <tr>
                                        <td><a href="/admin/user/id/${user.userId}">${user.userName}</a></td>
                                        <td><a href="/ad/${ad.id}">${ad.title}</a></td>
                                        <td>${ad.createDate}</td>
                                        <td>${ad.category.categoryName}</td>
                                        <td>
                                            <a href="/admin/ad/id/${ad.id}/remove" class="btn-danger btn">DELETE</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${user.ads}" var="ad">
                                    <c:if test="${currentCategory.id == ad.category.id}">
                                        <tr>
                                            <td><a href="/admin/user/id/${user.userId}">${user.userName}</a></td>
                                            <td><a href="/ad/${ad.id}">${ad.title}</a></td>
                                            <td>${ad.createDate}</td>
                                            <td>${ad.category.categoryName}</td>
                                            <td>
                                                <a href="/admin/ad/id/${ad.id}/remove" class="btn-danger btn">DELETE</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--/.container-->

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type='text/javascript' src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<!-- JavaScript jQuery code from Bootply.com editor -->

<script type='text/javascript'>

    function filter() {
        var category = $('#inputCategory').val();
        var user = $('#inputUser').val();

        if (category != "ALL" && user != "ALL") {
            window.location.href = "/admin/ads/user/" + user + "/category/" + category;
        } else if (category != "ALL" && user == "ALL") {
            window.location.href = "/admin/ads/category/" + category;
        } else if (category == "ALL" && user != "ALL") {
            window.location.href = "/admin/ads/user/" + user;
        } else {
            window.location.href = "/admin/ads";
        }
    }

    $(document).ready(function () {
        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });
    });

</script>

</body>
</html>