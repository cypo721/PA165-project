<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="New revision">
<jsp:attribute name="body">
    
<div class="container">
    <div class="col-md-4">
        <form:form method="post" modelAttribute="revision" action="${editAction}">
            <div class="form-group">
                <label for="dateOfRevision">Date of revision (YYYY-MM-DD)</label>
                <form:input type="date" path="dateOfRevision" cssClass="form-control" id="dateOfRevision" placeholder="Date of revision"/>
            </div>
            <div class="form-group">
                <label for="info">Info</label>
                <form:input path="info" cssClass="form-control" id="info" placeholder="Info"/>
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
        <a href="${pageContext.request.contextPath}/revision/list">Back to revisions</a>
    </p>
</jsp:attribute>
</my:template>
