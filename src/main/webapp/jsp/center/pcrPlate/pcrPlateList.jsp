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
                                            <span>扩增管理</span>
                                        </span>
                                    </div>
                                    <div class="con-nav-top-right">
                                        <span>
                                            <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                            <span style="color: #006CFF !important;">导出PCR扩增样本表</span>
                                        </span>
                                        <span>
                                            <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                            <span style="color: #006CFF !important;">导出DNA检验记录扩增作业单</span>
                                        </span>
                                        <span type="button" id='deleteBtn'>
                                            <span><img src="<%=path%>/automatedWork/img/1875.png" alt=""></span>
                                            <span style="color:#FF5A55 !important;">删除</span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="container-bottom">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label>
                                                    <input type="checkbox" name="allChecked" id="allChecked"/>序号
                                                </label>
                                            </th>
                                            <th>板名</th>
                                            <th>状态</th>
                                            <th>扩增时间</th>
                                            <th>检验系统</th>
                                            <th>扩增体系（微量）</th>
                                            <th>扩增体系（常数）</th>
                                            <th>扩增循环</th>
                                            <th>试剂批次</th>
                                            <th>仪器型号</th>
                                            <th>创建者</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="pcrPlateVoListTbody">
                                        <c:forEach items="${pcrPlateVoList}" var="pcrPlateVo" varStatus="s">
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="id" value="${pcrPlateVo.entity.id}"/>
                                                    <input type="hidden" name="boardNo" value="${pcrPlateVo.entity.boardNo}"/>
                                                    <input type="hidden" name="state" value="${pcrPlateVo.entity.state}"/>
                                                    <input type="hidden" name="pcrDatetime" value="<fmt:formatDate value='${pcrPlateVo.entity.pcrDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <input type="hidden" name="testSystem" value="${pcrPlateVo.entity.testSystem}"/>
                                                    <input type="hidden" name="pcrSystemTrace" value="${pcrPlateVo.entity.pcrSystemTrace}"/>
                                                    <input type="hidden" name="pcrSystemConstant" value="${pcrPlateVo.entity.pcrSystemConstant}"/>
                                                    <input type="hidden" name="pcrRunNum" value="${pcrPlateVo.entity.pcrRunNum}"/>
                                                    <input type="hidden" name="reagentBatch" value="${pcrPlateVo.entity.reagentBatch}"/>
                                                    <input type="hidden" name="pcrInstrumentNum" value="${pcrPlateVo.entity.pcrInstrumentNum}"/>
                                                    <input type="hidden" name="createPerson" value="${pcrPlateVo.entity.createPerson}"/>
                                                    <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${pcrPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <c:choose>
                                                        <c:when test="${pcrPlateVo.entity.isCreate eq '1'}">${s.count}</c:when>
                                                        <c:otherwise>
                                                            <label>
                                                                <input type="checkbox" name="box">${s.count}
                                                            </label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${pcrPlateVo.entity.boardNo}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${pcrPlateVo.entity.state eq '1'}">已开始</c:when>
                                                        <c:when test="${pcrPlateVo.entity.state eq '2'}">已完成</c:when>
                                                        <c:otherwise>未开始</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value='${pcrPlateVo.entity.pcrDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                </td>
                                                <td>${pcrPlateVo.entity.testSystem}</td>
                                                <td>${pcrPlateVo.entity.pcrSystemTrace}</td>
                                                <td>${pcrPlateVo.entity.pcrSystemConstant}</td>
                                                <td>${pcrPlateVo.entity.pcrRunNum}</td>
                                                <td>${pcrPlateVo.entity.reagentBatch}</td>
                                                <td>${pcrPlateVo.entity.pcrInstrumentNum}</td>
                                                <td>${pcrPlateVo.entity.createPerson}</td>
                                                <td>
                                                    <fmt:formatDate value='${pcrPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                </td>
                                                <td>
                                                    <span class="view">
                                                        <span><img src="<%=path%>/automatedWork/img/see.png" alt=""></span>
                                                        <span>查看</span>
                                                    </span>
                                                    <span class="delete">
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
                            <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/sample/pcrPlateList">
                            <%@ include file="../../common/pagefoot.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>
                    <button class="btn btn-info btn-sm" id="createBtn">创建STR电泳表</button>
                </div>
            </div>
        </div>
        <div class="modal fade modal-primary" id="sampleModal" data-backdrop="static"
             data-keyboard="false" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div>
                                <div>
                                    <p>板名</p>
                                    <input type="text" name="boardNoModel">
                                </div>
                                <div>
                                    <p>状态</p>
                                    <input type="text" name="stateModel">
                                </div>
                                <div>
                                    <p>扩增时间</p>
                                    <input type="text" name="pcrDatetimeModel">
                                </div>
                                <div>
                                    <p>检验系统</p>
                                    <input type="text" name="testSystemModel">
                                </div>
                                <div>
                                    <p>扩增系统 (微量)</p>
                                    <input type="text" name="pcrSystemTraceModel">
                                </div>
                                <div>
                                    <p>扩增体系 (常数)</p>
                                    <input type="text" name="pcrSystemConstantModel">
                                </div>
                                <div>
                                    <p>扩增循环数</p>
                                    <input type="text" name="pcrRunNumModel">
                                </div>
                                <div>
                                    <p>试剂批次</p>
                                    <input type="text" name="reagentBatchModel">
                                </div>
                                <div>
                                    <p>仪器型号</p>
                                    <input type="text" name="pcrInstrumentNumModel">
                                </div>
                                <div>
                                    <p>创建者</p>
                                    <input type="text" name="createPersonModel">
                                </div>
                                <div>
                                    <p>创建时间</p>
                                    <input type="text" name="createDatetimeModel">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
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
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script src="${pageContext.request.contextPath}/js/check.js" ></script>
<script type="">
    $(function () {
        'use strict';

        //创建电泳表
        $("#createBtn").on("click", function (){
            if (checkCount() <= 0) {
                alert("请选择要创建的板!");
                return false;
            }
            createSyPlate();
        });

        function createSyPlate() {
            $.ajax({
                url:"<%=path%>/center/sample/spellOrCreateSyList.html",
                type:"post",
                data:JSON.stringify(syPlate()),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        location.href = "<%=path%>/center/sample/syPlateList.html";
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        function syPlate() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#pcrPlateVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.pcrPlateId = $("input[name='id']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }

        //删除扩增信息
        $(".delete", "#pcrPlateVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            var sampleArr = new Array();
            var sample = {};
            sample.id = id;
            sampleArr.push(sample);

            deletePcrPlate(sampleArr);
        });

        function deletePcrPlate(obj) {
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url:"<%=path%>/center/sample/deletePcrPlate.html",
                    type:"post",
                    data:JSON.stringify(obj),
                    dataType:"json",
                    contentType:"application/json;charset=utf-8",
                    success: function (data) {
                        if(data.success){
                            location.href = "<%=path%>/center/sample/pcrPlateList.html";
                        }else {
                            alert("操作失败!");
                        }
                    },
                    error:function(data){
                        alert("操作失败!");
                    }
                });
            }
        }

        function checkCount() {
            var checkCount = 0;
            $("tr", "#pcrPlateVoListTbody").each(function () {
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            });

            return checkCount;
        }

        //查看扩增信息
        $(".view", "#pcrPlateVoListTbody").on("click", function () {
            var boardNo = $("input[name='boardNo']", $(this).parent().parent()).val();
            var state = $("input[name='state']", $(this).parent().parent()).val();
            var pcrDatetime = $("input[name='pcrDatetime']", $(this).parent().parent()).val();
            var testSystem = $("input[name='testSystem']", $(this).parent().parent()).val();
            var pcrSystemTrace = $("input[name='pcrSystemTrace']", $(this).parent().parent()).val();
            var pcrSystemConstant = $("input[name='pcrSystemConstant']", $(this).parent().parent()).val();
            var pcrRunNum = $("input[name='pcrRunNum']", $(this).parent().parent()).val();
            var reagentBatch = $("input[name='reagentBatch']", $(this).parent().parent()).val();
            var pcrInstrumentNum = $("input[name='pcrInstrumentNum']", $(this).parent().parent()).val();
            var createPerson = $("input[name='createPerson']", $(this).parent().parent()).val();
            var createDatetime = $("input[name='createDatetime']", $(this).parent().parent()).val();

            $("div.has-error", "#sampleModal").addClass("hide");
            $("input[name='boardNoModel']", "#sampleModal").val(boardNo);
            $("input[name='stateModel']", "#sampleModal").val(state);
            $("input[name='pcrDatetimeModel']", "#sampleModal").val(pcrDatetime);
            $("input[name='testSystemModel']", "#sampleModal").val(testSystem);
            $("input[name='pcrSystemTraceModel']", "#sampleModal").val(pcrSystemTrace);
            $("input[name='pcrSystemConstantModel']", "#sampleModal").val(pcrSystemConstant);
            $("input[name='pcrRunNumModel']", "#sampleModal").val(pcrRunNum);
            $("input[name='reagentBatchModel']", "#sampleModal").val(reagentBatch);
            $("input[name='pcrInstrumentNumModel']", "#sampleModal").val(pcrInstrumentNum);
            $("input[name='createPersonModel']", "#sampleModal").val(createPerson);
            $("input[name='createDatetimeModel']", "#sampleModal").val(createDatetime);

            $("#sampleModal").modal('show');
        });

        //删除扩增信息
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要删除的板!");
                return false;
            }
            deletePcrPlate(pcrPlate());
        });

        function pcrPlate() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#pcrPlateVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.id = $("input[name='id']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }
    });
</script>
</body>

</html>