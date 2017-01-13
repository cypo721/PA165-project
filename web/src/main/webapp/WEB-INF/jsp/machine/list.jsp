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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="${selected == 1 ? 'Machines' :  selected == 2 ? 'Free machines' : 'Unrevisioned machines'}" activeNav="machines">
    <jsp:attribute name="body">
<div class="row">
    <div class="machine-filters">
        <div class="row">
            <div class="col-lg-6">
                <select class="form-control" id="list-machines">
                    <option ${selected == 1 ? 'selected' : ''} value="${pageContext.request.contextPath}/machine/">
                        All machines
                    </option>
                    <option ${selected == 2 ? 'selected' : ''}
                        value="${pageContext.request.contextPath}/machine/free">
                        Free machines
                    </option>
                    <option ${selected == 3 ? 'selected' : ''}
                            value="${pageContext.request.contextPath}/machine/unrevisioned">
                        Unrevisioned machines in last year
                    </option>
                </select>
            </div>
            <div class="col-lg-6">

            </div>
        </div>
    </div>
    <br>
    <div>
        <sec:authorize access="hasAuthority('ADMIN')">
    <a href="${pageContext.request.contextPath}/machine/new"
       class="btn btn-success">New machine</a>
        </sec:authorize>
    </div>
    <br>
    <table class=" table table-striped">
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Type</th>
        <th>Date of buy</th>
        <th>Date of revision</th>
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
                        <sec:authorize access="hasAuthority('ADMIN')">
                        <a href="${pageContext.request.contextPath}/machine/edit/${m.id}"
                           class="btn btn-default">Edit</a>
                        <a href="${pageContext.request.contextPath}/machine/delete/${m.id}"
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
        $('#list-machines').on('change', function () {
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

