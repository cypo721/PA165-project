<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="Rentals">
<jsp:attribute name="body">

<div class="container">
    <a href="${pageContext.request.contextPath}/rental/new" class="btn btn-success">New rental</a>
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
                <td><a href="${pageContext.request.contextPath}/rental/edit/${rental.id}" class="btn btn-success">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</jsp:attribute>
</my:template>