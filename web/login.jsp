<%--
  Created by IntelliJ IDEA.
  model.User: yayayaka
  Date: 29-Jun-19
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Залогиниться</title>
    <link rel="stylesheet" href="/css/use.css" type="text/css">
</head>

<body>
<table align="center">
    <tr>
        <th>
            <h2>Залогиниться</h2>
        </th>
    </tr>
    <tr>
        <td class="td">
            <form method="post" action="/">
                <input type="text" id="login" name="login" placeholder="Логин" class="val" required pattern="^[a-zA-Z0-9]+$">
                <input type="password" id="password" name="password" placeholder="Пароль" class="val" required pattern="^[a-zA-Z0-9]+$">
                <input type="submit" value="Войти">
            </form>
            <div>
                Нет аккаунта? <a href="signUp.jsp">Создать новый</a>
            </div>
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
