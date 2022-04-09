<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/3/21
  Time: 14:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.6.0/dist/jquery.min.js" type="application/javascript"></script>
    <script src="./js/loginJs.js" type="application/javascript"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginServlet" method="get">
    账号： <input type="text" id="zh" name="zh" value="${cookie.zh.value}"/>
    <input type="checkbox" name="jzzh" id="jzzh" checked="checked"/>
    <label for="jzzh">记住账号</label>

    <span id="zhMsg"></span>
    <br/>
    口令： <input type="password" id="kl" name="kl" value="${cookie.kl.value}"/>
    <input type="checkbox" name="jzmm" id="jzmm" checked="checked"/>
    <label for="jzmm">记住密码</label>
    <span id="klMsg"></span>
    <br/>
    <input id="submit" type="submit" value="登录"/>
    <input id="logoff" type="reset" value="重置"/>
</form>
</body>
</html>
