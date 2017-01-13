<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: xjavorka
  Date: 14.12.16
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Home">
<jsp:attribute name="body">

<div class="container">
    <div class="jumbotron">
        <h1>Hi!</h1>
        <p>Welcome to our project from PA165 course at FI MUNI.</p>
        <sec:authorize access="isAnonymous()">
        <p>If you want to see and manage all information, please <a href="${pageContext.request.contextPath}/login">log in</a>.</p>
        </sec:authorize>
    </div>
    <p><h2>Authors:</h2></p>
    <p>
    <ul>
    <li>Patrik Cyprian</li>
    <li>Eduard Cihunka</li>
    <li>Vaclav Zouzalik</li>
    <li>Marek Bohm</li>
    </ul>
    </p>
</div>
</jsp:attribute>
</my:template>

