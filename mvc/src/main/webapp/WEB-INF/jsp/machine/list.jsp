<%--
  Created by IntelliJ IDEA.
  User: pato
  Date: 15.12.2016
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:template title="Machines">
    <jsp:attribute name="body">
<html>
<head>
    <title>Test</title>
    <div>blbabla</div>

    <div class="row">,
        <c:forEach items="${machines}" var="m">
                        <tr><td>huha</td>
                           <td><c:out value="${m.id}"/></td>
                            <td><c:out value="${m.name}"/></td>
                            <td><c:out value="${m.pricePerDay}"/></td>
                        </tr>
        </c:forEach>
    </div>
</head>
<body>

</body>
</html>
    </jsp:attribute>
</my:template>