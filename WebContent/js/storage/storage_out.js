window.StorageOut = (function($, module) {

    var _$grid = $("#storage_data");
    var _pager = "#storage_data_pager";
    var _$searchForm = $("#search_form");
    var _$outStorageform = $("#out_storage_form");
    var ids = {};   //选择的项

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>出库管理</li>");

        // 加载数据
        initStorageGrid();

        // 初始化查询表单
        initSearchForm();
        
        // 初始化出库对话框
        _initOutDialog();
    }
    
    function _initOutDialog() {
        
        $('#out_storage_dialog').dialog({
            autoOpen : false,
            modal : true,
            height : 150,
            width : 300,
            maxHeight : 150,
            maxWidth : 300,
            resizable : false,
            buttons : {
                '确定': function() {
                    if( $("#out_storage_form").valid() ){
                        $(this).dialog("close");
                        outExecute();
                    } else{
                        $("#goods_count").focus();
                    }
                },
                
                '取消':function(){
                    $('#out_storage_form').resetForm();
                    $(this).dialog("close");
                   }
                }
        });    
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

    // 出库操作UI
    function out(sid, maxCount) {
        $("#h_sid").val(sid);
        $("#goods_count").val('');
        $("#out_storage_form").validate({
            rules : {
                'info.goodsCount' : {
                    required : true,
                    digits : true, 
                    max: maxCount, 
                    min: 1
                }
            },
            messages : {
                'info.goodsCount' : {
                    required : "请输入货物数量",
                    digits : "必须输入有效的数字", 
                    max : "货物数量不能大于" + maxCount,
                    min : "货物数量不能小于1",
                    
                }
            }
        });
        $("#goods_count").rules("add", {  
            max: maxCount,   
            messages: {  
                max : "货物数量不能大于" + maxCount
            }  
        });
        
        $('#out_storage_dialog').dialog('open');
    }
    
    // 出库操作
    function outExecute() {
        var sid = $("#h_sid").val();
        var goodsCount = $("#goods_count").val();
        $.ajax({
            type : "post",
            url : "storage/out.action",
            data : {
                "info.sid" : sid,
                "info.goodsCount": goodsCount
            }
        }).done(function() {
            $("#storage_data").trigger("reloadGrid");
        }).fail(function() {
            CommonUtils.logError("out storage " + sid + " failed!");
        });
    }
    
    function _selectIds(){
        var select = _$grid.jqGrid('getGridParam', 'selarrrow');
        ids = {};
        $.each(select, function(i, n) {
            var ret = data.jqGrid('getRowData', n);
            ids["sid["+n+"]"] += ret.sid;
        });

        return _.count(ids) !== 0 ;
    }
    
    
    // 批量出库
    // TODO :
    // 此功能暂时无法支持，因为出库要动态修改出库的数量，所以出库的实现
    // 暂时无法批量进行。
    function batchOut() {

        if( !_selectIds() ){
            return;
        }
        
        $.ajax({
            type : "post",
            url : "storage/batchOut.action",
            data : {
                "ids" : ids
            }
        }).done(function() {
            _$grid.trigger("reloadGrid");
        }).fail(function() {
            CommonUtils.logError("out storage " + ids.toString() + " failed!");
        });
    }

    function initStorageGrid() {
        _$grid.jqGrid(_.extend({
            url : "storage/listStoreItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '果农信息', '存储编号', '苹果类型', '苹果尺寸', '仓库编号', 
                         '货物数量', '存储单价', '存储总价', '隔档编号',
                         '操作', 'sid'],
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
                name : 'repoSep',
                index : 'repoSep',
                width : 80,
                align : "center"
            },{
                name : 'oper',
                index : 'oper',
                width : 80,
                align : "center"
            },{
                name : 'sid',
                index : 'sid',
                width : 80,
                hidden: true
                
            } ],
            pager : _pager,
            multiselect: true,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                var oper_info = "<a class='operation' href='javascript:void(0)' onclick=StorageOut.out('" + rowdata["sid"] 
                + "'," + rowdata["goodsCount"]+ ")>出库</a>";
                _$grid.setRowData(rowid, {
                    '---' : rowid,
                    'oper': oper_info
                });
            }
        }, CommonUI.commonGridConfig()));
    }

    module.init = init;
    module.out = out;
    module.batchOut = batchOut;
    module.outExecute = outExecute;

    return module;
}($, window.StorageOut||{}));

$(function() {
    StorageOut.init();
});