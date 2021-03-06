
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<my:template title="Rentals">
<jsp:attribute name="body">

<div class="container">
    <sec:authorize access="hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/rental/new" class="btn btn-success">New rental</a>
    </sec:authorize>

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
                <td><fmt:formatDate value="${rental.dateFrom}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${rental.dateTo}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${rental.price}"/></td>

                <td><sec:authorize access="hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')">
                        <a href="${pageContext.request.contextPath}/rental/edit/${rental.id}" class="btn btn-success">Edit</a>
                        <a href="${pageContext.request.contextPath}/rental/delete/${rental.id}" class="btn btn-danger">Delete</a>
                    </sec:authorize></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</jsp:attribute>
</my:template>

