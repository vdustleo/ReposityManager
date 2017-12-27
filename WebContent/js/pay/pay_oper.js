window.PayOperate = (function($, module, LODOP) {
    
    var _$form = $("#pay_oper_form");
    var _$searchForm = $("#search_form");
    //定义的要打印的数据来源 
    var _dataForPrint = {}; 
    
    /**
     * 初始化模块
     */
    function init() {
        // 修改导航
        $("#navi > ul").empty().html("<li>首页</li> -><li>付款操作</li>");

        // 增加相关的校验
        initValidate();
        
        // 初始化为异步表单
        _initForm();

    }

    function _initForm() {

        // 查询表格异步提交
        _$searchForm.ajaxForm({
            dataType : 'json', 
            url: "pay/queryMoney.action" ,  
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
            },
            success : function (data) {
                CommonUtils.logError("提交成功.");
                CommonUtils.logError(!!data.info);
                if(!!data.info){
                    _dataForPrint.farmerName = data.info.farmerName;
                    _dataForPrint.storageSn = data.info.storageSn;
                    _dataForPrint.totalPrice = data.info.totalPrice;
                    _dataForPrint.owePrice = data.info.owePrice;
                    _dataForPrint.payedPrice = data.info.payPrice;
                    _initPayData(data.info);
                }
                // 无数据
                else{
                    $("#error_notify").removeClass('hide');
                    window.setTimeout(function() {
                        $("#error_notify").addClass('hide');
                    }, 5000);
                }
            },
            resetForm : false
        });
        
        // 清除搜索条件, 清除相关的数据
        $("#search_cancel").click(function() {
            _$searchForm.get(0).reset();
            _$form.get(0).reset();
        });
        
        // 操作表格异步提交(带打印的)
        _$form.ajaxForm({
            dataType : 'json', // 解决IE8的问题
            url: "pay/add.action" ,  
            beforeSubmit : function() {
                CommonUtils.logError("准备提交...");
                _dataForPrint.payPrice = parseInt($("#pay_price").val(), 10);
            },
            success : function () {
                CommonUtils.logError("提交成功.");
                $("#pay_price").attr("disabled", "disabled");
                $("#confirm_print").attr("disabled", "disabled");
                $("#confirm_only").attr("disabled", "disabled");
                
                //调用打印
                _printTicket();
            },
            resetForm : true
        });
        
        // 不打印提交
        $("#confirm_only").on("click", function() {
            var _data = $("#pay_oper_form").serialize();
            $.ajax({
                type : "post",
                url : "pay/add.action",
                data : _data
            }).done(function(data) {
                CommonUtils.logError("提交成功.");
                $("#pay_price").attr("disabled", "disabled");
                $("#confirm_print").attr("disabled", "disabled");
                $("#confirm_only").attr("disabled", "disabled");
                $("#pay_oper_form").get(0).reset();
            });
        });
        
        // 取消 
        $("#cancel_submit").on("click", function() {
            CommonUtils.loadPageByName("jsp/common/home_page.jsp");
        });
        
    }
    
    // 打印支付单据
    function _printTicket(){
        // 如果没有数据，就不进行打印
        if(!_dataForPrint){
            return;
        }
        
        LODOP = getLodop();
        LODOP.PRINT_INIT("支付单");
        LODOP.SET_SHOW_MODE("BKIMG_WIDTH", 500);
        LODOP.SET_SHOW_MODE("BKIMG_HEIGHT", 300);
        
        // 位置定义
        var orginTop = 50;
        var orginLeft = 200;
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
        
        // 打印项目
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "果农姓名:");
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "存储编号:");
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "存款总价:");
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "已付金额:");
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "支付金额:");
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, "支付时间:");
        
        // 打印数据
        orginTop = 50;
        _nextRow();
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, _dataForPrint.farmerName);
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, _dataForPrint.storageSn);
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, CommonUtils.formatCurrency(_dataForPrint.totalPrice));
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, CommonUtils.formatCurrency(_dataForPrint.payedPrice));
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, CommonUtils.formatCurrency(_dataForPrint.payPrice));
        LODOP.ADD_PRINT_TEXT(_nextLine(), orginLeft, width, height, moment().format('YYYY-MM-DD'));
        LODOP.PREVIEW();        
    }

    function initValidate() {
        _$form.validate({
            rules : {
                'info.payPrice' : {
                    required : true,
                    number : true,
                    min: 0,
                }
            },
            messages : {
                'info.payPrice' : {
                    required : "请输入支付金额",
                    number : "必须输入有效的数字"
                }
            }
        });
        
        _$searchForm.validate({
            rules : {
                'cond.storageSn' : "required"
            },
            messages : {
                'cond.storageSn': {
                    required : "存储编号不能为空",
                }
            },
            errorPlacement: function(error, element) {
                error.insertAfter($('#error_notify'));
            },
            errorClass: "error_info"
        });        
    }
    
    // 初始化要支付的表格
    function _initPayData(info) {
        $("#total_price").val(CommonUtils.formatCurrency(info.totalPrice));
        $("#_pay_price").val(CommonUtils.formatCurrency(info.payPrice));
        $("#owe_price").val(CommonUtils.formatCurrency(info.owePrice));

        // 初始化历史数据
        $("#h_farmer_name").val(info.farmerName);
        $("#h_storage_sn").val(info.storageSn);
        $("#h_total_price").val(info.totalPrice);
        $("#h_owe_price").val(info.owePrice);
        $("#h_psn").val(info.psn);

        // 金额不为0，激活
        if( parseInt(info.owePrice, 10) !== 0 ){
            $("#pay_price").removeAttr("disabled");
            $("#confirm_print").removeAttr("disabled");
            $("#confirm_only").removeAttr("disabled");
            
        }

        var maxPrice = parseInt(info.owePrice, 10);
        // 动态支付金额的检验规则
        $("#pay_price").rules("remove", "range");
        $("#pay_price").rules("add", {
            range : [ 1, maxPrice ],
            messages : {
                range : $.validator.format("金额必须在{0}到{1}之间")
            }
        });
    }

    module.init = init;

    return module;
}($, window.PayOperate || {}, LODOP));

$(function() {
    PayOperate.init();
});