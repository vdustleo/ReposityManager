window.StorageInRecord = (function($, module) {

    var _$grid = $("#storage_data");
    var _pager = "#storage_data_pager";
    var _$searchForm = $("#search_form");
    
    // 入库总计
    var _sum_record = 0;
    var _sum_goods = 0;

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>入库记录</li>");

        // 加载数据
        initStorageGrid();

        // 初始化查询表单
        initSearchForm();
        
        // 初始化功能区
        initOperate();
    }

    function doSearch() {
        var searchInfo = _$searchForm.serializeArray();
        var _post = {};
        _.each(searchInfo, function(n, i) {
            n.value = $.trim(n.value);
            _post[n.name] = n.value;
            
            // 隔档数必须为整数
            if(n.name === 'cond.repoSep' && n.value !== "" && isNaN(parseInt(n.value, 10))){
                _post[n.name] = 99999999;
            }
        });
        
        _$grid.setGridParam(_.extend({
            page : 1,
            postData : _post
        }, CommonUI.commonGridConfig())).trigger("reloadGrid");

    }
    
    function initSearchForm() {
        // 初始化下拉框
        CommonUtils.initSelect("repo_code", "common/listRepoInfo.action", "");
        CommonUtils.initSelect("apple_type", "common/listAppleType.action", "");
        CommonUtils.initSelect("apple_size", "common/listAppleSize.action", "");
        
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
    
    function initStorageGrid() {
        _$grid.jqGrid(_.extend({
            url : "storage/listInItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '果农信息', '存储编号', '苹果类型', '苹果尺寸', '仓库编号', 
                         '货物数量', '存储单价', '存储总价', '入库时间', '隔档编号', 'id'],
            colModel : [ {
                name : '---',
                index : '---',
                width : 60,
                align : "center"
            }, {
                name : 'farmerName',
                index : 'farmerName',
                width : 80,
                align : "center"
            }, {
                name : 'storageSn',
                index : 'storageSn',
                width : 120,
                align : "center"
            }, {
                name : 'appleType',
                index : 'appleType',
                width : 80,
                align : "center"
            }, {
                name : 'appleSize',
                index : 'appleSize',
                width : 60,
                align : "center"
            }, {
                name : 'repoCode',
                index : 'repoCode',
                width : 120,
                align : "center"
            }, {
                name : 'goodsCount',
                index : 'goodsCount',
                width : 80,
                align : "right"
            }, {
                name : 'unitPrice',
                index : 'unitPrice',
                width : 80,
                formatter : 'currency',
                align : "right"
            }, {
                name : 'totalPrice',
                index : 'totalPrice',
                width : 80,
                formatter : 'currency',
                align : "right"
            }, {
                name : 'operateTime',
                index : 'operateTime',
                width : 150,
                align : "center"
            }, {
                name : 'repoSep',
                index : 'repoSep',
                width : 80,
                align : "center"
            },{
                name : 'id',
                index : 'id',
                width : 80,
                hidden: true
                
            } ],
            multiselect: true,
            pager : _pager,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                _$grid.setRowData(rowid, {
                    '---' : rowid,
                });
            },
            loadComplete: function(data){
                _sum_record = data.storageStat.sumRecord;
                _sum_goods = data.storageStat.sumGoods;
                $("#sum_record").text(_sum_record);
                $("#sum_goods").text(_sum_goods);
            }
        }, CommonUI.commonGridConfig()));
    }
    
    function initOperate() {
        
        // 打印入库记录
        $("#print_record").on("click", function(){
            printInRecord();
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
    
    // 打印入库记录
    function printInRecord(){
        var selectedRows = _selectRows();
        // 无数据，则退出
        if( !selectedRows || selectedRows.length < 1 ){
            return;
        }
        LODOP = getLodop();
        LODOP.PRINT_INIT("入库记录单");
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
        
        var repoName = CommonUtils.queryRepoName();
        repoName = _.sprintf("%s入库明细单", repoName);
            
        // 打印出标题
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 350, width, height, repoName);
        
        // 计算入库次数，入库数量
        var inTimes = selectedRows.length;
        var inTotalCount = 0;
        for (var i = 0; i < inTimes; i++) {
            var item = selectedRows[i];
            inTotalCount += parseInt(item.goodsCount, 10);
        }
            
        // 打印入库次数
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 530, width, height, "入库次数:");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 590, width, height, inTimes);
        
        // 打印入库数量
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 620, width, height, "入库数量:");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 680, width, height, inTotalCount);
        
        // 打印出表头
        _nextLine();
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 50, width, height, "果农信息");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 110, width, height, "存储编号");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 170, width, height, "苹果类型");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 230, width, height, "苹果尺寸");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 290, width, height, "仓库编号");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 350, width, height, "货物数量");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 410, width, height, "存储单价");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 470, width, height, "存储总价");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 530, width, height, "入库时间");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 680, width, height, "隔档编号");
        
        // 打印分隔线
        _nextLine();
        LODOP.ADD_PRINT_LINE(orginTop, orginLeft + 50, orginTop, orginLeft + 750, 0, 1);
        
        // 打印实际的数据
        for (var i = 0; i < selectedRows.length; i++) {
            _nextLine();
            var item = selectedRows[i];
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 50, width, height, item.farmerName);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 110, width, height, item.storageSn);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 170, width, height, item.appleType);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 230, width, height, item.appleSize);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 290, width, height, item.repoCode);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 350, width, height, item.goodsCount);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 410, width, height, item.unitPrice);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 470, width, height, item.totalPrice);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 530, width, height, item.operateTime);
            LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 680, width, height, item.repoSep);            
        }
        
        // 打印脚线
        _nextLine();
        LODOP.ADD_PRINT_LINE(orginTop, orginLeft + 50, orginTop, orginLeft + 750, 0, 1);
        _nextLine();
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 50, width, height, "果农签字：");
        LODOP.ADD_PRINT_TEXT(orginTop, orginLeft + 250, width, height, "经办人签字：");

        LODOP.PREVIEW();            
        
    }

    module.init = init;

    return module;
}($, window.StorageInRecord||{}));

$(function() {
    StorageInRecord.init();
});