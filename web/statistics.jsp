<%@ page import="dataaccess.UserDAO" %>
<%@ page import="model.User" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yayayaka
  Date: 05-Jul-19
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика</title>
    <link rel="stylesheet" href="/css/use.css" type="text/css">
</head>
<%
    UserDAO dao = new UserDAO();
    TreeMap<Integer, String> userTryStat = dao.getStats();
%>
<body>
<table align="center">
    <tr>
        <td class="a"><a href="/game.jsp">Вернуться к игре</a></td>
        <td class="a"><a href="/logout">Разлогиниться</a></td>
    </tr>
</table>
<table align="center">
    <tr>
        <th>
            <div><h2>Статистика среднего числа попыток до угадывания числа:</h2></div>
        </th>
    </tr>
    <tr>
        <td class="td">
            <%
                for (Map.Entry<Integer, String> entry : userTryStat.entrySet()) {
            %><div><%=entry.getValue()%> -- <%=entry.getKey()%></div><%
            }
        %>
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
