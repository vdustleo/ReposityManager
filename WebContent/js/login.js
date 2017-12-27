$(function() {

    // 
    // 提交前的优化处理，解决频繁请求服务端的问题
    // 
    $('#login_form').submit(function() {

        var name = $("#login_id").val();
        var password = $("#password").val();

        // 验证用户是否OK
        if (name === "") {
            alert("登录编号不能为空!");
            return false;
        }

        if (password === "") {
            alert("密码不能为空!");
            return false;
        }

        var _longinResult = false;
        
        $.ajax({
            type : "post",
            async : false, // 此处必须是同步请求
            url : "loginCheck.action",
            data : {
                "user.loginId" : name,
                "user.password" : password
            },
            success : function(data) {

                // 验证成功跳转到主页
                if (data.loginError === "LOGIN_SUCCESS") {
                    _longinResult = true;
                } else if (data.loginError === "NO_USER_ACCOUNT") {
                    alert("不存在该登录编号!");
                    _longinResult = false;

                } else if (data.loginError === "PASSWORD_ERROR") {
                    alert("密码输入不正确!");
                    _longinResult = false;
                }

                else {
                    alert("系统错误,请联系管理员");
                    _longinResult = false;
                }
            },
            complete : function(XMLHttpRequest, textStatus) {

            },
            error : function() {
                alert("系统错误,请联系管理员");
                _longinResult = false;
            }
        });
        
        return _longinResult;
    });
});
