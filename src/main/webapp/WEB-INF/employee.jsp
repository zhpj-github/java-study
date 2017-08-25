<%--
  Created by IntelliJ IDEA.
  User: zpj-pc
  Date: 2017-08-25
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>
    <link href="css/dashboard.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/add.employee" method="post">
        <%--编号：<input name="no"><br/>--%>
        <%--姓名：<input name="name"><br/>--%>
        <%--备注：<input name="note"><br/>--%>
        <%--<input type="submit" value="添加">--%>

        <div class="form-group">
            <%--@declare id="no"--%><label for="no" class="col-sm-2 control-label">编号</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="no"
                       placeholder="请输入编号" value="${no}">
            </div>
        </div>
        <div class="form-group">
            <%--@declare id="name"--%><label for="name" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name"
                       placeholder="请输入姓名" value="${name}">
            </div>
        </div>
        <div class="form-group">
            <%--@declare id="note"--%><label for="note" class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="note"
                       placeholder="请输入备注" ck="must" value="${note}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">保存</button>
                <a href="query.employee">返回</a>
            </div>
        </div>
    </form>

    <hr>
    <div class="table-responsive">
    <table  class="table table-striped">
        <thead>
            <tr>
                <td>ID</td>
                <td>编号</td>
                <td>姓名</td>
                <td>备注</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.no}</td>
                    <td>${employee.name}</td>
                    <td>${employee.note}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
