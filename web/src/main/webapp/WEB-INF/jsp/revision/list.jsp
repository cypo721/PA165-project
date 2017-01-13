<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="Revisions">
    <jsp:attribute name="body">
<div class="row">
    <br>
    <div>
    <a href="${pageContext.request.contextPath}/revision/new"      
       class="btn btn-success">New revision</a>
    </div>
    <br>
    <table class=" table table-striped">
        <th>Id</th>
        <th>Date of revision</th>
        <th>Info</th>
        <th>Machine</th>
        <th>Submited by user</th>
        <th></th>
        

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
