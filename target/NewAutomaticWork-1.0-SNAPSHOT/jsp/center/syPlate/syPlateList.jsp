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
                                            <span>电泳管理</span>
                                        </span>
                                    </div>
                                    <div class="con-nav-top-right">
                                        <span>
                                            <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                            <span style="color: #006CFF !important;">导出STR电泳样本表</span>
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
                                            <th>检测时间</th>
                                            <th>状态</th>
                                            <th>测序仪型号</th>
                                            <th>分子量标记</th>
                                            <th>混合比例</th>
                                            <th>提取方法</th>
                                            <th>环境温湿度</th>
                                            <th>变性条件</th>
                                            <th>创建者</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="syPlateVoListTbody">
                                        <c:forEach items="${syPlateVoList}" var="syPlateVo" varStatus="s">
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="id" value="${syPlateVo.entity.id}"/>
                                                    <input type="hidden" name="boardNo" value="${syPlateVo.entity.boardNo}"/>
                                                    <input type="hidden" name="testDatetime" value="<fmt:formatDate value='${syPlateVo.entity.testDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <input type="hidden" name="state" value="${syPlateVo.entity.state}"/>
                                                    <input type="hidden" name="firstInstrumentNum" value="${syPlateVo.entity.firstInstrumentNum}"/>
                                                    <input type="hidden" name="molecularWeightMarker" value="${syPlateVo.entity.molecularWeightMarker}"/>
                                                    <input type="hidden" name="mixingRatio" value="${syPlateVo.entity.mixingRatio}"/>
                                                    <input type="hidden" name="sySystem" value="${syPlateVo.entity.sySystem}"/>
                                                    <input type="hidden" name="environmentTemperature" value="${syPlateVo.entity.environmentTemperature}"/>
                                                    <input type="hidden" name="denaturationCondition" value="${syPlateVo.entity.denaturationCondition}"/>
                                                    <input type="hidden" name="createPerson" value="${syPlateVo.entity.createPerson}"/>
                                                    <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${syPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <c:choose>
                                                        <c:when test="${syPlateVo.entity.isCreate eq '1'}">${s.count}</c:when>
                                                        <c:otherwise>
                                                            <label>
                                                                <input type="checkbox" name="box">${s.count}
                                                            </label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${syPlateVo.entity.boardNo}</td>
                                                <td>
                                                    <fmt:formatDate value='${syPlateVo.entity.testDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${syPlateVo.entity.state eq '1'}">已开始</c:when>
                                                        <c:when test="${syPlateVo.entity.state eq '2'}">已完成</c:when>
                                                        <c:otherwise>未开始</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${syPlateVo.entity.firstInstrumentNum}</td>
                                                <td>${syPlateVo.entity.molecularWeightMarker}</td>
                                                <td>${syPlateVo.entity.mixingRatio}</td>
                                                <td>${syPlateVo.entity.sySystem}</td>
                                                <td>${syPlateVo.entity.environmentTemperature}</td>
                                                <td>${syPlateVo.entity.denaturationCondition}</td>
                                                <td>${syPlateVo.entity.createPerson}</td>
                                                <td>
                                                    <fmt:formatDate value='${syPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
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
                            <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/sample/syPlateList">
                            <%@ include file="../../common/pagefoot.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>
                    <%--<button class="btn btn-info btn-sm" id="saveBtn">保 存</button>--%>
                    <%--<button class="btn btn-info btn-sm">保存并开始实验</button>--%>
                    <button class="btn btn-info btn-sm "id="saveBtn">导出电泳上样文件</button>
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
                                    <p>检测时间</p>
                                    <input type="text" name="testDatetimeModel">
                                </div>
                                <div>
                                    <p>状态</p>
                                    <input type="text" name="stateModel">
                                </div>
                                <div>
                                    <p>测序仪型号</p>
                                    <input type="text" name="firstInstrumentNumModel">
                                </div>
                                <div>
                                    <p>分子量标记</p>
                                    <input type="text" name="molecularWeightMarkerModel">
                                </div>
                                <div>
                                    <p>混合比例</p>
                                    <input type="text" name="mixingRatioModel">
                                </div>
                                <div>
                                    <p>上样体系</p>
                                    <input type="text" name="sySystemModel">
                                </div>
                                <div>
                                    <p>环境温湿度</p>
                                    <input type="text" name="environmentTemperatureModel">
                                </div>
                                <div>
                                    <p>变性条件</p>
                                    <input type="text" name="denaturationConditionModel">
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

        //删除扩增信息
        $(".delete", "#syPlateVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            var sampleArr = new Array();
            var sample = {};
            sample.id = id;
            sampleArr.push(sample);

            deleteSyPlate(sampleArr);
        });

        function deleteSyPlate(obj) {
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url:"<%=path%>/center/sample/deleteSyPlate.html",
                    type:"post",
                    data:JSON.stringify(obj),
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
        }

        function checkCount() {
            var checkCount = 0;
            $("tr", "#syPlateVoListTbody").each(function () {
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            });

            return checkCount;
        }

        //查看电泳信息
        $(".view", "#syPlateVoListTbody").on("click", function () {
            var boardNo = $("input[name='boardNo']", $(this).parent().parent()).val();
            var testDatetime = $("input[name='testDatetime']", $(this).parent().parent()).val();
            var state = $("input[name='state']", $(this).parent().parent()).val();
            var firstInstrumentNum = $("input[name='firstInstrumentNum']", $(this).parent().parent()).val();
            var molecularWeightMarker = $("input[name='molecularWeightMarker']", $(this).parent().parent()).val();
            var mixingRatio = $("input[name='mixingRatio']", $(this).parent().parent()).val();
            var sySystem = $("input[name='sySystem']", $(this).parent().parent()).val();
            var environmentTemperature = $("input[name='environmentTemperature']", $(this).parent().parent()).val();
            var denaturationCondition = $("input[name='denaturationCondition']", $(this).parent().parent()).val();
            var createPerson = $("input[name='createPerson']", $(this).parent().parent()).val();
            var createDatetime = $("input[name='createDatetime']", $(this).parent().parent()).val();

            $("div.has-error", "#sampleModal").addClass("hide");

            $("input[name='boardNoModel']", "#sampleModal").val(boardNo);
            $("input[name='testDatetimeModel']", "#sampleModal").val(testDatetime);
            $("input[name='stateModel']", "#sampleModal").val(state);
            $("input[name='firstInstrumentNumModel']", "#sampleModal").val(firstInstrumentNum);
            $("input[name='molecularWeightMarkerModel']", "#sampleModal").val(molecularWeightMarker);
            $("input[name='mixingRatioModel']", "#sampleModal").val(mixingRatio);
            $("input[name='sySystemModel']", "#sampleModal").val(sySystem);
            $("input[name='environmentTemperatureModel']", "#sampleModal").val(environmentTemperature);
            $("input[name='denaturationConditionModel']", "#sampleModal").val(denaturationCondition);
            $("input[name='createPersonModel']", "#sampleModal").val(createPerson);
            $("input[name='createDatetimeModel']", "#sampleModal").val(createDatetime);

            $("#sampleModal").modal('show');
        });

        //保存电泳信息
        $("#saveBtn").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要保存的板!");
                return false;
            }
            saveSyPlate(syPlate());
        });

        function saveSyPlate(params) {
            $.ajax({
                url:"<%=path%>/center/sample/saveSyPlate.html",
                type:"post",
                data:JSON.stringify(params),
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

        //删除电泳信息
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要删除的板!");
                return false;
            }
            deleteSyPlate(syPlate());
        });

        function syPlate() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#syPlateVoListTbody").each(function () {
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