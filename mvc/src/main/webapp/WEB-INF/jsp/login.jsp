<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        
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
    </body>
</html>