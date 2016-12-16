<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rentals</title>
</head>
<body>
    
<h1>Rentals</h1>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Machine</th>
            <th>User</th>
            <th>From</th>
            <th>To</th>
            <th>Price</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rentals}" var="rental">
            <tr>
                <td><c:out value="${rental.id}"/></td>
                <td><c:out value="${rental.machine.name}"/></td>
                <td><c:out value="${rental.user.email}"/></td>
                <td><c:out value="${rental.dateFrom}"/></td>
                <td><c:out value="${rental.dateTo}"/></td>
                <td><c:out value="${rental.price}"/></td>
                <td><a href="${pageContext.request.contextPath}/rental/edit/${rental.id}">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>