<%--
  Created by IntelliJ IDEA.
  User: zpj-pc
  Date: 2017-08-19
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css" />
    <title>登录</title>
    <script type="text/javascript" src="js/jquery-3.1.1.js"></script>
    <script type="text/javascript">
         /**
         * @return {boolean}
         */
        function CheckIn() {
            var names = document.getElementById("txtU_Name").value;
            var pass = document.getElementById("txtU_Pass").value;
            var check = document.getElementById("txtU_Check").value;
            if (names === "") {
                alert("请输入用户名...");
                document.getElementById("txtU_Name").focus();
                return false;
            }
            else if (pass === "") {
                alert("请输入用户密码...");
                document.getElementById("txtU_Pass").focus();
                return false;
            }
            else if (check === "") {
                alert("请输入验证码...");
                document.getElementById("txtU_Check").focus();
                return false;
            }
        }
        function reload(){
            document.getElementById("image").src="<%=request.getContextPath() %>/image.do?name="+new Date().getTime();
            $("#txtU_Check").val("");   // 将验证码清空
        }
    </script>
  </head>
  <body class="loginbody">
  <form id="form1" action="login.do" method="post">
    <div class="login1"></div>
    <div class="login2"></div>
    <div class="login3">
      <table cellpadding="0" cellspacing="1" border="0" style="margin-left:320px; width:300px;">
        <tr>
          <td>用户名称：</td>
          <td><input id="txtU_Name" name="userName" type="text" class="loginTXT"   maxlength="20"
                     value='${param.userName}'/></td>
        </tr>
        <tr>
          <td>用户密码：</td>
          <td><input id="txtU_Pass" name="password" type="password" class="loginTXT" maxlength="20"
                     value='${param.password}'/></td>
        </tr>
        <tr>
          <td>验证码：</td>
          <td><input id="txtU_Check"  name="check" type="text" class="loginTXT" style="width:85px;" maxlength="5"/>&nbsp;
            <img id="image" src="image.do" alt="验证码" style="vertical-align:middle;" onclick="reload();"/></td>
        </tr>
        <tr>
          <td colspan="2">
            <span style="color: red; ">${requestScope["msg"]}</span>
            <hr /></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="submit" id ="BtnLogin"  value="登录管理"
                   onclick="return CheckIn()"
                   style="height: 26px"/></td>
        </tr>
      </table>
    </div>
    <div class="login4"></div>
  </form>
  </body>
</html>
