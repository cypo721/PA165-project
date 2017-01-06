
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="New rental">
<jsp:attribute name="body">
    
<div class="container">
    <div class="col-md-4">
        <form:form method="post" modelAttribute="rental" action="${editAction}">
            <div class="form-group">
                <label for="dateFrom">From (YYY-MM-DD)</label>
                <form:input type="date" path="dateFrom" cssClass="form-control" id="dateFrom" placeholder="From"/>
            </div>
            <div class="form-group">
                <label for="dateTo">To</label>
                <form:input type="date" path="dateTo" cssClass="form-control" id="dateTo" placeholder="To"/>

            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <form:input path="price" cssClass="form-control" id="price" placeholder="Price"/>
            </div>
            <div class="form-group">
                <label for="price">Machine</label>
                <form:select path="machine" cssClass="form-control">
                    <c:forEach items="${machines}" var="m">
                        <form:option value="${m.id}">${m.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label for="usr">User</label>
                <form:select path="user" cssClass="form-control">
                    <c:forEach items="${users}" var="u">
                        <form:option value="${u.email}">${u.email}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
    </div>
</div>
    <p>
        <a href="${pageContext.request.contextPath}/rental/list">Back to rentals</a>
    </p>
</jsp:attribute>
</my:template>
