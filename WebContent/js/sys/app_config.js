/**
 * app设置
 */
window.AppConfig = (function($, module) {

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>系统设置</li> -><li>app设置</li>");

        getServerIp();
    }
    
    function getServerIp() {
        $.ajax({
            type : "post",
            url : "common/serverIp.action",
        }).done(function(data) {
            $('#ip_placeholder_link').text(data.ipAddr);
            $('#ip_placeholder').text(data.ipAddr);
            _$a = $('#download_link');
            var downlink = $.trim(_$a.text());
            var host = $.trim($('#app_host').text());
            _$a.attr('href', $.trim(_$a.text()));
            generateQrCode(downlink, host);
        });
        
    }
    /**
     * 生成二维码
     */
    function generateQrCode(downlink, host) {
        // 生成下载app的二维码
        $("#download_code").qrcode({
            render : "table", // table方式
            width : 300, // 宽度
            height : 300, // 高度
            text : downlink // 下载地址
        });

        // 生成连接服务器的二维码
        $("#connect_server_code").qrcode({
            render : "table", // table方式
            width : 300, // 宽度
            height : 300, // 高度
            text : host // 链接地址
        });       
        
    }

    module.init = init;

    return module;
}($, window.AppConfig || {}));

$(function() {
    AppConfig.init();
});