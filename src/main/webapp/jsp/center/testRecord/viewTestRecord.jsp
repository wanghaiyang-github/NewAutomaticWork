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
                                <div class="con-nav-top" style="margin-bottom: 0px;">
                                    <div class="con-nav-top-left">
                                            <span>
                                                <span></span>
                                                <span>实验运行情况</span>
                                            </span>
                                    </div>
                                    <div class="con-nav-top-right">

                                    </div>
                                </div>
                                <div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                            <div class="details-left">
                                                <div>
                                                    <div>
                                                        <p><img src="<%=path%>/automatedWork/img/stop.png" alt=""></p>
                                                        <p>结束</p>
                                                    </div>
                                                </div>
                                                <ul>
                                                    <li>
                                                        <p><img src="<%=path%>/automatedWork/img/2020-1.png" alt=""></p>
                                                        <p>暂停</p>
                                                    </li>
                                                    <li>
                                                        <p><img src="<%=path%>/automatedWork/img/2020-2.png" alt=""></p>
                                                        <p>恢复</p>
                                                    </li>
                                                    <li>
                                                        <p><img src="<%=path%>/automatedWork/img/2020-3.png" alt=""></p>
                                                        <p>停止</p>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                            <div class="progress-box">
                                                <div>
                                                    <div>70%</div>
                                                    <div>
                                                        <p></p>
                                                    </div>
                                                </div>
                                                <div>
                                                    <div>
                                                        <label>实验名称</label>
                                                        <input type="text" name="" id=""  value="123" disabled="disabled">
                                                    </div>
                                                    <div>
                                                        <label>提取表名</label>
                                                        <input type="text" name="" id="" value="123" disabled="disabled">
                                                    </div>
                                                    <div>
                                                        <label>实验名称</label>
                                                        <input type="text" name="" id="" value="123" disabled="disabled">
                                                    </div>
                                                </div>
                                                <div>
                                                    <div>
                                                        <label for="">当前事件</label>
                                                        <input type="text" name="" id="" value="123" disabled="disabled">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                            <div class="times">
                                                <div>
                                                    <div class="time-style">
                                                        <span>
                                                            <span><img src="<%=path%>/automatedWork/img/time.png" alt=""></span>
                                                            <span>运行时间</span>
                                                        </span>
                                                        <span>
                                                            <span>1</span>
                                                            <span>小时</span>
                                                        </span>
                                                        <span>
                                                            <span>23</span>
                                                            <span>分</span>
                                                        </span>
                                                    </div>
                                                    <div  class="time-style">
                                                        <span>
                                                            <span><img src="<%=path%>/automatedWork/img/time.png" alt=""></span>
                                                            <span>剩余时间</span>
                                                        </span>
                                                        <span>
                                                            <span>1</span>
                                                            <span>小时</span>
                                                        </span>
                                                        <span>
                                                            <span>23</span>
                                                            <span>分</span>
                                                        </span>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 10px;background:#DAD7DB;"></div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="container-bottom">
                                    <div class="con-nav-top">
                                        <div class="con-nav-top-left">
                                                <span>
                                                    <span style="background: #FFA700 !important;"></span>
                                                    <span style="color: #FFA700 !important;">实验过程</span>
                                                </span>
                                        </div>
                                        <div class="con-nav-top-right">

                                        </div>
                                    </div>
                                    <div class="img-style">
                                        <ul>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                            <li><img src="<%=path%>/automatedWork/img/2020-18.jpg" alt=""></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <div>
                    <button class="btn btn-info btn-sm back">返回上一步</button>
                </div>
            </div>
        </div>
</div>
<!-- BEGIN JS -->
<%@ include file="../../common/newScript.jsp" %>
<script type="">
    $(function () {
        'use strict';

        //跳转添加检材页面
        $(".back").click(function () {
            location.href = "<%=path%>/center/sample/testRecordList.html";
        })

    });
</script>
</body>

</html>