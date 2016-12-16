<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <!-- bootstrap loaded from content delivery network -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css"/>
    <jsp:invoke fragment="head"/>
</head>
<body>

</form>
<%--<!-- navigation bar -->--%>
<%--<nav class="navbar navbar-default">--%>
    <%--<div class="container-fluid">--%>
        <%--<!-- Brand and toggle get grouped for better mobile display -->--%>

    <%--</div>--%>
<%--</nav>--%>

<div class="container">
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">PA165 Rental of Machinery</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/machine/list">Machines</a></li>

                    <li><a href="${pageContext.request.contextPath}/revision/list">Revisions</a></li>

                    <li><a href="${pageContext.request.contextPath}/rental/list">Rentals</a></li>
                    
                    <li><a href="${pageContext.request.contextPath}/user/list">Users</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#/admin/products">Products</a></li>
                            <li><a href="#/admin/categories">Categories</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer text-center">
        <hr/>
        <div class="container">
            <p class="text-muted">&copy; FI MUNI 2016 - PA165 - Team 6</p>
        </div>
    </footer>

</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>