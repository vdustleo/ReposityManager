<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用仓库管理系统</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />

<!-- 此处理打印组件的要求，请不要移动位置 -->
<script language="javascript" src="js/print_plugin/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!-- 打印组件结束 -->
</head>

<body>
	<div class="wrapper">


		<%@ include file="jsp/common/header.jsp"%>

		<%@ include file="jsp/common/menu.jsp"%>

		<div id="navi" class="hide">
			<ul class="navi">
				<li>首页</li>
			</ul>
		</div>


		<div id="data_area"></div>
		<div id="data_area_sub"></div>

		<%@ include file="jsp/common/footer.jsp"%>

	</div>
	<script type="text/javascript" src="js/3rd_party/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="js/3rd_party/jquery.jqGrid.min.js"></script>
	<script type="text/javascript"
		src="js/3rd_party/jquery-ui-1.9.2.custom.min.js"></script>
	<script type="text/javascript" src="js/3rd_party/jquery.form.js"></script>
	<script type="text/javascript" src="js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="js/i18n/message_cn.js"></script>
    <script type="text/javascript" src="js/3rd_party/underscore.js"></script>
    <script type="text/javascript" src="js/3rd_party/underscore.string.min.js"></script>
    <script type="text/javascript" src="js/3rd_party/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/3rd_party/moment.min.js"></script>
    <script type="text/javascript" src="js/3rd_party/jquery.qrcode.min.js"></script> 
    <script type="text/javascript" src="js/common/common.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
<script type="text/javascript">
    $(function() {
        $('#data_area').load("jsp/common/home_page.jsp");
    });
</script>
</html>