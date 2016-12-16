<%--
  Created by IntelliJ IDEA.
  User: pato
  Date: 15.12.2016
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:template title="Machines">
    <jsp:attribute name="body">
<div class="row">
    <a href="${pageContext.request.contextPath}/machine/new"
       class="btn btn-success">New machine</a>
    <table class=" table table-striped">
        <th>Číslo</th>
        <th>Meno</th>
        <th>Cena</th>
        <th>Typ</th>
        <th>Dátum zakúpenia</th>
        <th>Dátum revizie</th>
        <th></th>
        <c:forEach items="${machines}" var="m">
                <tr>
                    <td><c:out value="${m.id}"/></td>
                    <td><c:out value="${m.name}"/></td>
                    <td><c:out value="${m.pricePerDay}"/> [CZK]</td>
                    <td><c:out value="${m.machineType}"/> </td>
                    <td><fmt:formatDate value="${m.dateOfBuy}" pattern="yyyy-MM-dd"/> </td>
                    <td><fmt:formatDate value="${m.dateOfLastRevision}" pattern="yyyy-MM-dd"/> </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/machine/edit/${m.id}"
                           class="btn btn-default">Edit</a>
                        <a href="${pageContext.request.contextPath}/machine/delete/${m.id}"
                           class="btn btn-danger">Delete</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
</div>

    </jsp:attribute>
</my:template>

