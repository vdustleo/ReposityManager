// mixin underscore.string
_.mixin(_.str.exports());
//定义
var LODOP; 
window.CommonUtils = (function($, module) {

    /**
     * 初始化模块
     */
    function init() {
        loadMenu();
        
        // 初始化全局的validation
        $.validator.setDefaults({           
            errorPlacement: function(error, element) {
                if ( element.is(":radio") )
                    error.appendTo( element.parent().next().next() );
                else if ( element.is(":checkbox") )
                    error.appendTo ( element.next() );
                else
                    error.appendTo( element.parent().next() );
            }         
        });
        
    }

    /**
     * 加载主菜单
     */
    function loadMenu() {
        // 首页
        _loadPage("home_page", "jsp/common/home_page.jsp");
        
        // 入库
        _loadPage("storage_in", "jsp/storage/storage_in.jsp");
        
        // 出库
        _loadPage("storage_out", "jsp/storage/storage_out.jsp");
        
        // 付款
        _loadPage("pay_oper", "jsp/pay/pay_oper.jsp");
        
        
        // 查询
        _loadPage("storage_in_record", "jsp/storage/storage_in_record.jsp");
        _loadPage("storage_out_record", "jsp/storage/storage_out_record.jsp");
        _loadPage("storage_query", "jsp/storage/storage_query.jsp");
        _loadPage("pay_record", "jsp/pay/pay_record.jsp");
        _loadPage("pay_statistic", "jsp/pay/pay_statistic.jsp");
        
        // 系统设置
        _loadPage("repo_manage", "jsp/reposity/reposity_list.jsp");
        
        // TODO 加载此处页面需要进行控制，根据license限制进行处理
        _loadPage("repo_add", "jsp/reposity/reposity_add.jsp");
        _loadPage("base_info", "jsp/sys/base_info.jsp");
        
        // 果农登记
        _loadPage("farmer_add", "jsp/farmer/farmer_add.jsp");
        _loadPage("farmer_list", "jsp/farmer/farmer_list.jsp");
        
        // app设置
        _loadPage("app_config", "jsp/sys/app_config.jsp");
        
    }

    /**
     * 加载页面
     */
    function _loadPage(id, page) {
        $('#' + id ).click(function() {
            $('#data_area_sub').empty().hide();
            $('#data_area').load(page).show();
        });
    }
    
    /**
     * 加载指定页面
     */
    function loadPageByName(page) {
        $('#data_area').empty().load(page).show();
    }

    /**
     * 记录日志
     */
    function logError(info) {
        if (window.console) {
            console.log(info);
        }
    }
    
    /**
     * 通知信息
     */
    function notify(msg) {
        alert(msg);
    }

    /**
     * 初始化select框
     */
    function initSelect(id, action, defval) {
        // 支持设置初值
        if (defval === undefined) {
            defval = "";
        }

        $.ajax({
            type : "post",
            url : action
        }).done(function(data) {
            var selector = $("#"+id);
            selector.empty().append("<option value=''>--请选择--</option>");
            
            if(!data.infos){
                return;
            }
            
            for (var i = 0; i < data.infos.length; i++) {
                selector.append('<option value="' + data.infos[i].name + '">' + data.infos[i].value + '</option>');
            }

            if (defval !== "") {
                selector.val(defval);
            }
        }).fail(function() {

        });

    }
    
    /**
     * 初始化select框, 根据数值，生成对应的编号值
     */
    function initSelectByCount(id, maxCount) {

        var selector = $("#"+id);
        selector.empty().append("<option value=''>--请选择--</option>");
        
        for (var i = 1; i <= maxCount; i++) {
            selector.append('<option value="' + i + '">' + i + '</option>');
        }

        // 设置初值
        selector.val('');
    }    
    
    /**
     * 取得系统的仓库名称
     */
    function queryRepoName() {
        var repoName = "";
        $.ajax({
            type : "post",
            url : "reposity/queryRepoName.action",
            async: false
        }).done(function(data) {
            repoName = data.info.repoCode;
        });
        return repoName;
    }    
    
    
    function formatCurrency(currency) {
        return _.sprintf("%.2f", parseFloat(currency));
    }

    module.init = init;
    module.logError = logError;
    module.initSelect = initSelect;
    module.initSelectByCount = initSelectByCount; 
    module.loadPageByName = loadPageByName;
    module.notify = notify;
    module.formatCurrency = formatCurrency;
    module.queryRepoName = queryRepoName;
    return module;

}($, window.CommonUtils || {}));

$(function() {
    CommonUtils.init();
    LODOP = getLodop(document.getElementById('LODOP_OB'), document
            .getElementById('LODOP_EM'));    
});
