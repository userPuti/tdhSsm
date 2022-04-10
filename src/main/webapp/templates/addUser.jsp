<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/3/28
  Time: 10:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加用户</title>
    <jsp:include page="../static/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_BTN"/>
    </jsp:include>
    <script src="../static/js/addUser.js" type="application/javascript"></script>
    <script src="../static/resources/js/jquery.serialize.js" type="application/javascript"></script>
</head>
<body>
<div class="tdh_form_title">添加用户信息</div>
<%--<div class="tdh_form_subtitle">副标题</div>--%>
<div class="tdh_form_caption"><i class="tdh_form_tag"></i>基本信息</div>
<form action="${pageContext.request.contextPath}/addUserServlet" method="post" id="fAddUser">
    <jsp:include page="commonTable.jsp"/>
    <div class="tdh_layout_b" style="height: 40px; text-align: center">
        <input class="tdh_btn tdh_btn_blue" id="iSubmit" type="button"  value="保存"/>
        <input class="tdh_btn tdh_btn_blue" id="back" type="button" value="返回"/>
    </div>
</form>
</body>
<script type="application/javascript">
    let _path = "${pageContext.request.contextPath}";
</script>
</html>

