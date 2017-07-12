<%--
  Created by IntelliJ IDEA.
  User: zengzy19585
  Date: 2017/7/12
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="../../js/jQuery-MD5.js"></script>
        <script type="text/javascript" src="../../js/driver-register.js"></script>
        <title>司机注册</title>
    </head>
    <body>
        <div id="test">${test}</div>
        <br>姓名：<br>
        <input id="name" type="text"/>
        <br/>手机号：<br>
        <input id="mobile_number" type="number"/>
        <br>车牌号：(示例：闽E12345)<br>
        <input id="car_plate" type="text"/>
        <br/>密码：<br>
        <input id="password" type="password"/>
        <br>
        <button id="submit">提交</button>
    </body>
</html>
