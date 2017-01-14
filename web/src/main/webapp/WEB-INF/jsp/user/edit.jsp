<%--
  Created by IntelliJ IDEA.
  User: marek
  Date: 14.1.2017
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:template title="${'New user'}">
<jsp:attribute name="body">
<div class="container">
    <div class="col-md-6">
        <form:form id="${user.id}" method="post" modelAttribute="user"
                   action="${pageContext.request.contextPath}/user/save">
        <form:hidden path="id"  style="display:none"/>

        <div class="form-group">
            <label for="email">E-mail</label>
            <form:input path="email" cssClass="form-control" id="email" placeholder="Email"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:input path="password" cssClass="form-control" id="password" placeholder="Password"/>
        </div>

            <div class="form-group">
                <label for="Role">Role</label>
                <form:select path="role" cssClass="form-control">
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="CUSTOMER">CUSTOMER</form:option>
                            <form:option value="EMPLOYEE">EMPLOYEE</form:option>
                    </form:select>
            </div>


        <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
        <br>
        <br>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/user/">Back to Users </a>
    </div>
</div>
</jsp:attribute>
</my:template>
