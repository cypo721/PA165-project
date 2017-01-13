
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="Edit revision">
<jsp:attribute name="body">
    
<div class="container">
    <div class="col-md-4">
        <form:form method="post" modelAttribute="revision" action="${editAction}">
            <div class="form-group">
                <label for="dateOfRevision">Date of revision (YYYY-MM-DD)</label>
                <form:input type="date" path="dateOfRevision" cssClass="form-control" id="dateOfRevision" placeholder="Date of revision"/>
                <p><form:errors path="dateOfRevision" cssClass="error" /></p>
            </div>
            <div class="form-group">
                <label for="info">Info</label>
                <form:input path="info" cssClass="form-control" id="info" placeholder="Info"/>
                <p><form:errors path="info" cssClass="error" /></p>
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
            <button type="submit" color="green" class="btn btn-success">Submit</button>
        </form:form>
            <br>
        <a class="btn btn-danger" color="red" href="${pageContext.request.contextPath}/revision/">Back to revisions</a>
    </div>
    
</div>
</jsp:attribute>
</my:template>