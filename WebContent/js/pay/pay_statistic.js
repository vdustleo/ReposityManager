window.PayStatistic = (function($, module) {

    var _$grid = $("#pay_data");
    var _pager = "#pay_data_pager";
    var _$searchForm = $("#search_form");

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>付款管理</li>");

        // 加载数据
        initPayGrid();

        // 初始化查询表单
        initSearchForm();
    }

    function doSearch() {
        var searchInfo = _$searchForm.serializeArray();
        var _post = {};
        _.each(searchInfo, function(n, i) {
            _post[n.name] = n.value;
            CommonUtils.logError(n.name + " " + n.value);
        });

        _$grid.setGridParam(_.extend({
            page : 1,
            postData : _post
        }, CommonUI.commonGridConfig())).trigger("reloadGrid");

    }

    function initSearchForm() {

        _$searchForm.ajaxForm({
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
                return false;
            },
            success : function() {
                CommonUtils.logError("提交成功.");
            },
            resetForm : true
        });

        // 搜索
        $("#search_submit").click(function() {
            doSearch();
        });

        // 清除搜索条件
        $("#search_cancel").click(function() {
            $("#search_form").get(0).reset();
            doSearch();
        });
    }

    function initPayGrid() {
        _$grid.jqGrid(_.extend({
            url : "pay/listStatItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '存储编号', '果农信息', '货物总价', '付款总额', '欠款总额', '付款时间' ],
            colModel : [ {
                name : '---',
                index : '---',
                width : 60,
                align : "center"
            }, {
                name : 'storageSn',
                index : 'storageSn',
                width : 120,
                align : "center"
            }, {
                name : 'farmerName',
                index : 'farmerName',
                width : 60,
                align : "center"
            }, {
                name : 'totalPrice',
                index : 'totalPrice',
                width : 120,
                formatter : 'currency',
                align : "right"
            }, {
                name : 'payPrice',
                index : 'payPrice',
                width : 80,
                formatter : 'currency',
                align : "right"
            }, {
                name : 'owePrice',
                index : 'owePrice',
                width : 120,
                formatter : 'currency',
                align : "right"
            }, {
                name : 'payTime',
                index : 'payTime',
                width : 120,
                align : "center"
            } ],
            pager : _pager,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                _$grid.setRowData(rowid, {
                    '---' : rowid,
                });
            },
            loadComplete : function(data) {
                $("#sum_price").text(CommonUtils.formatCurrency(data.payStat.sumPrice));
                $("#pay_money").text(CommonUtils.formatCurrency(data.payStat.payPrice));
                $("#owe_money").text(CommonUtils.formatCurrency(data.payStat.owePrice));
            }
        }, CommonUI.commonGridConfig()));
    }

    module.init = init;

    return module;
}($, window.PayStatistic || {}));

$(function() {
    PayStatistic.init();
});
