<%--
  Created by IntelliJ IDEA.
  User: Wei
  Date: 2022/2/22
  Time: 下午 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/include/exam_header.jspf" %>
<body>
<table class="main_table">
    <tr>
        <td valign="top">
            <%@include file="exam_form.jspf" %>
        </td>
        <td valign="top">
            <%@include file="exam_list.jspf" %>
        </td>
    </tr>
</table>
</body>
</html>
