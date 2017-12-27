window.ReposityAdd = (function($, module) {

    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>添加仓库</li>");

        // 增加相关的校验
        _initValidate();

        // 初始化为异步表单
        _initForm();

    }

    function _initForm() {
        $("#repo_add_form").ajaxForm({
            dataType : 'json', // 解决IE8的问题
            url : "reposity/add.action",
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
            },
            success : function() {
                CommonUtils.logError("提交成功.");

                //成功后，要给出提示, 延迟一段结束 
                $("#repo_notify").removeClass('hide');
                window.setTimeout(function() {
                    $("#repo_notify").addClass('hide');
                }, 5000);
            },

            resetForm : true
        });

        // 取消 
        $("#cancel_submit").on("click", function() {
            CommonUtils.loadPageByName("jsp/reposity/reposity_list.jsp");
        });
    }

    function _initValidate() {
        $("#repo_add_form").validate({
            rules : {
                'info.repoCode' : "required",
                'info.sepNum' : {
                    required : true,
                    digits : true,
                    min : 1,
                },
                'info.admin' : {
                    required : true,
                    minlength : 2
                },
            },
            messages : {
                'info.repoCode' : "请输入仓库编号",
                'info.sepNum' : {
                    required : "请输入最大存储空间",
                    digits : "必须输入有效的数字",
                    min : "必须输入有效的数值"
                },
                'info.admin' : {
                    required : "请输入管理员名称",
                    minlength : "管理员名称不能小于3个字符",
                }
            }
        });
    }

    module.init = init;

    return module;
}($, window.ReposityAdd || {}));

$(function() {
    ReposityAdd.init();
});
