<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yayayaka
  Date: 01-Jul-19
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Игра</title>
    <link rel="stylesheet" href="/css/use.css" type="text/css">
</head>
<%
    ArrayList moves = (ArrayList)session.getAttribute("moves");
    ArrayList result = (ArrayList)session.getAttribute("results");
    boolean isGuessed = (boolean)session.getAttribute("isGuessed");
%>
<script>
    function addSymbol(number) {
        var move = document.getElementById("move");
        move.value += number;
    }

    function delSymbol(){
        var move = document.getElementById("move");
        move.value = move.value.substr(0, move.value.length - 1);
    }

    function matchDigits() {
        if (!document.getElementById("move").value.match("^[0-9]+$")) {
            alert("В данном поле могут быть только цифры");
        }
    }
</script>
<body>
<table align="center">
    <tr>
        <td class="a"><a href="/game">Новая игра</a></td>
        <td class="a"><a href="/statistics.jsp">Просмотр статистики</a></td>
        <td class="a"><a href="/logout">Разлогиниться</a></td>
    </tr>
</table>
<table align="center">
    <tr>
        <th>
            <h2>Игра "Бык-корова"</h2>
        </th>
    </tr>
    <tr>
        <td class="td">
    <%
        for (int movesPointer = 0; movesPointer < moves.size(); movesPointer++) {
            %><div><%=moves.get(movesPointer)%> -- <%=result.get(movesPointer)%></div><%
        }
        if (isGuessed) {
            int hiddenNumber = (int) session.getAttribute("hiddenNumber");
            %>
    <div><h4>Поздравляю! Вы угадали число! Это число <%=hiddenNumber%></h4></div>
    <%
        } else {
           %>
        </td>
    </tr>
            <tr>
                <td class="td">
        <div>Ваш ход</div>
    <div><form action="/game" method="post">
        <input type="text" id="move" name="move" placeholder="Ваш ход" required pattern="^[0-9]+$" class="val">
        <input type="submit" value="Ходить">
    </form></div>
                </td>
</tr>

</table>
<table align="center">
    <tr>
        <td class="td"><input class="calc" type="submit" id="1" value="1" onclick="addSymbol(1)"></td>
        <td class="td"><input class="calc" type="submit" id="2" value="2" onclick="addSymbol(2)"></td>
        <td class="td"><input class="calc" type="submit" id="3" value="3" onclick="addSymbol(3)"></td>
    </tr>
    <tr>
        <td class="td"><input class="calc" type="submit" id="4" value="4" onclick="addSymbol(4)"></td>
        <td class="td"><input class="calc" type="submit" id="5" value="5" onclick="addSymbol(5)"></td>
        <td class="td"><input class="calc" type="submit" id="6" value="6" onclick="addSymbol(6)"></td>
    </tr>
    <tr>
        <td class="td"><input class="calc" type="submit" id="7" value="7" onclick="addSymbol(7)"></td>
        <td class="td"><input class="calc" type="submit" id="8" value="8" onclick="addSymbol(8)"></td>
        <td class="td"><input class="calc" type="submit" id="9" value="9" onclick="addSymbol(9)"></td>
    </tr>
    <tr>
        <td class="td" colspan="2" align="center" rowspan="0"><input class="calc" type="submit" id="0" value="0" onclick="addSymbol(0)"></td>
        <td class="td"><input class="calc" type="submit" id="del" value="del" onclick="delSymbol()"></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td class="td-bottom" colspan="3">
            &nbsp;
        </td>
    </tr>
</table>
</body>
</html>
