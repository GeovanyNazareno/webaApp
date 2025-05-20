<%--
  Created by IntelliJ IDEA.
  User: Geovanny Nazareno
  Date: 15/5/2025
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Login de usuario</h1>
<div>
    <form action="/web_app_cookies/login" method="post">
        <div>
            <lable for="username">Nombre de usuarios:</lable>
            <div>
                <input type="text" name="username" id="username"></input>
            </div>
        </div>

        <div>
            <label for="pass">Password:</label>
            <div>
                <input type="password" name="password" id="pass"></input>
            </div>
        </div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </form>
</div>

</body>
</html>
