window.PayRecord = (function($, module) {

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
        
        // 初始化操作区
        initOperate();
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
            success : function () {
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
    
    function _selectRows(){
        var select = _$grid.jqGrid('getGridParam', 'selarrrow');
        rows = [];
        $.each(select, function(i, n) {
            var ret = _$grid.jqGrid('getRowData', n);
            rows[rows.length] = ret;
        });

        return rows;
    }

    function initPayGrid() {
        _$grid.jqGrid(_.extend({
            url : "pay/listItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '存储编号', '果农信息', '货物总价', '付款总额', '欠款总额', '付款时间'],
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
            multiselect: true,
            pager : _pager,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                _$grid.setRowData(rowid, {
                    '---' : rowid,
                });
            }
        }, CommonUI.commonGridConfig()));
    }

    function initOperate() {
        
        // 打印入库记录
        $("#print_record").on("click", function(){
            printPayRecord();
        });
    }
    
    
    // 打印付款记录
    function printPayRecord(){
        
        var selectedRows = _selectRows();
        // 无数据，则退出
        if( !selectedRows || selectedRows.length < 1 ){
            return;
        }
        console.log(selectedRows);
        LODOP = getLodop();
        LODOP.PRINT_INIT("付款记录单");
        LODOP.SET_SHOW_MODE("BKIMG_WIDTH", 500);
        LODOP.SET_SHOW_MODE("BKIMG_HEIGHT", 300);
        
        // 位置定义
        var orginTop = 20;
        var orginLeft = 20;
        var width = 300;
        var height = 20;
        function _nextLine()
        {
            orginTop = orginTop + 20;
            return orginTop;
        }
        function _nextRow()
        {
            orginLeft = orginLeft + 100;
            return orginLeft;
        }
        
        // 打印出标题
        var repoName = CommonUtils.queryRepoName();
        repoName = _.sprintf("%s出库明细单", repoName);        
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 350, width, height, repoName);
        
        // 打印出表头
        _nextLine();        
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 100, width, height, "果农姓名");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 200, width, height, "货物总价");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 300, width, height, "付款总额");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 400, width, height, "欠款总额");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 500, width, height, "付款时间");
        
        // 打印分隔线
        _nextLine();
        LODOP.ADD_PRINT_LINE(orginTop, orginLeft + 100, orginTop, orginLeft + 600, 0, 1);
        
        // 打印实际的数据
        for (var i = 0; i < selectedRows.length; i++) {
            _nextLine();
            var item = selectedRows[i];
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 100, width, height, item.farmerName);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 200, width, height, item.totalPrice);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 300, width, height, item.payPrice);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 400, width, height, item.owePrice);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 500, width, height, item.payTime);
        }
        
        // 打印分隔线
        _nextLine();
        LODOP.ADD_PRINT_LINE(orginTop, orginLeft + 100, orginTop, orginLeft + 600, 0, 1);
        
        // 打印脚线
        _nextLine();        
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 100, width, height, "果农签字：");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 300, width, height, "经办人签字：");
        
        LODOP.PREVIEW();        
    }
    module.init = init;

    return module;
}($, window.PayRecord||{}));

$(function() {
    PayRecord.init();
});