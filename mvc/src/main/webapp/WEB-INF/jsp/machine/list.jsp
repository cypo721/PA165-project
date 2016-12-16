<%--
  Created by IntelliJ IDEA.
  User: pato
  Date: 15.12.2016
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
    <div>blbabla</div>

    <div class="row">
        <c:forEach begin="1" end="1" var="machine">
            <div class="col-xs-12 col-sm-6 col-md-2 col-lg-1">
                <p><button class="btn btn-default">Button ${machine}</button></p>
            </div>
        </c:forEach>
    </div>
</head>
<body>

</body>
</html>
