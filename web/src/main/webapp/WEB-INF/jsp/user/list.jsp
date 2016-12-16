<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="Users">
<jsp:attribute name="body">

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Joined date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.joinedDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</jsp:attribute>
</my:template>