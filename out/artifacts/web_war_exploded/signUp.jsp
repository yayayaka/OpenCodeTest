<%--
  Created by IntelliJ IDEA.
  model.User: yayayaka
  Date: 30-Jun-19
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать аккаунт</title>
    <link rel="stylesheet" href="/css/use.css" type="text/css">
</head>
<body>
<table align="center">
    <tr>
        <th>
            <h2>Создать аккаунт</h2>
        </th>
    </tr>
    <tr>
        <td class="td">
            <form method="post" action="/signup">
                <input type="text" id="login" name="login" placeholder="Имя" class="val" required pattern="^[a-zA-Z0-9]+$">
                <input type="password" id="password" name="password" placeholder="Пароль" class="val" required pattern="^[a-zA-Z0-9]+$">
                <input type="submit" value="Создать">
            </form>
            <div>Есть аккаунт? <a href="login.jsp">Войти</a></div>
        </td>
    </tr>
    <tr>
        <td class="td-bottom">
            &nbsp;
        </td>
    </tr>
</table>
</body>
</html>
