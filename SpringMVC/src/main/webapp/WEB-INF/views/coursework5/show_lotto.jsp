<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wei
  Date: 2022/2/13
  Time: 下午 08:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lotto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome6/css/all.min.css"/>
    <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css"
          integrity="sha384-Uu6IeWbM+gzNVXJcM9XV3SohHtmWE+3VGi496jvgX1jyvDTXfdK+rfZc8C1Aehk5" crossorigin="anonymous">
</head>
<body style="padding: 15px; text-align: -webkit-center;">
<form action="./add" class="pure-form">
    <fieldset>
        <button type="submit" class="pure-button pure-button-primary">電腦選號</button>
    </fieldset>
</form>

<%-- HW: 統計每個號碼出現的次數，並依次數由大到小排列 --%>
<%-- E.g., 9:(19), 5:(6), 13(1)... --%>
<h1>號碼重複出現次數</h1>
<div style="width: 80%;overflow: auto">
    <table class="pure-table">
        <thead>
        <c:forEach items="${lottoShowTimes}" var="item">
            <th>${item.key}</th>
        </c:forEach>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${lottoShowTimes}" var="item">
                <td>${item.value}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>

<h1>最新樂透</h1>
<div style="width: fit-content;height: 60%;overflow: auto">
    <table class="pure-table pure-table-horizontal">
        <thead>
        <tr>
            <th>Index</th>
            <th>Lotto</th>
            <th>重抽</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lottoList}" var="lotto" varStatus="status">
            <tr>
                <td>${status.index}</td>
                <td>${lotto}</td>
                <td>
                    <button type="submit" class="pure-button pure-button-primary"
                            onclick="window.location.href='./update/${status.index}'">
                        <i class="fa-solid fa-arrow-rotate-right"></i>
                    </button>
                </td>
                <td>
                    <button type="submit" class="pure-button" style="background: rgb(202, 60, 60)"
                            onclick="window.location.href='./delete/${status.index}'">
                        <i class="fa-solid fa-trash-can"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
