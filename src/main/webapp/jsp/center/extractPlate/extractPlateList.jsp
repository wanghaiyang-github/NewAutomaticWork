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
                                            <span>提取管理</span>
                                        </span>
                                        <span type="button" id='exportCSV'>
                                            <span style="background-color: #fff !important;"><img src="<%=path%>/automatedWork/img/dj.png" alt=""></span>
                                            <span>导出CSV</span>
                                        </span>
                                        <span type="button" id='exportSampleFile'>
                                            <span><img src="<%=path%>/automatedWork/img/dj-2.png" alt=""></span>
                                            <span>快速导出上样文件</span>
                                        </span>
                                    </div>
                                    <div class="con-nav-top-right">
                                        <span type="button" id="exportExtractSampleTable">
                                            <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                            <span style="color: #006CFF !important;">提取DNA样本表</span>
                                        </span>
                                        <span type="button" id="exportExtractSampleRecord">
                                            <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                            <span style="color: #006CFF !important;">导出提取样本记录表</span>
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
                                            <th>孔数</th>
                                            <th>试剂盒</th>
                                            <th>设备类型</th>
                                            <th>提取方法</th>
                                            <th>提取时间</th>
                                            <th>扩增表名</th>
                                            <th>创建者</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="extractPlateVoListTbody">
                                        <c:forEach items="${extractPlateVoList}" var="extractPlateVo" varStatus="s">
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="id" value="${extractPlateVo.entity.id}"/>
                                                    <input type="hidden" name="boardNo" value="${extractPlateVo.entity.boardNo}"/>
                                                    <input type="hidden" name="state" value="${extractPlateVo.entity.state}"/>
                                                    <input type="hidden" name="holeNum" value="${extractPlateVo.entity.holeNum}"/>
                                                    <input type="hidden" name="kitName" value="${extractPlateVo.entity.kitName}"/>
                                                    <input type="hidden" name="deviceType" value="${extractPlateVo.entity.deviceType}"/>
                                                    <input type="hidden" name="extractMethod" value="${extractPlateVo.entity.extractMethod}"/>
                                                    <input type="hidden" name="extractDatetime" value="<fmt:formatDate value='${extractPlateVo.entity.extractDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <input type="hidden" name="pcrPlateName" value="${extractPlateVo.entity.pcrPlateName}"/>
                                                    <input type="hidden" name="createPerson" value="${extractPlateVo.entity.createPerson}"/>
                                                    <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${extractPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                    <c:choose>
                                                        <c:when test="${extractPlateVo.entity.isCreate eq '1'}">${s.count}</c:when>
                                                        <c:otherwise>
                                                            <label>
                                                                <input type="checkbox" name="box">${s.count}
                                                            </label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${extractPlateVo.entity.boardNo}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${extractPlateVo.entity.state eq '1'}">已开始</c:when>
                                                        <c:when test="${extractPlateVo.entity.state eq '2'}">已完成</c:when>
                                                        <c:otherwise>未开始</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${extractPlateVo.entity.holeNum}</td>
                                                <td>${extractPlateVo.entity.kitName}</td>
                                                <td>${extractPlateVo.entity.deviceType}</td>
                                                <td>${extractPlateVo.entity.extractMethod}</td>
                                                <td>
                                                    <fmt:formatDate value='${extractPlateVo.entity.extractDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                </td>
                                                <td>${extractPlateVo.entity.pcrPlateName}</td>
                                                <td>${extractPlateVo.entity.createPerson}</td>
                                                <td>
                                                    <fmt:formatDate value='${extractPlateVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
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
                            <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/sample/extractPlateList">
                            <%@ include file="../../common/pagefoot.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>
                    <button class="btn btn-info btn-sm" id="createBtn">创建PCR扩展表</button>
                    <button class="btn btn-info btn-sm" id="spellCreateBtn">拼板并创建RCR扩展表</button>
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
                                <p>孔数</p>
                                <input type="text" name="holeNumModel">
                            </div>
                            <div>
                                <p>试剂盒</p>
                                <input type="text" name="kitNameModel">
                            </div>
                            <div>
                                <p>设备类型</p>
                                <input type="text" name="deviceTypeModel">
                            </div>
                            <div>
                                <p>提取方法</p>
                                <input type="text" name="extractMethodModel">
                            </div>
                            <div>
                                <p>提取时间</p>
                                <input type="text" name="extractDatetimeModel">
                            </div>
                            <div>
                                <p>扩展表名</p>
                                <input type="text" name="pcrPlateNameModel">
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

        //创建扩增表
        $("#createBtn").on("click", function (){
            if (checkCount() <= 0) {
                alert("请选择要创建的板!");
                return false;
            }
            createPcrPlate("NO");
        });
        //拼板并创建扩增表
        $("#spellCreateBtn").on("click", function (){
            if (checkCount() <= 0) {
                alert("请选择要拼的板!");
                return false;
            }
            createPcrPlate("YES");
        });

        function createPcrPlate(obj) {
            $.ajax({
                url:"<%=path%>/center/sample/spellOrCreatePcrList.html?isSpell=" + obj,
                type:"post",
                data:JSON.stringify(pcrPlate()),
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

        function pcrPlate() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#extractPlateVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.extractPlateId = $("input[name='id']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }

        //查看提取信息
        $(".view", "#extractPlateVoListTbody").on("click", function () {
            var boardNo = $("input[name='boardNo']", $(this).parent().parent()).val();
            var state = $("input[name='state']", $(this).parent().parent()).val();
            var holeNum = $("input[name='holeNum']", $(this).parent().parent()).val();
            var kitName = $("input[name='kitName']", $(this).parent().parent()).val();
            var deviceType = $("input[name='deviceType']", $(this).parent().parent()).val();
            var extractMethod = $("input[name='extractMethod']", $(this).parent().parent()).val();
            var extractDatetime = $("input[name='extractDatetime']", $(this).parent().parent()).val();
            var pcrPlateName = $("input[name='pcrPlateName']", $(this).parent().parent()).val();
            var createPerson = $("input[name='createPerson']", $(this).parent().parent()).val();
            var createDatetime = $("input[name='createDatetime']", $(this).parent().parent()).val();

            $("div.has-error", "#sampleModal").addClass("hide");
            $("input[name='boardNoModel']", "#sampleModal").val(boardNo);
            $("input[name='stateModel']", "#sampleModal").val(state);
            $("input[name='holeNumModel']", "#sampleModal").val(holeNum);
            $("input[name='kitNameModel']", "#sampleModal").val(kitName);
            $("input[name='deviceTypeModel']", "#sampleModal").val(deviceType);
            $("input[name='extractMethodModel']", "#sampleModal").val(extractMethod);
            $("input[name='extractDatetimeModel']", "#sampleModal").val(extractDatetime);
            $("input[name='pcrPlateNameModel']", "#sampleModal").val(pcrPlateName);
            $("input[name='createPersonModel']", "#sampleModal").val(createPerson);
            $("input[name='createDatetimeModel']", "#sampleModal").val(createDatetime);

            $("#sampleModal").modal('show');
        });

        //删除提取信息
        $(".delete", "#extractPlateVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            var sampleArr = new Array();
            var sample = {};
            sample.id = id;
            sampleArr.push(sample);

            deleteExtractPlate(sampleArr);
        });

        function deleteExtractPlate(obj) {
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url:"<%=path%>/center/sample/deleteExtractPlate.html",
                    type:"post",
                    data:JSON.stringify(obj),
                    dataType:"json",
                    contentType:"application/json;charset=utf-8",
                    success: function (data) {
                        if(data.success){
                            location.href = "<%=path%>/center/sample/extractPlateList.html";
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

        function extractPlate() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#extractPlateVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.id = $("input[name='id']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }

        function checkCount() {
            var checkCount = 0;
            $("tr", "#extractPlateVoListTbody").each(function () {
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            });

            return checkCount;
        }
        //导出CSV文件
        $("#exportCSV").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要导出的板!");
                return false;
            }
            exportExtract("csv");
        });
        //导出上样文件
        $("#exportSampleFile").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要导出的板!");
                return false;
            }
            exportExtract("sampleFile");
        });
        //导出DNA提取样本表
        $("#exportExtractSampleTable").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要导出的板!");
                return false;
            }
            exportExtract("table");
        });
        //导出提取样本记录表
        $("#exportExtractSampleRecord").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要导出的板!");
                return false;
            }
            exportExtract("record");
        });

        function exportExtract(obj) {
            var record;
            //提取
            var recordExtractArr = new Array();
            recordExtractArr = extractPlate();

            var listLength = recordExtractArr.length;
            if (listLength > 0) {
                var extractId = "";
                for (var i = 0; i < listLength; i++) {
                    extractId = recordExtractArr[i].id;
                    if (extractId !== null || extractId !== 'undefined' || extractId !== '') {
                        if (obj == "csv") {
                            var url = "<%=path%>/center/sample/exportCSVFile.html?entity.extractPlateId=" + extractId;
                        }else if (obj == "sampleFile") {
                            var url = "<%=path%>/center/sample/exportSampleFile.html?entity.extractPlateId=" + extractId;
                        }else if (obj == "table") {
                            var url = "<%=path%>/center/sample/exportExtractSampleTable.html?entity.extractPlateId=" + extractId;
                        }else if (obj == "record"){
                            var url = "<%=path%>/center/sample/exportExtractSampleRecord.html?entity.extractPlateId=" + extractId;
                        }
                        generateRecord(url);
                    }
                }
            }
        }

        function generateRecord(url) {
            var fileFrame = document.createElement("iframe");
            fileFrame.src = url;//文件路径
            fileFrame.style.display = "none";
            document.body.appendChild(fileFrame);
        }

        //删除样本板
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要删除的板!");
                return false;
            }
            deleteExtractPlate(extractPlate());
        });

        //判断必填项
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

            return true;
        }

    });
</script>
</body>

</html>