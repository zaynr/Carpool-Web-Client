<%--
  Created by IntelliJ IDEA.
  User: zengzy19585
  Date: 2017/7/12
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="../../js/jQuery-MD5.js"></script>
        <script type="text/javascript" src="../../js/driver-login.js"></script>
        <title>司机登录</title>
    </head>
    <body>
        <div id="test">${test}</div>
        <br>手机号：<br>
        <input id="mobile_number" type="number"/>
        <br/>密码：<br>
        <input id="password" type="password"/>
        <br>
        <button id="submit">提交</button>
    </body>
</html>
