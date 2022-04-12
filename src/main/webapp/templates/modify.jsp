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
    <title>修改</title>
    <jsp:include page="/static/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_PAGE_LAYOUT"/>
    </jsp:include>
    <script src="${pageContext.request.contextPath}/static/js/modify.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/resources/js/jquery.serialize.js" type="application/javascript"></script>
</head>
<body>
<div class="tdh_form_title">修改用户信息</div>
<div class="tdh_form_caption"><i class="tdh_form_tag"></i>基本信息</div>
<form action="${pageContext.request.contextPath}/update" method="post" id="modifyForm">
    <table class="tdh_form">
        <colgroup>
            <col width="14%"/>
            <col width="24%"/>
            <col width="14%"/>
            <col width="24%"/>
        </colgroup>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户账号</td>
            <td class="tdCont"><input id="iYhzh" name="yhid" class="inputText" type="text" value="${user.yhid}"
                                      readonly="readonly"/></td>
            <td class="tdTitle"><i class="required">*</i>用户姓名</td>
            <td class="tdCont"><input id="iYhxm" name="yhxm" class="inputText" type="text" value="${user.yhxm}"
                                      maxlength="40"/></td>
        </tr>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户口令</td>
            <td class="tdCont"><input id="iYhkl" name="yhkl" class="inputText" type="text" value="${user.yhkl}"
                                      maxlength="20"/></td>
            <td class="tdTitle" id="tCfkl"><i class="required">*</i>重复口令</td>
            <td class="tdCont"><input id="iCfkl" name="cfkl" class="inputText" type="text" value="${user.yhkl}"
                                      maxlength="20"/></td>
        </tr>
        <tr>
            <td class="tdTitle">排序号</td>
            <td class="tdCont"><input id="iPxh" name="pxh" class="inputText" type="text" value="${user.pxh}"/></td>
            <td class="tdTitle">出生日期</td>
            <td class="tdCont">
                <input id="iCsrq" name="csrq" class="Wdate inputText" type="text" value="${user.csrq}"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'} );"
                       readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">用户部门</td>
            <td class="tdCont">
                <input type="hidden" id="iYhbm" value="${user.yhbm}"/>
                <select class="inputSel" id="sYhbm" name="yhbm">
                    <option value="" selected></option>
                </select>
            </td>
            <td class="tdTitle">用户性别</td>
            <td class="tdCont">
                <input type="hidden" id="iYhxb" value="${user.yhxb}"/>
                <select class="inputSel" id="sYhxb" name="yhxb">
                    <option value="" selected></option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">是否禁用</td>
            <td class="tdCont" colspan="3">
                <input type="hidden" id="iSfjy" value="${user.sfjy}"/>
                <input name="sfjy" class="inputSwitch" type="checkbox" data-text="失效/启用"/>
            </td>
        </tr>
    </table>
    <div class="tdh_layout_b" style="height: 40px; text-align: center">
        <input class="tdh_btn tdh_btn_blue" id="iSubmit" type="button"  value="保存"/>
        <input class="tdh_btn tdh_btn_blue" id="back" type="button" onclick="layerClose(true)" value="返回"/>
    </div>
</form>
</body>
</html>
