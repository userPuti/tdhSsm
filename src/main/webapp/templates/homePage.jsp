<%--
  Created by IntelliJ IDEA.
  User: puti
  Date: 2022/3/28
  Time: 10:21
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户信息</title>
    <jsp:include page="/static/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_PAGE_LAYOUT"/>
    </jsp:include>
    <script src="${pageContext.request.contextPath}/static/js/homePage.js" type="text/javascript"></script>
</head>
<body>
<div class="tdh_layout" style="padding-top: 60px;">
    <!--搜索条件-->
    <div class="tdh_layout_t" style="height: 65px;">
        <div class="tdh_form_search">
            <table>
                <colgroup>
                    <col width="100">
                    <col width="150">
                    <col width="100">
                    <col width="150">
                    <col width="*">
                </colgroup>
                <tr>
                    <td class="tdTitle">用户账号:</td>
                    <td class="tdCont">
                        <input id="yhzh" name="yhzh" class="inputText" type="text" placeholder="请输入"/>
                    </td>
                    <td class="tdTitle">用户部门:</td>
                    <td class="tdCont">
                        <select id="yhbm" name="yhbm" class="inputSel">
                            <option value="" selected></option>
                        </select>
                    </td>
                    <td class="tdTitle">
                        <a class="tdh_btn tdh_btn_blue" href="javascript:void(0);" id="query">
                            <i class="tdh_icon icon_search"></i>查询
                        </a>
                    </td>
                    <td align="right">
                        <a class="tdh_btn tdh_btn_blue" href="javascript:void(0);" id="queryAll">
                            <i class="tdh_icon icon_search"></i>全部用户
                        </a>
                        <a class="tdh_btn tdh_btn_blue" id="addUser">
                            <i class="tdh_icon icon_add"></i>新增
                        </a>
                        <a class="tdh_btn tdh_btn_blue" href="javascript:void(0);" id="delUsers"
                           onclick="bulkDeletion()">
                            <i class="tdh_icon icon_del"></i>批量删除
                        </a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!--列表查询-->
    <div class="tdh_layout_c">
        <div id="grid" style="width: 100%;height: 100%"></div>
    </div>
</div>
<script type="text/javascript">
    let _path = "${pageContext.request.contextPath}";
</script>
</body>
</html>
