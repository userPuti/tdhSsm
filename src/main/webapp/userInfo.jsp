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
    <title>用户信息展示</title>
    <jsp:include page="resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_LAYER_BTN"/>
    </jsp:include>
    <script src="js/userInfo.js" type="application/javascript"></script>
</head>
<body>
<div class="tdh_form_title">用户信息</div>
<%--<div class="tdh_form_subtitle">副标题</div>--%>
<div class="tdh_form_caption"><i class="tdh_form_tag"></i>基本信息</div>
<form action="${pageContext.request.contextPath}/addUserServlet" method="post">
    <table class="tdh_form">
        <colgroup>
            <col width="14%"/>
            <col width="24%"/>
            <col width="14%"/>
            <col width="24%"/>
        </colgroup>
        <tr>
            <td class="tdTitle">用户账号</td>
            <td class="tdCont">
                <input id="iYhzh" class="inputText" type="text" readonly="readonly"/>
            </td>
            <td class="tdTitle">用户姓名</td>
            <td class="tdCont">
                <input id="iYhxm" class="inputText" type="text" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">用户口令</td>
            <td class="tdCont">
                <input id="iYhkl" class="inputText" type="text" readonly="readonly"/>
            </td>
            <td class="tdTitle">用户部门</td>
            <td class="tdCont">
                <input id="iYhbm" class="inputText" type="text" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">排序号</td>
            <td class="tdCont">
                <input id="iPxh" class="inputText" type="text" readonly="readonly"/>
            </td>

            <td class="tdTitle">出生日期</td>
            <td class="tdCont">
                <input id="iCsrq" class="inputText" type="text" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">用户性别</td>
            <td class="tdCont">
                <input id="iYhxb" class="inputText" type="text" readonly="readonly"/>
            </td>
            <td class="tdTitle">是否禁用</td>
            <td class="tdCont">
                <input id="iSfjy" class="inputText" type="text" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="tdh_layout_b" style="height: 40px; text-align: center">
                    <input class="tdh_btn tdh_btn_blue" id="back" type="button" value="返回"/>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
