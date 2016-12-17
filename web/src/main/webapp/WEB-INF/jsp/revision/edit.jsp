<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:template title="${revision.id == null ? 'New revision' : 'Edit revision'}">
<jsp:attribute name="body">
<div class="container">
    <div class="col-md-6">
        <form:form id="${revision.id}" method="post" modelAttribute="revision"
                   action="${pageContext.request.contextPath}/revision/save">
        <form:hidden path="id"  style="display:none"/>
        
        <div class="form-group">
            <label for="name">Info about revision</label>
            <form:input path="info" cssClass="form-control" id="name" placeholder="Name"/>
            <p><form:errors path="info" cssClass="error" /></p>
        </div>
        
        <div class="form-group">
            <label for="name">Date of revision</label>
            <form:input type="date" class="date" path="dateOfRevision" cssClass="form-control" id="name" placeholder="Name"/>
            <p><form:errors path="dateOfRevision" cssClass="error" /></p>
        </div>
        
         <div class="form-group">
            <form:label path="machine" cssClass="col-sm-2 control-label">Machine</form:label>
            <div class="col-sm-10">
                <form:select path="machine" cssClass="form-control">
                    <c:forEach items="${machines}" var="m">
                        <form:option value="${m.id}">${m.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="machine" cssClass="error"/></p>
            </div>
        </div>
            
            <div class="form-group">
            <form:label path="user" cssClass="col-sm-2 control-label">User</form:label>
            <div class="col-sm-10">
                <form:select path="user" cssClass="form-control">
                    <c:forEach items="${users}" var="u">
                        <form:option value="${u.id}">${u.surname}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="user" cssClass="error"/></p>
            </div>
        </div>
        
        

        <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
    </div>
</div>
</jsp:attribute>
</my:template>
