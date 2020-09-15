<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="herder-nav">
    <div class="herder-nav-container">
        <div class="herder-left-con">
            <div class="herder-img">
                <img src="<%=path%>/automatedWork/img/bazl.png" alt="">
            </div>
            <div>
                <p>全 自 动 工 作 站 检 材 处 理 平 台</p>
                <p>Fully automatic material inspection and processing platform</p>
            </div>
        </div>
        <div class="dispaly-flex">
            <div>
                <span class="">
                    <img src="<%=path%>/automatedWork/img/tx001.png" alt=""></span>
                <span class="" style="margin-left:5px;display: inline-block;width:50px;height:50px; line-height: 50px">
                                <shiro:user><shiro:principal property="loginName"/></shiro:user>
                <input type="hidden" name="loginName" value="<shiro:user><shiro:principal property="loginName"/></shiro:user>">
                </span>
            </div>
            <div class="user-item" title="退出登录">
                <span class=""><img src="<%=path%>/automatedWork/img/tc001.png" alt=""></span>
                <span class="" style="margin-left:5px;display: inline-block;width:50px;height:50px; line-height: 50px">
                    退出
                </span>
            </div>
        </div>
    </div>
</div>