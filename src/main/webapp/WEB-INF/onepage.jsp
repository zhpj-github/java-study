<%--
  Created by IntelliJ IDEA.
  User: zpj-pc
  Date: 2017-08-19
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>标题</title>
</head>
<body>
转发<br>
<%=pageContext.getServletContext().getAttribute("global")%>
<br>
${pageContext.getAttribute("global")}
</body>
</html>
