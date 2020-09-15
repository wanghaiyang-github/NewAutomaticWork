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
            <div class="container-right">
                <div class="container-fluid Scrollbar">
                    <div class="side-body padding-top">
                        <div class="container-style">
                            <div class="row posi-rel">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                    <div>
                                        <div class="top-tag-box">
                                            <div class="table-tag">
                                                <div class="title-first-span">
                                                    <span></span>
                                                    <span>查询列表</span>
                                                </div>
                                                <ul class="ul-btns">
                                                    <li type="button" id='newLabUserBtn'
                                                        style="font-weight:400; margin-right: 50px;">
                                                        <span><img src="<%=path%>/automatedWork/img/dc001.png" alt=""></span>
                                                        <span>新增用户</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>登录名</th>
                                                    <th>姓名</th>
                                                    <th>性别</th>
                                                    <th>证件号</th>
                                                    <th>联系电话</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="labUserListTbody">
                                                <c:forEach items="${labUserList}" var="labUser" varStatus="s">
                                                    <tr>
                                                        <td>${s.count}</td>
                                                        <td>${labUser.loginName}</td>
                                                        <td>${labUser.userName}</td>
                                                        <td>${labUser.gender}</td>
                                                        <td>${labUser.cardId}</td>
                                                        <td>${labUser.phoneNum}</td>
                                                        <td>
                                                            <input type="hidden" name="labUserId" value="${labUser.id}"/>
                                                            <span type="button" class="editBtn">
                                                                <span><img src="<%=path%>/automatedWork/img/bj.png" alt=""></span>
                                                                <span>编辑</span>
                                                            </span>
                                                            <span class="delBtn">
                                                                <span><img src="<%=path%>/automatedWork/img/tra.png" alt=""></span>
                                                                <span>删除</span>
                                                            </span>
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
            <%@ include file="../../common/foot.jsp" %>
        </div>
        <div class="modal fade modal-primary" id="sampleModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="modelnewStyle">
                                <div>
                                    <span></span>
                                    <span>检材编号</span>
                                    <span name="sampleNoModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>样板名</span>
                                    <span name="boardNoModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>提取板孔位</span>
                                    <span name="extractPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增板孔位</span>
                                    <span name="pcrPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>电泳板孔位</span>
                                    <span name="syPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>预测验方法</span>
                                    <span name="preExperimentalMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>确证方法</span>
                                    <span name="confirmatoryMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检材转移方法</span>
                                    <span name="sampleTransferMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>洗脱体积</span>
                                    <span name="elutionModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检材属性</span>
                                    <span name="samplePropertyModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建者</span>
                                    <span name="createPersonModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建时间</span>
                                    <span name="createDatetimeModel"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="newstyle">
                            <button data-dismiss="modal" class="btn btn-info btn-sm" type="button">关 闭</button>
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

        $("#newLabUserBtn").on("click",function(){
            location.href='<%=path%>/center/7/editLabUser.html?operateType=1';
        });

        $(".editBtn","#labUserListTbody").on('click',function(){
            var labUserId = $("input[name='labUserId']", $(this).parent()).val();
            location.href='<%=path%>/center/7/editLabUser.html?labUserId='+labUserId +"&operateType=2";
        });

        $(".delBtn","#labUserListTbody").on("click",function(){
            var labUserId=$("input[name='labUserId']", $(this).parent()).val();
            $("#identification").val("1");
            $("#param1").val(labUserId);
            $().confirmPersonOperation("确认删除吗?");
        });

        $.fn.confirmPersonOperation = function (obj) {
            $("#confirmContent").text(obj);
            $("#confirm-model").modal();
        }

        $("#confirmBtn").on("click", function (){
            var identification = $("#identification").val();
            if (identification == "1") {
                location.href='<%=path%>/center/7/delLabUser.html?labUserId='+$("#param1").val();
            }
        });
    });
</script>
</body>
</html>