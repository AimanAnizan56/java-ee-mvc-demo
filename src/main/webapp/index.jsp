<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <form id="form" action="./user/login" method="post">
            <input type="text" name="username" placeholder="Username">
            <input type="password" name="password" placeholder="Password">
            <button type="submit">Login</button>
            <a href="./sign-up.jsp">Sign new account</a>
        </form>
        <%
            if (request.getAttribute("message") != null) {%>
                <div style="background: green; color: white;">
                    ${requestScope.message}
                </div>
            <%}
        %>
    </body>
</html>