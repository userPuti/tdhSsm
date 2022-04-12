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
    <script src="${pageContext.request.contextPath}/static/resources/v2/static/lib/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/login.js" type="text/javascript"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
    <label for="zh">账号：</label>
    <input type="text" id="zh" name="zh" value="${cookie.zh.value}"/>
    <input type="checkbox" name="jzzh" id="jzzh" checked="checked" />
    <label for="jzzh">记住账号</label>
    <span id="zhMsg"></span>
    <br/>
    <label for="kl"> 口令：</label>
    <input type="password" id="kl" name="kl" value="${cookie.kl.value}"/>
    <input type="checkbox" name="jzmm" id="jzmm" checked="checked"/>
    <label for="jzmm">记住密码</label>
    <span id="klMsg"></span>
    <br/>
    <input id="submit" type="submit" value="登录"/>
    <input id="logoff" type="reset" value="重置"/>
</form>
</body>
</html>
