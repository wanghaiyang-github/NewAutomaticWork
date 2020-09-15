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
        </div>
    </div>
</div>
<%@ include file="../common/newScript.jsp" %>
<script type="">
    $(function () {
        'use strict';

        onClick(); //自动触发事件

        function onClick(){
            $("li", ".side-menu-Ulitem").each(function () {
                var index = $(this).index(); //获取索引下标 也从0开始
                if (index == 0) {
                    location.href = "<%=path%>/center/sample/sampleTableList.html";
                }
            });
        }

    });
</script>
</body>

</html>