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
                                            <span>字典列表</span>
                                        </span>
                                        <span type="button" data-toggle="modal" id='addBtn'>
                                            <span><img src="<%=path%>/automatedWork/img/26.png" alt=""></span>
                                            <span>添加</span>
                                        </span>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>字典类型</th>
                                                    <th>字典名称</th>
                                                    <th>创建时间</th>
                                                    <th>创建人</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="dictInfoListTbody">
                                                <c:forEach items="${dictInfoList}" var="dictInfo" varStatus="s">
                                                    <tr>
                                                        <td>${s.count}</td>
                                                        <td>${dictInfo.dictTypeCode}</td>
                                                        <td><a href="<%=path%>/center/7/getChildrenDictItem.html?dictTypeCode=${dictInfo.dictTypeCode}">${dictInfo.dictTypeName}</a></td>
                                                        <td><fmt:formatDate value="${dictInfo.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                        <td>${dictInfo.createPerson}</td>
                                                        <td>
                                                            <input type="hidden" name="dictId" value="${dictInfo.id}"/>
                                                            <input type="hidden" name="dictCode" value="${dictInfo.dictTypeCode}"/>
                                                            <input type="hidden" name="dictName" value="${dictInfo.dictTypeName}"/>
                                                            <input type="hidden" name="oldDictTypeCode" value="${dictInfo.dictTypeCode}"/>

                                                            <button name="editBtn" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i> 修改</button>
                                                            <button name="delBtn" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 删除</button>
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
                <div class="modal fade modal-primary" id="dictInfoModal" data-backdrop="static"
                     data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div>字典信息</div>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div>
                                        <div>
                                            <div class="name-khc">
                                                <p>字典类型</p>
                                            </div>
                                            <input name="dictTypeCode" id="dictTypeCode" type="text" class="form-control small required"/>
                                        </div>
                                        <div>
                                            <p>字典名称</p>
                                            <input name="dictTypeName" id="dictTypeName" type="text" class="form-control small required"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div>
                                    <input type="hidden" id="dictInfoId" name="dictInfoId"/>
                                    <input type="hidden" id="dictIndex" name="dictIndex"/>
                                    <input type="hidden" id="oldDictTypeCode" name="oldDictTypeCode"/>
                                    <button class="btn btn-info btn-sm" type="button" id="saveBtn">确定</button>
                                    <button data-dismiss="modal" class="btn btn-info btn-sm" type="button">关闭</button>
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

                $("#addBtn").on('click',function(){
                    EditDictInfoRow(this);
                });

                $("button[name='editBtn']","#dictInfoListTbody").on("click",function(){
                    EditDictInfoRow(this);
                });

                function EditDictInfoRow(obj){
                    var $curTR = $(obj).parents("tr");
                    var dictInfo = {};

                    dictInfo.id = $("input[name='dictId']", $curTR).val();
                    dictInfo.dictTypeCode = $("input[name='dictCode']", $curTR).val();
                    dictInfo.dictTypeName = $("input[name='dictName']", $curTR).val();
                    dictInfo.oldDictTypeCode=$("input[name='oldDictTypeCode']", $curTR).val();
                    dictInfo.index = $(obj).parents("tr").index();

                    newDictInfoRow(dictInfo);
                }

                function newDictInfoRow(dictInfo) {
                    $("input[name='dictInfoId']", "#dictInfoModal").val(dictInfo.id);
                    $("input[name='dictTypeCode']", "#dictInfoModal").val(dictInfo.dictTypeCode);
                    $("input[name='dictTypeName']", "#dictInfoModal").val(dictInfo.dictTypeName);
                    $("input[name='oldDictTypeCode']", "#dictInfoModal").val(dictInfo.oldDictTypeCode);
                    $("input[name='dictIndex']", "#dictInfoModal").val(dictInfo.index);

                    $("#dictInfoModal").modal('show');
                }

                function checkInputValids(){
                    var dictTypeCode = $("#dictTypeCode").val();
                    if(dictTypeCode == ""){
                        alert("请输入字典类型!");
                        $("#dictTypeCode").focus();
                        return;
                    }

                    var dictTypeName = $("#dictTypeName").val();
                    if(dictTypeName == ""){
                        alert("请输入字典名称!");
                        $("#dictTypeName").focus();
                        return;
                    }

                    var totalRows = $("tr", "#dictInfoListTbody").length;
                    var dictIndex = $("input[name='dictIndex']", "#dictInfoModal").val();
                    var code = $("input[name='dictTypeCode']", "#dictInfoModal").val();

                    var typeCode;
                    for (var i = 0;i < totalRows;i++) {
                        if (dictIndex == i)
                            continue;

                        typeCode = $("input[name='dictCode']", "tbody tr:eq("+ i +")").val();
                        if (typeCode.toUpperCase()== code.toUpperCase()) {
                            alert("输入的字典类型重复！");
                            return;
                            break;
                        }
                    }

                    return true;
                }

                function getParams(){
                    var dictInfo = {};

                    dictInfo.id=$("#dictInfoId").val();
                    dictInfo.dictTypeCode = $("#dictTypeCode").val();
                    dictInfo.dictTypeName = $("#dictTypeName").val();

                    dictInfo.oldDictTypeCode = $("#oldDictTypeCode").val();

                    return dictInfo;
                }

                $("#saveBtn").on("click",function(){
                    if(!checkInputValids())
                        return;

                    $.ajax({
                        url:"<%=path%>/center/7/addOrEditDictInfo.html",
                        type:"post",
                        data:JSON.stringify(getParams()),
                        dataType:"json",
                        contentType:"application/json;charset=utf-8",
                        success: function (data) {
                            if(data.success){
                                location.href = "<%=path%>/center/7/03.html";
                            }else {
                                alert("操作失败!");
                            }
                        },
                        error:function(data){
                            alert("操作失败!");
                        }
                    });
                });

                $("button[name='delBtn']","#dictInfoListTbody").on("click",function(){
                    if(confirm("确认要删除该字典信息吗？")){
                        var dictId=$("input[name='dictId']", $(this).parent()).val();
                        location.href='<%=path%>/center/7/delDictInfo.html?dictInfoId='+dictId;
                    }
                });
            });
        </script>
</body>

</html>