<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="./user/signup" method="post">
            <input type="text" name="username" placeholder="Username">
            <input type="email" name="email" placeholder="Email">
            <input type="password" name="password" placeholder="Password">

            <button type="submit">Create Account</button>
            <a href="./">Had account? Login here</a>
        </form>
    </body>
</html>
