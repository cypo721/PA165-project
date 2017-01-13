<%--
  Created by IntelliJ IDEA.
  User: pato
  Date: 13.1.2017
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:template>
<jsp:attribute name="body">

<div class="container">
    <div class="jumbotron">
        <h1>Access Denied!</h1>
        <p>You don't have rights to access this page.</p>
    </div>
</div>
</jsp:attribute>
</my:template>
