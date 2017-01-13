<%--
  Created by IntelliJ IDEA.
  User: pato
  Date: 16.12.2016
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:template title="${machine.id == null ? 'New machine' : 'Edit machine'}">
<jsp:attribute name="body">
<div class="container">
    <div class="col-md-6">
        <form:form id="${machine.id}" method="post" modelAttribute="machine"
                   action="${pageContext.request.contextPath}/machine/save">
        <form:hidden path="id"  style="display:none"/>
        <form:hidden path="dateOfLastRevision"  style="display:none"/>
        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="name" cssClass="form-control" id="name" placeholder="Name"/>
        </div>

        <div class="form-group">
            <label for="name">Date of buy</label>
            <form:input type="date" class="date" path="dateOfBuy" cssClass="form-control" id="name" placeholder="Date of buy"/>
        </div>

        <%--<div class="form-group">--%>
            <%--<label for="name">Date of last revision</label>--%>
            <%--<form:input type="date" class="date" path="dateOfLastRevision" cssClass="form-control" id="name" placeholder="Date of last revision" />--%>
        <%--</div>--%>

            <div class="form-group">
                <label for="machineType">Machine Type</label>
                    <form:select path="machineType" cssClass="form-control">
                        <c:forEach items="${types}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

        <div class="form-group">
            <label for="price">Price</label>
            <div class="input-group">
                <form:input type="number" path="pricePerDay" cssClass="form-control" id="price" placeholder="Price"
                            aria-describedby="currency-addon"/>
                <span class="input-group-addon" id="currency-addon">CZK</span>
                </div>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
        <br>
        <br>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/machine/">Back to Machines </a>
    </div>
</div>
</jsp:attribute>
</my:template>
