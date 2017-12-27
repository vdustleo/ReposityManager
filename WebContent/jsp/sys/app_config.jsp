<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="" class="func_title">App下载</div>
<p class="app-config">
    App下载路径:<a id="download_link" href=''> http://<span id="ip_placeholder_link"></span>:<%=request.getServerPort()%><s:url value="/app/ReposityManager.apk" /></a>
</p>
<p class="app-config">或者您可以扫描以下二维码自动下载:</p>
<div id="download_code" class="app-qr-code"></div>
<div id="" class="func_title">App连接Server</div>
<p class="app-config">
    Server路径: <span id="app_host">http://<span id="ip_placeholder"></span>:<%=request.getServerPort()%><s:url value="/" /></span>
</p>
<p class="app-config">或者您可以打开App软件，在设置连接服务器扫描以下二维码自动连接：</p>
<div id="connect_server_code" class="app-qr-code"></div>
<script type="text/javascript" src="js/sys/app_config.js"></script>
