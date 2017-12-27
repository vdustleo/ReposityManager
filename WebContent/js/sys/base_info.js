/**
 * 基础信息设置
 */
window.BaseInfo = (function($, module) {

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>系统设置</li> -><li>基础信息</li>");

        // 初始化仓库的初值
        _initValues();
        
        // 增加相关的校验
        _initValidate();
        
        // 初始化为异步表单
        _initForm();

    }
    
    function _initValues() {
        
        $.ajax({
            type : "post",
            url : "reposity/queryRepoName.action",
        }).done(function(data) {
            $('#repo_name').val(data.info.repoCode);
        });
    }

    function _initForm() {
        $("#base_info_form").ajaxForm({
            dataType : 'json', // 解决IE8的问题
            url: "reposity/updateRepoName.action" ,  
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
            },
            success : function () {
                CommonUtils.logError("提交成功.");
                
                //成功后，要给出提示, 延迟一段结束 
                $("#baseinfo_notify").removeClass('hide');
                window.setTimeout(function() {
                    $("#baseinfo_notify").addClass('hide');
                }, 5000);
            },
            
            resetForm : false
        });
    }

    function _initValidate() {
        $("#base_info_form").validate({
            rules : {
                'info.repoCode' : "required"
            },
            messages : {
                'info.repoCode' : "仓库名称不能为空"
            }
        });
    }

    module.init = init;

    return module;
}($, window.BaseInfo || {}));

$(function() {
    BaseInfo.init();
});