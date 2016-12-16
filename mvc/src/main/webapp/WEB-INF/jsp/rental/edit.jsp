<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="Edit rental">
<jsp:attribute name="body">
    
<div class="container">
    <div class="col-md-4">
        <form:form method="post" modelAttribute="rental" action="${editAction}">
            <div class="form-group">
                <label for="dateFrom">From</label>
                <form:input path="dateFrom" cssClass="form-control" id="dateFrom" placeholder="From" value="${rental.dateFrom}"/>
            </div>
            <div class="form-group">
                <label for="dateTo">To</label>
                <form:input path="dateTo" cssClass="form-control" id="dateTo" placeholder="To" value="${rental.dateFrom}"/>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <form:input path="price" cssClass="form-control" id="price" placeholder="Price" value="${rental.price}"/>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/rental/list">Back to rentals</a>
    </p>
</div>
</jsp:attribute>
</my:template>