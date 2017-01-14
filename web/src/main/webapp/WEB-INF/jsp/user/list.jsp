<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="Users">
    <jsp:attribute name="body">
<div class="row">
    <br>
    <div>
        <sec:authorize access="hasAuthority('ADMIN')">
    <a href="${pageContext.request.contextPath}/user/new"
       class="btn btn-success">New user</a>
        </sec:authorize>
    </div>
    <br>
    <table class=" table table-striped">
        <th>Id</th>
        <th>E-mail</th>
        <th>Role</th>
        <th></th>
        <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>

                        <sec:authorize access="hasAuthority('ADMIN')">
                        <a href="${pageContext.request.contextPath}/user/delete/${user.id}"
                           class="btn btn-danger">Delete</a>
                        </sec:authorize>
                    </td>
                </tr>
        </c:forEach>
    </table>
</div>

    </jsp:attribute>
    <jsp:attribute name="script">
<script>
    $(function () {
        $('#list-users').on('change', function () {
            var url = $(this).val();
            if (url) {
                window.location = url;
            }
            return false;
        });
    });
</script>
</jsp:attribute>
</my:template>

