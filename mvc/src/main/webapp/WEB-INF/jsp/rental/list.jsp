<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<my:template title="Rentals">
<jsp:attribute name="body">
    
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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rentals}" var="rental">
            <tr>
                <td><c:out value="${rental.id}"/></td>
                <td><c:out value="${rental.machine}"/></td>
                <td><c:out value="${rental.user}"/></td>
                <td><c:out value="${rental.dateFrom}"/></td>
                <td><c:out value="${rental.dateTo}"/></td>
                <td><c:out value="${rental.price}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</jsp:attribute>
</my:template>