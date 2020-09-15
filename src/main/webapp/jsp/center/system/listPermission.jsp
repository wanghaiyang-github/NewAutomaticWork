<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${loginTitleName}</title>
    <%@ include file="../../common/newLink.jsp" %>
    <%@ include file="../../common/include.jsp" %>
    <%
        String path = request.getContextPath();
    %>
</head>
<body class="flat-blue">
<div class="app-container">
    <div class="row content-container">
        <%@ include file="../../common/platformName.jsp" %>
        <div style="margin-top:60px;">
            <%@ include file="../../common/menuManagement.jsp" %>
            <div class="container-fluid">
                <div class="side-body padding-top">
                    <div class="container-style">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="con-nav-top">
                                    <div class="con-nav-top-left">
                                        <span>
                                            <span></span>
                                            <span>权限列表</span>
                                        </span>
                                        <span type="button" id='newLabPermissionBtn' data-toggle="modal"/>
                                        <span><img src="<%=path%>/automatedWork/img/26.png" alt=""></span>
                                        <span>添加</span>
                                        </span>
                                        <span>
                                            <a href="<%=path%>/center/modifyPassword.html">
                                                <span>修改当前登录人密码</span>
                                            </a>
                                        </span>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>授权名</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="labRoleListTbody">
                                                <c:forEach items="${labRoleList}" var="labRole" varStatus="s">
                                                    <tr>
                                                        <td>${s.count}</td>
                                                        <td>${labRole.roleName}</td>
                                                        <td>
                                                            <input type="hidden" name="labRoleId" value="${labRole.id}"/>
                                                            <button name="editBtn" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i> 编 辑</button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- BEGIN JS -->
        <%@ include file="../../common/newScript.jsp" %>
        <script type="">
            $(function () {
                'use strict';

                $("#newLabPermissionBtn").on("click",function(){
                    location.href='<%=path%>/center/7/editPermission.html?operateType=1';
                });

                $("button[name='editBtn']","#labRoleListTbody").on('click',function(){
                    var labRoleId = $("input[name='labRoleId']", $(this).parent()).val();
                    location.href='<%=path%>/center/7/editPermission.html?labRoleId='+labRoleId +"&operateType=2";
                });
            });
        </script>
</body>

</html>