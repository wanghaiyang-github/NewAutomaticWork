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
                        <div class="container-style" style="padding: 0 !important;">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="new-title" style="border-bottom: 1px solid #ccc;">
                                        <div class="new-title-child">
                                                <span class="new-title-span">
                                                    <span></span>
                                                    <span>实验运行情况</span>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="row">
                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8"
                                             style="border-right: #ccc 1px solid;">
                                            <div class="experimentDetails-left">
                                                <div class="ed-left-nth-1">
                                                    <p><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></p>
                                                    <p>
                                                        <img src="<%=path%>/automatedWork/img/wt.png" alt="">
                                                        正在运行
                                                    </p>
                                                </div>
                                                <div>
                                                    <ul class="ed-ul-style">
                                                        <li>
                                                            <span></span>
                                                            <span>实验编号：</span>
                                                            <span id="testNoSpan"></span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>程序名称：</span>
                                                            <span id="programNameSpan"></span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>提取表名：</span>
                                                            <span id="extractPlateNameSpan"></span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>样本数量：</span>
                                                            <span class="number"></span>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div>
                                                    <ul class="ed-ul-style">
                                                        <li>
                                                            <span></span>
                                                            <span>本次任务已运行30分钟，还有1小时完成</span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>开始时间：</span>
                                                            <span id="startTimeSpan"></span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>结束时间：</span>
                                                            <span id="endTimeSpan"></span>
                                                        </li>
                                                        <li>
                                                            <span></span>
                                                            <span>实验进度：</span>
                                                                <span class="porss">
                                                                    <b></b>
                                                                </span>
                                                            <span class="porss-font">70%</span>
                                                            <span class="examine">查看详情</span>
                                                            <input type="hidden" name="idHidden" id="idHidden">
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                            <div class="experimentDetails-right">
                                                <div class="ed-righr-operating">
                                                    <div class="ed-sp-click">
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/1881.png" alt="">
                                                                启动
                                                            </span>
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/2044.png" alt="">
                                                                暂停
                                                            </span>
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/2055.png" alt="">
                                                                停止
                                                            </span>
                                                    </div>
                                                    <div class="ed-sp-click">
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/2066.png" alt="">
                                                                恢复
                                                            </span>
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/2077.png" alt="">
                                                                开灯
                                                            </span>
                                                            <span>
                                                                <img src="<%=path%>/automatedWork/img/2088.png" alt="">
                                                                关灯
                                                            </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
                                     style="width: 100%;height: 10px;background: #D6D3D6;"></div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="con-nav-top" style="padding: 0px 15px 0px 15px;border: none;">
                                        <div class="con-nav-top-left">
                                            <ul class="ul-btns margin-right-20px">
                                                <li>
                                                    任务列表
                                                </li>
                                                <li type="button" data-toggle="modal" data-target="#modalPrimary" id='addBtn'>
                                                    <span><img src="<%=path%>/automatedWork/img/1104.png" alt=""></span>
                                                    <span>创建实验</span>
                                                </li>
                                                <li type="button" id='deleteBtn'>
                                                    <span><img src="<%=path%>/automatedWork/img/tra.png" alt=""></span>
                                                    <span>批量删除</span>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="con-nav-top-right">
                                            <div class="table-btn-tag">
                                                <ul class="table-btn-tag-ul">
                                                    <c:choose>
                                                        <c:when test="${empty query.entity.state}">
                                                            <li class="active-tag">
                                                                全部
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>
                                                                全部
                                                            </li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${query.entity.state eq '0'}">
                                                            <li class="active-tag">
                                                                未开始任务
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>
                                                                未开始任务
                                                            </li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${query.entity.state eq '1'}">
                                                            <li class="active-tag">
                                                                进行中任务
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>
                                                                进行中任务
                                                            </li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${query.entity.state eq '2'}">
                                                            <li class="active-tag">
                                                                已完成任务
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>
                                                                已完成任务
                                                            </li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="container-bottom" style="padding: 0px 15px 0px 15px">
                                        <table class="table table-bordered table-hover table-striped tbale-mrleft">
                                            <thead>
                                            <tr>
                                                <th style="width: 5%;">
                                                    <label class="allLabel">
                                                        <input type="checkbox" name="allChecked" id="allChecked"/>序号
                                                    </label>
                                                </th>
                                                <th style="width: 7%;">实验号</th>
                                                <th style="width: 7%;">名称</th>
                                                <th style="width: 6%;">设备类型</th>
                                                <th style="width: 6%;">程序名称</th>
                                                <th style="width: 7%;">提取表名称</th>
                                                <th style="width: 6%;">服务名称</th>
                                                <th style="width: 5%;">状态</th>
                                                <th style="width: 6%;">当前事件</th>
                                                <th style="width: 6%;">完成事件</th>
                                                <th style="width: 7%;">已运行时间</th>
                                                <th style="width: 7%;">剩余时间</th>
                                                <th style="width: 5%;">创建者</th>
                                                <th style="width: 7%;">创建时间</th>
                                                <th style="width: 7%;">结束时间</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="testRecordVoListTbody">
                                            <c:forEach items="${testRecordVoList}" var="testRecordVo" varStatus="s">
                                                <tr>
                                                    <td>
                                                        <input type="hidden" name="id" value="${testRecordVo.entity.id}"/>
                                                        <input type="hidden" name="testNo" value="${testRecordVo.entity.testNo}"/>
                                                        <input type="hidden" name="boardNoName" value="${testRecordVo.entity.boardNoName}"/>
                                                        <input type="hidden" name="deviceType" value="${testRecordVo.entity.deviceType}"/>
                                                        <input type="hidden" name="programId" value="${testRecordVo.entity.programId}"/>
                                                        <input type="hidden" name="programName" value="${testRecordVo.programName}"/>
                                                        <input type="hidden" name="extractPlateId" value="${testRecordVo.entity.extractPlateId}"/>
                                                        <input type="hidden" name="extractPlateName" value="${testRecordVo.extractPlateName}"/>
                                                        <input type="hidden" name="count" value="${testRecordVo.count}"/>
                                                        <input type="hidden" name="serverId" value="${testRecordVo.entity.serverId}"/>
                                                        <input type="hidden" name="createPerson" value="${testRecordVo.entity.createPerson}"/>
                                                        <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${testRecordVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                        <input type="hidden" name="startDatetime" value="<fmt:formatDate value='${testRecordVo.entity.startDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                        <input type="hidden" name="endDatetime" value="<fmt:formatDate value='${testRecordVo.entity.endDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                        <c:choose>
                                                            <c:when test="${testRecordVo.entity.isCreate eq '1'}">${s.count}</c:when>
                                                            <c:otherwise>
                                                                <label class="labelBox">
                                                                    <input type="checkbox" name="box">${s.count}
                                                                </label>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>${testRecordVo.entity.testNo}</td>
                                                    <td>${testRecordVo.boardNoName}</td>
                                                    <td>${testRecordVo.entity.deviceType}</td>
                                                    <td>${testRecordVo.programName}</td>
                                                    <td>${testRecordVo.extractPlateName}</td>
                                                    <td>${testRecordVo.serverName}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${testRecordVo.entity.state eq '1'}">已开始</c:when>
                                                            <c:when test="${testRecordVo.entity.state eq '2'}">已完成</c:when>
                                                            <c:otherwise>未开始</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>${testRecordVo.entity.createPerson}</td>
                                                    <td>
                                                        <fmt:formatDate value='${testRecordVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value='${testRecordVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                    </td>
                                                    <td>
                                                       <%-- <span>
                                                            <span><img src="<%=path%>/automatedWork/img/bj001.png" alt=""></span>
                                                            <span>编辑</span>
                                                        </span>--%>
                                                        <span class="view">
                                                            <span><img src="<%=path%>/automatedWork/img/see.png" alt=""></span>
                                                            <span>查看</span>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/sample/sampleInfoList">
                                <%@ include file="../../common/pagefoot.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="../../common/foot.jsp" %>
        </div>
        <div class="modal fade modal-primary" id="sampleModal" data-backdrop="static"
             data-keyboard="false" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>
                            创建实验
                                <span>
                                    <span><img src="<%=path%>/automatedWork/img/1877.png" alt="" style="width: 24px; height: 24px;margin-right: 3px;"></span>
                                    <span type="button" data-toggle="modal" data-target="#extract">查看提取单</span>
                                </span>
                        </div>
                    </div>
                    <div class="modal-body" style="height: 500px;">
                        <form>
                            <div>
                                <div>
                                    <div class="name-khc">
                                        <p>名称</p>
                                        <p>
                                            <img src="<%=path%>/automatedWork/img/khc.png" alt="">
                                            <span>年-月-日-第几板</span>
                                        </p>
                                        <select name="syPlateIdModel">
                                            <c:forEach items="${syPlateVoList}" var="list" varStatus="s">
                                                <option value="${list.entity.id}">${list.entity.boardNo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div>
                                    <p>设备类型</p>
                                    <select name="deviceTypeModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${deviceTypeList}" var="list" varStatus="s">
                                            <option value="${list.dictName}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <p>提取表名称</p>
                                    <select name="extractPlateIdModel">
                                        <option value=''>请选择</option>
                                    </select>
                                </div>
                                <div>
                                    <p>程序名称</p>
                                    <select name="programIdModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${programNameList}" var="list" varStatus="s">
                                            <option value="${list.dictName}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <p>服务名称</p>
                                    <select name="serverIdModel">
                                        <%--<option value=''>请选择</option>--%>
                                        <c:forEach items="${serverInfoList}" var="list" varStatus="s">
                                            <option value="${list.id}">${list.serverName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <p>创建者</p>
                                    <input type="text" name="createPersonModel" >
                                </div>
                                <div>
                                    <p>创建时间</p>
                                    <input name="createDatetimeModel" type="text">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="saveBtn">保 存</button>
                            <button data-dismiss="modal" class="btn btn-info btn-sm" type="button">关 闭</button>
                            <input type="hidden" name="idModel" value=""/>
                            <input type="hidden" name="sampleOperateType" value=""/>
                            <input type="hidden" name="sampleTableRownum" value=""/>
                        </div>
                    <%--</div>--%>
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

        // 实验记录管理 顶部导航 全部  未开始 进行中 已完成
        $(".table-btn-tag-ul li").click(function () {
            $(this).addClass('active-tag').siblings().removeClass('active-tag');
            if ($(this).index() == 0) {
                //获取全部列表
                getList("");
            } else if ($(this).index() == 1) {
                //获取未开始列表
                getList("0");
            } else if ($(this).index() == 2) {
                //获取进行中列表
                getList("1");
            } else if ($(this).index() == 3) {
                //获取已完成列表
                getList("2");
            }
        })

        $("select[name='syPlateIdModel']", "#sampleModal").change(function(){
            var syPlateId = $("select[name='syPlateIdModel']", "#sampleModal").val();
            $.ajax({
                url:"<%=path%>/center/sample/getTestRecord.html?syPlateId=" + syPlateId,
                type:"post",
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        var testRecordVo = data.testRecordVo;
                        $("select[name='deviceTypeModel']", "#sampleModal").val(testRecordVo.deviceType);
                        var str = "<option value='"+testRecordVo.extractPlateId+"' selected>"+testRecordVo.extractPlateName+"</option>";
                        $("select[name='extractPlateIdModel']", "#sampleModal").append(str);
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        });

        //跳转添加检材页面
        function getList (obj) {
            location.href = "<%=path%>/center/sample/testRecordList.html?entity.state=" + obj;
        };

        function testRecord() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#testRecordVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.id = $("input[name='id']", $(this)).val();
                    sample.boardNoName = $("input[name='boardNoName']", $(this)).val();
                    sample.deviceType = $("input[name='deviceType']", $(this)).val();
                    sample.programId =  $("input[name='programId']", $(this)).val();
                    sample.extractPlateId =  $("input[name='extractPlateId']", $(this)).val();
                    sample.serverId =  $("input[name='serverId']", $(this)).val();
                    sample.createPerson = $("input[name='createPerson']", $(this)).val();
                    sample.createDatetime = $("input[name='createDatetime']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }

        function checkCount() {
            var checkCount = 0;
            $("tr", "#testRecordVoListTbody").each(function () {
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            });

            return checkCount;
        }

        //查看实验信息
        $(".view", "#testRecordVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            var testNo = $("input[name='testNo']", $(this).parent().parent()).val();
            var programName = $("input[name='programName']", $(this).parent().parent()).val();
            var extractPlateName = $("input[name='extractPlateName']", $(this).parent().parent()).val();
            var count = $("input[name='count']", $(this).parent().parent()).val();
            var startDatetime = $("input[name='startDatetime']", $(this).parent().parent()).val();
            var endDatetime = $("input[name='endDatetime']", $(this).parent().parent()).val();

            $("#idHidden").text(id);
            $("#testNoSpan").text(testNo);
            $("#programNameSpan").text(programName);
            $("#extractPlateNameSpan").text(extractPlateName);
            $(".number").text(count);
            $("#startTimeSpan").text(startDatetime);
            $("#endTimeSpan").text(endDatetime);
        });

        //删除实验信息
        $(".delete", "#testRecordVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            $("#identification").val("1");
            $("#param1").val(id);
            $().confirmTestRecordOperation("确认删除吗?");
        });

        //删除实验信息
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                $("#modelContent").text("请选择要删除的实验记录！");
                $("#msg-model").modal();
                return false;
            }
            $("#identification").val("2");
            $().confirmTestRecordOperation("确认删除吗?");
        });

        $.fn.confirmTestRecordOperation = function (obj) {
            $("#confirmContent").text(obj);
            $("#confirm-model").modal();
        }

        $("#confirmBtn").on("click", function (){
            var identification = $("#identification").val();
            if (identification == "1") {
                var sampleArr = new Array();
                var sample = {};
                sample.id = $("#param1").val();
                sampleArr.push(sample);

                deleteTestRecord(sampleArr);
            }else if (identification == "2") {
                deleteTestRecord(testRecord());
            }
        });

        function deleteTestRecord(obj) {
            $.ajax({
                url:"<%=path%>/center/sample/deleteTestRecord.html",
                type:"post",
                data:JSON.stringify(obj),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        location.href = "<%=path%>/center/sample/testRecordList.html";
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        //查看创建详情
        $("#viewCreateBtn").on("click", function () {
            viewCreate();
        });

        function viewCreate() {
            if (checkCount() <= 0) {
                alert("请选择要查看的板!");
                return false;
            }
            if (checkCount() > 1) {
                alert("只能查看一个板！");
                return false;
            }
            var sampleArr = new Array();
            sampleArr = testRecord();
            sampleRow(sampleArr[0], "edit");
        }

        //查看实验详情
        $("#viewBtn").on("click", function () {
            if (checkCount() <= 0) {
                alert("请选择要查看的板!");
                return false;
            }
            if (checkCount() > 1) {
                alert("只能查看一个实验板！");
                return false;
            }
            var sampleArr = new Array();
            sampleArr = testRecord();
            location.href = "<%=path%>/center/sample/viewTestRecord.html?id=" + sampleArr[0].id;
        });

        //创建实验信息
        $("#addBtn").on("click", function () {
            addSampleRow("add");
        });

        function addSampleRow(obj) {
            var sample = {};
            sample.id = "";
            sample.syPlateId = "";
            sample.deviceType = "";
            sample.extractPlateId = "";
            sample.programId = "";
            sample.serverId = "";
            sample.createPerson = $("input[name='loginName']").val();
            sample.createDatetime = formatDateTime;
            sampleRow(sample, "add");
        }

        //获取当前日期转换成年月日
        function nowDate() {
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            return year + "-" + month + "-" + day;
        }

        var formatDateTime = function () {
            var date = new Date();
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h=h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            minute = minute < 10 ? ('0' + minute) : minute;
            var second=date.getSeconds();
            second=second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
        };

        function sampleRow(sample, operateType) {
            $("div.has-error", "#sampleModal").addClass("hide");
            if (sample.syPlateId == "") {
                $("select[name='syPlateIdModel']", "#sampleModal").prop('selectedIndex', 0);
            } else {
                $("select[name='syPlateIdModel']", "#sampleModal").val(sample.syPlateId);
            }
            if (sample.deviceType == "") {
                $("select[name='deviceTypeModel']", "#sampleModal").prop('selectedIndex', 0);
            } else {
                $("select[name='deviceTypeModel']", "#sampleModal").val(sample.deviceType);
            }
            if (sample.extractPlateId == "") {
                $("select[name='extractPlateIdModel']", "#sampleModal").prop('selectedIndex', 0);
            } else {
                $("select[name='extractPlateIdModel']", "#sampleModal").val(sample.extractPlateId);
            }
            if (sample.programId == "") {
                $("select[name='programIdModel']", "#sampleModal").prop('selectedIndex', 0);
            } else {
                $("select[name='programIdModel']", "#sampleModal").val(sample.programId);
            }
            if (sample.serverId == "") {
                $("select[name='serverIdModel']", "#sampleModal").prop('selectedIndex', 0);
            } else {
                $("select[name='serverIdModel']", "#sampleModal").val(sample.serverId);
            }
            $("input[name='createPersonModel']", "#sampleModal").val(sample.createPerson);
            $("input[name='createDatetimeModel']", "#sampleModal").val(sample.createDatetime);
            $("input[name='idModel']", "#sampleModal").val(sample.id);

            var syPlateId = $("select[name='syPlateIdModel']", "#sampleModal").val();
            if(syPlateId != undefined && syPlateId != null && syPlateId != ""){
                $("select[name='syPlateIdModel']", "#sampleModal").trigger("change");
            }

            $("#sampleModal").modal('show');
        }

        //保存单个板信息
        $("#saveBtn").on("click", function () {
            /*if(!checkInputValids()) {
             return;
             }*/

            saveTestRecord(getParams());

        });

        function saveTestRecord(params) {
            $.ajax({
                url:"<%=path%>/center/sample/saveTestRecord.html",
                type:"post",
                data:JSON.stringify(params),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        location.href = "<%=path%>/center/sample/testRecordList.html";
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        function getParams(){
            var sampleArr = new Array();
            var testRecord = {};

            testRecord.id = $("input[name='idModel']").val();
            testRecord.syPlateId = $("select[name='syPlateIdModel'] :checked").val();
            testRecord.deviceType = $("select[name='deviceTypeModel'] :checked").val();
            testRecord.extractPlateId = $("select[name='extractPlateIdModel'] :checked").val();
            testRecord.programId = $("select[name='programIdModel'] :checked").val();
            testRecord.serverId = $("select[name='serverIdModel'] :checked").val();
            testRecord.createPerson = $("input[name='createPersonModel']").val();
            testRecord.createDatetime = $("input[name='createDatetimeModel']").val();

            sampleArr.push(testRecord);

            return sampleArr;
        }

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

        laydate.render({
            elem: '.form_datetime', //指定元素
            showBottom: false
        });
    });
</script>
</body>

</html>