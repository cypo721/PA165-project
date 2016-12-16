<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:template title="Login">
<jsp:attribute name="body">
        <c:if test="${param.error ne null}">
            <div class="alert alert-danger" role="alert">Failed to login</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
                <sec:csrfInput/>
                <div class="form-group"><label for="username"> Email: <input type="text" class="form-control"
                                                                                  name="email" id="email"/>
                </label></div>
                <div class="form-group"><label for="password"> Password: <input type="password" class="form-control"
                                                                                name="password" id="password"/>
                </label></div>
                <div><input type="submit" class="btn btn-default" value="Login"/></div>
        </form>
</jsp:attribute>
</my:template>