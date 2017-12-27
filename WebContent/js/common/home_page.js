window.HomePage = (function($, module) {
    /**
     * 加载页面
     */
    function _loadPage(id, page) {
        $('#b_' + id).click(function() {
            $('#data_area_sub').empty().hide();
            $('#data_area').load(page).show();
        });
    }

    function init() {

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

        // 果农登记
        _loadPage("farmer_add", "jsp/farmer/farmer_add.jsp");
        _loadPage("farmer_list", "jsp/farmer/farmer_list.jsp");
    }

    module.init = init;

    return module;

}($, window.HomePage || {}));
$(function() {
    HomePage.init();
});
