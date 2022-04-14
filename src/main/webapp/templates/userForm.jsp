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
    <title>用户信息</title>
    <jsp:include page="/static/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_LAYER_BTN_LAYOUT"/>
    </jsp:include>
    <script src="${pageContext.request.contextPath}/templates/js/userForm.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/resources/js/jquery.serialize.js" type="text/javascript"></script>
</head>
<body>
<div class="tdh_form_title">用户信息</div>
<div class="tdh_form_caption"><i class="tdh_form_tag"></i>基本信息</div>
<form action="" method="post" id="userForm">
    <table class="tdh_form">
        <colgroup>
            <col width="14%"/>
            <col width="24%"/>
            <col width="14%"/>
            <col width="24%"/>
        </colgroup>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户账号</td>
            <td class="tdCont"><input id="iYhid" name="yhid" class="inputText" type="text" placeholder="请输入" value="${user.yhid}"
                                      maxlength="14"/></td>
            <td class="tdTitle"><i class="required">*</i>用户姓名</td>
            <td class="tdCont"><input id="iYhxm" name="yhxm" class="inputText" type="text" placeholder="请输入" value="${user.yhxm}"
                                      maxlength="40"/></td>
        </tr>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户口令</td>
            <td class="tdCont"><input id="iYhkl" name="yhkl" class="inputText" type="text" placeholder="请输入" value="${user.yhkl}"
                                      maxlength="20"/></td>
            <td class="tdTitle" id="tCfkl"><i class="required">*</i>重复口令</td>
            <td class="tdCont"><input id="iCfkl" name="cfkl" class="inputText" type="text" placeholder="请输入" value="${user.yhkl}"
                                      maxlength="20"/></td>
        </tr>
        <tr>
            <td class="tdTitle">排序号</td>
            <td class="tdCont"><input id="iPxh" name="pxh" class="inputText" type="text" placeholder="请输入" value="${user.pxh}"/></td>
            <td class="tdTitle">出生日期</td>
            <td class="tdCont">
                <input id="iCsrq" name="csrq" class="Wdate inputText" type="text"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'} );"
                       readonly="readonly" value="${user.csrq}"/>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">用户部门</td>
            <td class="tdCont">
                <input type="hidden" id="iYhbm" value="${user.yhbm}"/>
                <input type="hidden" id="iDeparts" value="${departs}"/>
                <select class="inputSel" id="sYhbm" name="yhbm">
                    <option value="" selected></option>
                </select>
            </td>
            <td class="tdTitle">用户性别</td>
            <td class="tdCont">
                <input type="hidden" id="iYhxb" value="${user.yhxb}"/>
                <input type="hidden" id="iGender" value="${gender}"/>
                <select class="inputSel" id="sYhxb" name="yhxb">
                    <option value="" selected></option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">是否禁用</td>
            <td class="tdCont" colspan="3">
                <input id="sfjy" value="${user.sfjy}" type="hidden"/>
                <input id="iSfjy" name="sfjy" class="inputSwitch" type="checkbox" data-text="失效/启用" />
            </td>
        </tr>
    </table>
    <div class="tdh_layout_b" style="height: 40px; text-align: center">
        <input class="tdh_btn tdh_btn_blue" id="iSubmit" type="button" value="保存"/>
        <input class="tdh_btn tdh_btn_blue" id="iBack" type="button" onclick="layerClose(true)" value="返回"/>
    </div>
    <input type="hidden" id="iFunc" value="${func}"/>
</form>
</body>
</html>

