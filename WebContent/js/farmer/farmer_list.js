window.Farmer = (function($, module) {

    var _$grid = $("#farmer_data");
    var _pager = "#farmer_data_pager";
    var _$searchForm = $("#search_form");

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>果农信息</li>");

        // 加载数据
        initFarmerGrid();

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

    function deleteRecord(storageSn) {

        if (window.confirm("你确定要删除吗?")) {

            $.ajax({
                type : "post",
                url : "farmer/hasStorage.action",
                data : {
                    "info.storageSn" : storageSn
                }
            }).done(function(data) {
                if (data.hasStorage) {
                    CommonUtils.logError("Farmer " + storageSn + " has bean in used! Can't delete.");
                    CommonUtils.notify(_.sprintf("存储编号%s关联货物，不能删除!", storageSn));

                }
                // 如果没有引用，直接删除
                else {
                    CommonUtils.logError("deleteRecord: canDelete!");

                    $.ajax({
                        type : "post",
                        url : "farmer/delete.action",
                        data : {
                            "info.storageSn" : storageSn
                        }
                    }).done(function() {
                        _$grid.trigger("reloadGrid");
                    }).fail(function() {
                        CommonUtils.logError("deleteRecord " + storageSn + " failed!");
                    });
                }
            }).fail(function(data) {
                CommonUtils.logError("deleteRecord " + repoCode + " failed!");
            });
        }

    }

    function initFarmerGrid() {
        _$grid.jqGrid(_.extend({
            url : "farmer/listItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '存储编号', '果农姓名', '联系电话', '存储单价', '操作' ],
            colModel : [ {
                name : '---',
                index : '---',
                width : 30,
                align : "center"
            }, {
                name : 'storageSn',
                index : 'storageSn',
                width : 120,
                align : "center"
            }, {
                name : 'farmerName',
                index : 'farmerName',
                width : 120,
                align : "center"
            }, {
                name : 'farmerTel',
                index : 'farmerTel',
                width : 120,
                align : "right"
            }, {
                name : 'unitPrice',
                index : 'unitPrice',
                width : 120,
                align : "right"
            }, {
                name : 'oper',
                index : 'oper',
                width : 150,
                align : "center"
            } ],
            pager : _pager,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                var oper_info = "<a class='operation' href='javascript:void(0)' onclick=Farmer.deleteRecord('" + rowdata["storageSn"] + "')>删除</a>";
                _$grid.setRowData(rowid, {
                    '---' : rowid,
                    'oper' : oper_info
                });
            }
        }, CommonUI.commonGridConfig()));
    }

    module.init = init;
    module.deleteRecord = deleteRecord;

    return module;
}($, window.Farmer || {}));

$(function() {
    Farmer.init();
});
//@ sourceURL = framer_list.js