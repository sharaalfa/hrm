<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Менеджер отчетов</title>
</head>
<body bgcolor="#808080">
<div align="center">
    <h1>Центральность по правому собственному вектору</h1>
    <table border="1">
        <th>Номер подразделения</th>
        <th>Нименование подразделения</th>
        <th>Значение ценральности</th>
        <tr>
            <td>${list.get(0)}</td>
            <td>${list.get(1)}</td>
            <td>${list.get(2)}</td>

        </tr>
        <tr>
            <td>${list.get(3)}</td>
            <td>${list.get(4)}</td>
            <td>${list.get(5)}</td>

        </tr>
        <tr>
            <td>${list.get(6)}</td>
            <td>${list.get(7)}</td>
            <td>${list.get(8)}</td>

        </tr>
        <tr>
            <td>${list.get(9)}</td>
            <td>${list.get(10)}</td>
            <td>${list.get(11)}</td>

        </tr>
        <tr>
            <td>${list.get(12)}</td>
            <td>${list.get(13)}</td>
            <td>${list.get(14)}</td>

        </tr>
        <tr>
            <td>${list.get(15)}</td>
            <td>${list.get(16)}</td>
            <td>${list.get(17)}</td>

        </tr>
        <tr>
            <td>${list.get(18)}</td>
            <td>${list.get(19)}</td>
            <td>${list.get(20)}</td>

        </tr>

        <tr>
            <td>${list.get(21)}</td>
            <td>${list.get(22)}</td>
            <td>${list.get(23)}</td>

        </tr>
        <tr>
            <td>${list.get(24)}</td>
            <td>${list.get(25)}</td>
            <td>${list.get(26)}</td>

        </tr>
        <tr>
            <td>${list.get(27)}</td>
            <td>${list.get(28)}</td>
            <td>${list.get(29)}</td>

        </tr>



    </table>
</div>
<div id="container"></div>
<style>
    table {
        width: 300px; /* Ширина таблицы */
        border: 5px solid #5c4980; /* Рамка вокруг таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
        text-align: center;
    }
</style>

<div class="container">
    <div class="col-md-10">
        <h4>Центральность по правому собственному вектору</h4>
        <h5>Количество участия, центральность участия</h5>
        <p>Степень важности данного подразделения, которая пропорциональна степени важности подразделений,
            с которым он связан</p>
    </div>
</div>

<table class="table">
    <tr>

        <div>
            <td><a href="/tableInMax">Предыдущая таблица</a></td>
        </div>

</table>
<table class="table">
    <tr>

        <div>
            <td><a href="/chr">На главную</a></td>
        </div>

</table>
</body>
</html>

