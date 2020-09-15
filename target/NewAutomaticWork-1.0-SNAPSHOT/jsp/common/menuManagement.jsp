<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-left">
    <div class="side-menu sidebar-inverse">
        <ul class="side-menu-Ulitem">
            <c:forEach items="${rootPermissionList}" var="root" varStatus="rp">
                <li>
                    <a href="<%=path%>/${root.permissionLink}" style="width: 100%;height: 100%;display: flex;align-items: center;justify-content: center;">
                        <div>
                            <p class="${root.otherStyle}"><img src="<%=path%>/automatedWork/${root.iconStyle}" alt=""></p>
                            <p>${root.permissionName}</p>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
