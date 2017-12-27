window.StorageIn = (function($, module, CommonUtils) {

    var _$form = $("#storage_add_form");
    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>入库</li>");

        // 增加相关的校验
        _initValidate();

        // 初始化为异步表单
        _initForm();

        // 初始化自动处理存储编号
        _initStorageSn();

    }

    function _initForm() {
        // 初始化下拉框 
        CommonUtils.initSelect("apple_type", "common/listAppleType.action", "");
        CommonUtils.initSelect("apple_size", "common/listAppleSize.action", "");
        CommonUtils.initSelect("repo_code", "common/listRepoInfo.action", "");
        CommonUtils.initSelectByCount("repo_sep", 0);

        // 初始化异步提交表单
        _$form.ajaxForm({
            dataType : 'json', // 解决IE8的问题
            url : "storage/in.action",
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
            },
            success : function() {
                CommonUtils.logError("提交成功.");

                //成功后，要给出提示, 延迟一段结束
                $("#goods_count").val("");
                $("#storage_notify").removeClass('hide');
                window.setTimeout(function() {
                    $("#storage_notify").addClass('hide');
                }, 5000);
            },
            resetForm : false
        });

        // 取消 
        $("#cancel_submit").on("click", function() {
            CommonUtils.loadPageByName("jsp/common/home_page.jsp");
        });
    }

    function _initValidate() {
        _$form.validate({
            rules : {
                'info.storageSn' : {
                    required : true,
                    digits : true
                },
                'info.farmerName' : "required",
                'info.unitPrice' : {
                    required : true,
                },
                'info.appleType' : {
                    required : true,
                    digits : true
                },
                'info.appleSize' : {
                    required : true,
                    digits : true
                },
                'info.repoCode' : "required",
                'info.goodsCount' : {
                    required : true,
                    digits : true
                },
                'info.repoSep' : {
                    required : true,
                    digits : true, 
                    min : 1
                }
            },
            messages : {
                'info.storageSn' : {
                    required : "请输入存储编号",
                    digits : "必须输入有效的存储编号"
                },
                'info.unitPrice' : {
                    required : "请输入存储单价",
                },
                'info.farmerName' : "请输入果农性名",
                'info.appleType' : {
                    required : "请选择苹果尺寸",
                    digits : "必须输入有效的数字"
                },
                'info.appleSize' : {
                    required : "请选择苹果尺寸",
                    digits : "必须输入有效的数字"
                },
                'info.repoCode' : "请选择仓库编号",
                'info.goodsCount' : {
                    required : "请输入货物数量",
                    digits : "必须输入有效的数字"
                },
                'info.repoSep' : {
                    required : "请输入隔档编号",
                    digits : "必须输入有效的隔档编号", 
                    min : "隔档编号必须大于1",
                }
            }
        });
    }

    function _initStorageSn() {
        var resultData = {}; // 数据定义
        var allRepo = {}; // 所有的仓库
        var maxStorageSn = 1;        

        // 获得所有的列表
        $.ajax({
            type : "post",
            url : "farmer/listAllItem.action",
        }).done(function(data) {

            // 组织相应的数据
            resultData = data.datas;
            
        }).fail(function(data) {
            CommonUtils.logError("listAllItem failed!");
        });

        $.ajax({
            type : "post",
            url : "reposity/listAllItem.action",
        }).done(function(data) {

            // 组织相应的数据
            allRepo = data.datas;
            
        }).fail(function(data) {
            CommonUtils.logError("listAllItem failed!");
        });

        // 存储编号的相关信息联动
        $("#relate_info").on("click",function(){
            var sn = $("#storage_sn").val();
            // 取得数据
            var target= _.findWhere(resultData, {"storageSn": sn});
            if(target && target.farmerName){
                // 设置数值
                $("#farmer_name").val(target.farmerName);
                $("#farmer_tel").val(target.farmerTel);
                $("#unit_price").val(target.unitPrice);
            }
        });
        
        // 存储编号的相关信息联动
        $("#storage_sn").on("change",function(){
            $("#farmer_name").val("");
            $("#farmer_tel").val("");
            $("#unit_price").val("");
        });
        
        
        // 仓库变量影响隔档的校验规则
        $("#repo_code").on("change", function() {
            var repoCode = $("#repo_code").val();
            if( repoCode !== null ){
                var maxStorage = _.findWhere(allRepo, {"repoCode": repoCode});
                maxStorageSn = (maxStorage != null) ? maxStorage.sepNum : 0;
                CommonUtils.initSelectByCount("repo_sep", maxStorageSn);
            }
            
        });
    }

    module.init = init;

    return module;
}($, window.StorageIn || {}, window.CommonUtils));

$(function() {
    StorageIn.init();
});