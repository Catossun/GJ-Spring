<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello Coursework</title>
</head>
<body style="padding: 20px;text-align: center">
<h1>
    Coursework List
</h1>
<br/>
<table style="width: 25%;text-align: center;margin:auto" border="1">
    <thead>
    <th>No.</th>
    <th>Demo site</th>
    </thead>
    <tbody>
    <tr>
        <td>
            5
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/mvc/coursework5/lotto/">Lotto</a>
        </td>
    </tr>
    <tr>
        <td>
            6
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/mvc/coursework6/exam/">Exam Form</a>
        </td>
    </tr>
    <tr>
        <td>
            7
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/mvc/coursework7/fundstock/">Fund Stock</a>
            <a href="${pageContext.request.contextPath}/html/coursework7/fund.html">Fund</a>
        </td>
    </tr>
    </tbody>
</table>
<br/>
Current Time: <%=new Date()%>
</body>
</html>