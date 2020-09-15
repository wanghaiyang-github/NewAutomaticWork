<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${loginTitleName}</title>
    <%@ include file="../common/newLink.jsp" %>
    <%@ include file="../common/include.jsp" %>
    <%
        String path = request.getContextPath();
    %>
</head>
<body class="flat-blue">
<div class="app-container">
    <div class="row content-container">
        <%@ include file="../common/platformName.jsp" %>
        <div style="margin-top:60px;">
            <%@ include file="../common/menuManagement.jsp" %>
            <div class="container-fluid">
                <div class="side-body padding-top">
                    <div class="container-style">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="con-nav-top">
                                    <div class="con-nav-top-left">
                                        <span>
                                            <span></span>
                                            <span>用户信息</span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal tasi-form">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">旧密码 <i class="fa fa-asterisk color_red"></i></label>
                                        <div class="col-md-5">
                                            <input id="oldPassword" type="password" class="form-control small required" value=""/>
                                        </div>
                                        <div class="col-md-2 has-error hide">
                                            <p class="help-block">必填项</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">新密码 <i class="fa fa-asterisk color_red"></i></label>
                                        <div class="col-md-5">
                                            <input id="newPassword" type="password" class="form-control small required" value=""/>
                                        </div>
                                        <div class="col-md-2 has-error hide">
                                            <p class="help-block">必填项</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">再次输入新密码 <i class="fa fa-asterisk color_red"></i></label>
                                        <div class="col-md-5">
                                            <input id="confirmNewPassword" type="password" class="form-control small required" value=""/>
                                        </div>
                                        <div class="col-md-2 has-error hide">
                                            <p class="help-block">必填项</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-8" id="messagLabel" style="text-align:center; font-weight:bold; font-size:16px;"></label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>
                    <button class="btn btn-info btn-sm" id="saveBtn"><i class="fa fa-check"></i> 保 存 </button>
                    <button class="btn btn-info btn-sm" onclick="javascript:history.go(-1);"><i class="fa fa-reply"></i> 返 回 </button>
                    <input type="hidden" id="updatePasswordUrl" value="<%=path%>/center/updatePassword.html"/>
                </div>
                <div>
                    <p>Copyright© 2019 北京博安智联科技有限公司</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- BEGIN JS -->
<%@ include file="../common/newScript.jsp" %>
<script type="">
    $(function () {
        'use strict';
        $("#saveBtn").on('click',function(){
            var oldPassword = $("#oldPassword").val();
            if (oldPassword == "") {
                alert("请输入旧密码！");
                $("#oldPassword").focus();
                return;
            }
            var newPassword = $("#newPassword").val();
            if (newPassword == "") {
                alert("请输入新密码！");
                $("#newPassword").focus();
                return;
            }
            var confirmNewPassword = $("#confirmNewPassword").val();
            if (confirmNewPassword == "") {
                alert("请再次输入新密码！");
                $("#confirmNewPassword").focus();
                return;
            }
            if (confirmNewPassword == newPassword){

            }else {
                alert("新密码不一致，请重新输入！");
                $("#confirmNewPassword").val("");
                $("#confirmNewPassword").focus();
                return;
            }

            $.ajax({
                url:"<%=path%>/center/updatePassword.html?oldPassword=" + oldPassword + "&newPassword=" + newPassword,
                type:"get",
                dataType:"json",
                success:function(data){
                    alert("修改成功！");
                    window.history.go(-1);
                },
                error:function(e) {
                    alert("修改失败！");
                }
            });
        });

    });
</script>
</body>

</html>