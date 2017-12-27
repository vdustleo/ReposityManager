window.Reposity = (function($, module) {

    var _$grid = $("#reposity_data");
    var _pager = "#reposity_data_pager";

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>仓库管理</li>");

        // 加载数据
        initReposityGrid();

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

    function doSearch() {
        var search_info = $("#search_info").val();

        var _postData = {
            "cond" : search_info
        };

        _$grid.setGridParam({
            page : 1,
            postData : _postData
        }).trigger("reloadGrid");

    }

    function deleteRecord(repoCode) {

        if (window.confirm("你确定要删除吗?")) {

            $.ajax({
                type : "post",
                url : "reposity/hasStorage.action",
                data : {
                    "info.repoCode" : repoCode
                }
            }).done(function(data) {
                if (data.hasStorage) {
                    CommonUtils.logError("Reposity " + repoCode + " has bean in used! Can't delete.");
                    CommonUtils.notify(_.sprintf("仓库%s存在货物，不能删除!", repoCode));

                }
                // 如果没有引用，直接删除
                else {
                    CommonUtils.logError("deleteRecord: canDelete!");

                    $.ajax({
                        type : "post",
                        url : "reposity/delete.action",
                        data : {
                            "info.repoCode" : repoCode
                        }
                    }).done(function() {
                        _$grid.trigger("reloadGrid");
                    }).fail(function() {
                        CommonUtils.logError("deleteRecord " + repoCode + " failed!");
                    });
                }
            }).fail(function(data) {
                CommonUtils.logError("deleteRecord " + repoCode + " failed!");
            });
        }

    }

    function initReposityGrid() {
        _$grid.jqGrid(_.extend({
            url : "reposity/listItem.action",
            datatype : "json",
            mtype : "post",
            height : 300,
            width : 1188,
            colNames : [ '序号', '仓库编号', '隔档数量', '管理员', '操作' ],
            colModel : [ {
                name : '---',
                index : '---',
                width : 30,
                align : "center"
            }, {
                name : 'repoCode',
                index : 'repoCode',
                width : 120,
                align : "center"
            }, {
                name : 'sepNum',
                index : 'sepNum',
                width : 120,
                align : "right"
            }, {
                name : 'admin',
                index : 'admin',
                width : 120,
                align : "center"
            }, {
                name : 'oper',
                index : 'oper',
                width : 150,
                align : "center"
            } ],
            pager : _pager,
            afterInsertRow : function(rowid, rowdata, rowelem) {
                var oper_info = "<a class='operation' href='javascript:void(0)' onclick=Reposity.deleteRecord('" + rowdata["repoCode"] + "')>删除</a>";
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
}($, window.Reposity || {}));

$(function() {
    Reposity.init();
});