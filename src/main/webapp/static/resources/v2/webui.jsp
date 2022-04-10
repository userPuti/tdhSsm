<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String uilist = "_" + request.getParameter("UIS") +"_";
 %>
<script type="text/javascript" >
  var CONTEXT_PATH  = '<%=CONTEXT_PATH%>';
</script>
<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/reset.css" />
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/lib/jquery.min.js" ></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/commonjs/tdh.nicescroll.js" ></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/commonjs/tdh.common.js" ></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxAjax/codebase/dhtmlxcommon.js" ></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/layui/lay/modules/layer.js" ></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/js/common.js" ></script>
<%if(uilist.indexOf("_LAYOUT_")>=0){%>
<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/layout/css/layout.css" />
<%}%>
<%if(uilist.indexOf("_BTN_")>=0){ %>
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/btn/css/btn.css" />
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/btn/js/tdh.btn.js" ></script>
<%}%>
<%if(uilist.indexOf("_FORM_")>=0){ %>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/DatePicker/WdatePicker.js" ></script>
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/form/css/form.css" />
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/form/js/tdh.form.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/form/js/tdh.form.placeholder.js" ></script>
<%}%>
<%if(uilist.indexOf("_PAGE_")>=0){ %>
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/paging/css/paging.css" />
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/tdh/paging/js/tdh.paging.js" ></script>
<%}%>
<%if(uilist.indexOf("_ZTREE_")>=0){%>
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/ztree/css/metroStyle/metroStyle.css" />
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/ztree/css/metroStyle/diy.css" />
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/ztree/js/jquery.ztree.core.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/ztree/js/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/ztree/js/jquery.ztree.excheck.js"></script>
<%}%>
<%if(uilist.indexOf("_DGRID_")>=0){ %>
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.css" />
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_pgn_bricks.css" />
	<link rel="stylesheet" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css" />
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgridcell.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_json.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_pgn.js" ></script>
	<script type="text/javascript" src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxDataView/codebase/dhtmlxdataprocessor.js"></script>
<%}%>
<%if(uilist.indexOf("_DTAB_")>=0){%>
	<link rel="STYLESHEET" type="text/css" href="<%=CONTEXT_PATH %>/static/resources/v2/static/plugg/dhtmlx/dhtmlxTabbar/codebase/dhtmlxtabbar.css" />
	<script  src="<%=CONTEXT_PATH %>/static/resources/v2/static/plugg/dhtmlx/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
<%}%>
<%if(uilist.indexOf("_DTREE_")>=0){ %>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxTree/codebase/dhtmlxtree.css" />
	<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<%}%>
<%if(uilist.indexOf("_DLAYOUT_")>=0){%>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxLayout/codebase/dhtmlxlayout.css" />
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css" />
<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
<%}%>
<%if(uilist.indexOf("_DCOMBO_")>=0){%>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxCombo/codebase/dhtmlxcombo.css" />
<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxCombo/codebase/dhtmlxcombo.js"></script>
<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxCombo/codebase/ext/dhtmlxcombo_whp.js"></script>
<%}%>
<%if(uilist.indexOf("_DACCORDION_")>=0){%>
<script src="<%=CONTEXT_PATH%>/resources/v2/static/plugg/dhtmlx/dhtmlxAccordion/codebase/dhtmlxcommon.js"></script>
<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxAccordion/codebase/dhtmlxcontainer.js"></script>
<script src="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
<link rel="stylesheet"  href="<%=CONTEXT_PATH%>/static/resources/v2/static/plugg/dhtmlx/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_blue.css">
<%}%>