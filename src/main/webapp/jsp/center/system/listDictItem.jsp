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
                                        <span type="button" id='addBtn' data-toggle="modal"/>
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
                                                    <th>字典编号</th>
                                                    <th>字典名称</th>
                                                    <th>字典详情</th>
                                                    <th>创建时间</th>
                                                    <th>创建人</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="dictItemListTbody">
                                                <c:forEach items="${dictItemList}" var="dictItem" varStatus="s">
                                                    <tr>
                                                        <td>${s.count}</td>
                                                        <td>${dictItem.dictTypeCode}</td>
                                                        <td>${dictItem.dictCode}</td>
                                                        <td>${dictItem.dictName}</td>
                                                        <td width="350">${dictItem.dictDesc}</td>
                                                        <td><fmt:formatDate value="${dictItem.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                        <td>${dictItem.createPerson}</td>
                                                        <td>
                                                            <input type="hidden" name="dictId" value="${dictItem.id}"/>
                                                            <input type="hidden" name="dictTypeCode" value="${dictItem.dictTypeCode}"/>
                                                            <input type="hidden" name="dictItemCode" value="${dictItem.dictCode}"/>
                                                            <input type="hidden" name="dictName" value="${dictItem.dictName}"/>
                                                            <input type="hidden" name="dictDesc" value="${dictItem.dictDesc}"/>
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
                <div class="footer">
                    <div>
                        <button class="btn btn-info" id="backBtn" type="button"><i class="fa fa-reply"></i> 返 回 </button>
                    </div>
                </div>
                <div class="modal fade modal-primary" id="dictItemModal" data-backdrop="static"
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
                                            <input name="dictTypeCode" id="dictTypeCode" type="text" class="form-control small required" value="${dictTypeCode}"/>
                                        </div>
                                        <div>
                                            <p>字典编号</p>
                                            <input name="dictCode" id="dictCode" type="text" class="form-control small required"/>
                                        </div>
                                        <div>
                                            <p>字典名称</p>
                                            <input name="dictItemName" id="dictItemName" type="text" class="form-control small required"/>
                                        </div>
                                        <div>
                                            <p>字典详情</p>
                                            <input name="dictItemDesc" id="dictItemDesc" type="text" class="form-control small required"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div>
                                    <input type="hidden" id="dictItemId" name="dictItemId"/>
                                    <input type="hidden" id="dictIndex" name="dictIndex"/>
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
                    EditDictItemRow(this);
                });

                $("button[name='editBtn']").on("click",function(){
                    EditDictItemRow(this);
                });

                function EditDictItemRow(obj){
                    var $curTR = $(obj).parents("tr");
                    var dictItem = {};

                    dictItem.id = $("input[name='dictId']", $curTR).val();
                    dictItem.dictCode = $("input[name='dictItemCode']", $curTR).val();
                    dictItem.dictTypeName = $("input[name='dictName']", $curTR).val();
                    dictItem.dictDesc = $("input[name='dictDesc']", $curTR).val();
                    dictItem.index = $(obj).parents("tr").index();

                    newDictItemRow(dictItem);
                }

                function newDictItemRow(dictItem) {
                    $("input[name='dictItemId']", "#dictItemModal").val(dictItem.id);
                    $("input[name='dictCode']", "#dictItemModal").val(dictItem.dictCode);
                    $("input[name='dictItemName']", "#dictItemModal").val(dictItem.dictTypeName);
                    $("input[name='dictItemDesc']", "#dictItemModal").val(dictItem.dictDesc);
                    $("input[name='dictIndex']", "#dictItemModal").val(dictItem.index);

                    $("#dictItemModal").modal('show');
                }

                function checkInputValids(){
                    var dictCode = $("#dictCode").val();
                    if(dictCode == ""){
                        alert("请输入字典编号!");
                        $("#dictCode").focus();
                        return;
                    }

                    var dictItemName = $("#dictItemName").val();
                    if(dictItemName == ""){
                        alert("请输入字典名称!");
                        $("#dictItemName").focus();
                        return;
                    }

                    var totalRows = $("tr", "#dictItemListTbody").length;
                    var dictIndex = $("input[name='dictIndex']", "#dictItemModal").val();
                    var code = $("input[name='dictCode']", "#dictItemModal").val();

                    var itemCode;
                    for (var i = 0;i < totalRows;i++) {
                        if (dictIndex == i)
                            continue;

                        itemCode = $("input[name='dictItemCode']", "tbody tr:eq("+ i +")").val();
                        if (itemCode == code) {
                            alert("输入的字典编号重复！");
                            return;
                            break;
                        }
                    }

                    return true;
                }

                function getParams(){
                    var dictItem = {};

                    dictItem.id=$("#dictItemId").val();
                    dictItem.dictTypeCode = $("#dictTypeCode").val();
                    dictItem.dictCode = $("#dictCode").val();
                    dictItem.dictName = $("#dictItemName").val();
                    dictItem.dictDesc = $("#dictItemDesc").val();

                    return dictItem;
                }

                $("#saveBtn").on("click",function(){
                    if(!checkInputValids())
                        return;

                    $.ajax({
                        url:"<%=path%>/center/7/addOrEditDictItem.html",
                        type:"post",
                        data:JSON.stringify(getParams()),
                        dataType:"json",
                        contentType:"application/json;charset=utf-8",
                        success: function (data) {
                            if(data.success){
                                var dictTypeCode = $("#dictTypeCode").val();
                                location.href = "<%=path%>/center/7/getChildrenDictItem.html?dictTypeCode="+dictTypeCode;
                            }else {
                                alert("操作失败!");
                            }
                        },
                        error:function(data){
                            alert("操作失败!");
                        }
                    });
                });

                $("#backBtn").on("click",function(){
                    location.href='<%=path%>/center/7/03.html';
                });

                $("button[name='delBtn']","#dictItemListTbody").on("click",function(){
                    if(confirm("确认要删除该字典信息吗？")){
                        var dictId=$("input[name='dictId']", $(this).parent()).val();
                        var dictTypeCode=$("#dictTypeCode").val();

                        location.href='<%=path%>/center/7/delDictItem.html?dictItemId='+dictId+"&dictTypeCode="+dictTypeCode;
                    }
                });
            });
        </script>
</body>

</html>