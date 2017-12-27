window.FarmerAdd = (function($, module) {

    var _$dialog = null;
    /**
     * 初始化模块
     */
    function init() {

        // 增加存储单价检验方法
        jQuery.validator.addMethod("currency", function(value, element) {
            var tel = /^([0-9]+\.[0-9]+|[0-9]+)$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写价格");

        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>果农登记</li>");

        // 增加相关的校验
        _initValidate();

        // 初始化为异步表单
        _initForm();

        // 初始化确认信息对话框
        _initDialog();

    }

    function _initForm() {
        $("#farmer_add_form").ajaxForm({
            dataType : 'json', // 解决IE8的问题
            url : "farmer/add.action",
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
            },
            success : function(data) {
                CommonUtils.logError("提交成功.");
                var sn = data.info.storageSn;
                $("#storage_sn_info").text(sn);
                $('#confirm_dialog').dialog('open');
            },

            resetForm : true
        });

        // 取消
        $("#cancel_submit").on("click", function() {
            CommonUtils.loadPageByName("jsp/farmer/farmer_list.jsp");
        });
    }

    function _initValidate() {
        $("#farmer_add_form").validate({
            rules : {
                'info.farmerName' : "required",
                'info.farmerTel' : {
                    required : true,
                    digits : true
                },
                'info.unitPrice' : {
                    required : true,
                    currency : true
                },
            },
            messages : {
                'info.farmerName' : "请输入果农姓名",
                'info.farmerTel' : {
                    required : "请输入果农联系电话",
                    digits : "请输入有效的联系电话"
                },
                'info.unitPrice' : {
                    required : "存储单价不能为空",
                    currency : "请正确填写存储单价"
                }
            }
        });
    }

    function _initDialog() {
        _$dialog = $('#confirm_dialog').dialog({
            autoOpen : false,
            modal : true,
            height : 150,
            width : 300,
            maxHeight : 150,
            maxWidth : 300,
            resizable : false,
            buttons : {
                Ok : function() {
                    $(this).dialog("close");
                }
            }
        });
    }

    module.init = init;

    return module;
}($, window.FarmerAdd || {}));

$(function() {
    FarmerAdd.init();
});

//@ sourceURL=/js/farmer/farmer_add.js
