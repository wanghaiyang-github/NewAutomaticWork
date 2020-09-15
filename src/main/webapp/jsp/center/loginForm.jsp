<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/include.jsp" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>
    <!-- BEGIN META -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Custom Theme">
    <!-- END META -->
    <title>
        ${loginTitleName}
    </title>
    <%@ include file="../common/newLink.jsp" %>
</head>
<body class="flat-blue" style="height: 100%;">
<div class="log-bg-box">
    <div class="login-left"></div>
    <div class="login-right">
        <div class="login-right-container">
            <div>
                <img src="<%=path %>/automatedWork/img/bazl.png" alt="" style="width: 66px;height: 89px;">
            </div>
            <div>
                <p>全 自 动 工 作 站 检 材 处 理 平 台</p >
                <%--<p>Fully automatic material inspection and processing platform</p>--%>
            </div>
            <div>
                <div>
                    <input type="text" placeholder="用户名" class="userName" name="loginname">
                </div>
                <div>
                    <input type="password" placeholder="密码" class="passWord" name="password">
                </div>
            </div>
            <div>
                <button type="button" id="loginBtn">登 录</button>
            </div>
            <div>
                <p>北京博安智联科技有限公司</p>
                <p>公司电话：400-011-5530</p>
            </div>
        </div>
    </div>
</div>
<!-- Javascript Libs -->

<%@ include file="../common/newScript.jsp" %>
<script>
    $(function(){
        document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                $("#loginBtn").click();
            }
        }
        // 登录操作
        $("#loginBtn").click(function () {
            var userInfo = {
                userName:$(".userName").val(),
                passWord:$(".passWord").val()
            }
            console.log(userInfo);
            if(userInfo.userName == "" || userInfo.userName == null || userInfo.userName == undefined ){
                $(".userName").focus();
                $("#modelContent").text("用户名，密码不能为空！");
                $("#msg-model").modal();
                return false;
            }else if(userInfo.passWord == "" || userInfo.passWord == null || userInfo.passWord == undefined ){
                $(".passWord").focus();
                $("#modelContent").text("用户名，密码不能为空！");
                $("#msg-model").modal();
                return false;
            }else {
                $.ajax({
                    type: "post",
                    url: "<%=path %>/center/loginHome.html",
                    dataType: "json",
                    success: function (data) {
                        if(data.success){
                            var loginname = $(".userName").val();
                            var password = $(".passWord").val();
                            location.href = "<%=path %>/center/login.html?loginname="+loginname+"&password="+password;
                        }else{
                            alert("登录失败!");
                        }
                    },
                    error:function(data){
                        alert("登录失败!");
                    }
                });
            }
        })
    })

</script>
</body>
</html>

