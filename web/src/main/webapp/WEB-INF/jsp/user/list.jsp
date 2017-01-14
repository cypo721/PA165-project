<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:template title="Users">
    <jsp:attribute name="body">
<div class="row">
    <br>
    <div>
        <a href="${pageContext.request.contextPath}/user/new"
           class="btn btn-success">New user</a>
    </div>
    <br>
    <table class=" table table-striped">
        <th>Id</th>
        <th>E-mail</th>
        <th>Role</th>
        <th>Joined</th>
        <th></th>
        <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td><c:out value="${user.joinedDate}"/> </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/edit/${user.id}"
                           class="btn btn-default">Edit</a>
                        <a href="${pageContext.request.contextPath}/user/delete/${user.id}"
                           class="btn btn-danger">Delete</a>
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

