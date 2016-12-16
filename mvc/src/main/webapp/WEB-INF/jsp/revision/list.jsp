<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:template title="Revisions">
    <jsp:attribute name="body">
<div class="row">
    <table class=" table table-striped">
        <th>Číslo</th>
        <th>Čas revízie</th>
        <th>Info</th>
        <c:forEach items="${revisions}" var="r">
                <tr>
                    <td><c:out value="${r.id}"/></td>
                    <td><c:out value="${r.dateOfRevision}"/></td>
                    <td><c:out value="${r.info}"/></td>
                </tr>
        </c:forEach>
    </table>
</div>

    </jsp:attribute>
</my:template>