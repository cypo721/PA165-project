<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:template title="Revisions">
    <jsp:attribute name="body">
<div class="row">
    <a href="${pageContext.request.contextPath}/revision/new"
       class="btn btn-success">New revision</a>
    <table class=" table table-striped">
        <th>Číslo</th>
        <th>Čas revízie</th>
        <th>Info</th>
        <th>Stroj</th>
        <th>Zadal uživatel</th>
        

        <c:forEach items="${revisions}" var="r">
                <tr>
                    <td><c:out value="${r.id}"/></td>
                    <td><fmt:formatDate value="${r.dateOfRevision}" pattern="yyyy-MM-dd"/> </td>
                    <td><c:out value="${r.info}"/></td>
                    <td><c:out value="${r.machine.name}"/> </td>
                    <td><c:out value="${r.user.email}"/> </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/revision/edit/${r.id}"
                           class="btn btn-default">Edit</a>
                        <a href="${pageContext.request.contextPath}/revision/delete/${r.id}"
                           class="btn btn-danger">Delete</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
</div>

    </jsp:attribute>
</my:template>
