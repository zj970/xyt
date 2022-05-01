<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>校园通</title>
    <%@include file="common.jsp"%>
    <script>
        layui.use('layer');
        function logout() {
            layer.confirm('是否确认退出?',{icon:3,title:'提示'},function (index) {
                location.href = "${path}/logout";
            });
        }
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">校园通</div>

    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item">
        <a href="${path}/main/home" target="middle"><i class="layui-icon" data-icon="&#xe68e;">&#xe68e;&nbsp;&nbsp;</i><cite>主页</cite></a>
        </li>

    </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
<%--                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                    <img src="${path}/images/320755dbe6849f1c26308e67c5eb2b67.jpeg" class="layui-nav-img">
                    ${sessionScope.login_user}
                </a>
                <dl class="layui-nav-child">
                    <%--   target="middle"在指定的框架中打开被链接文档。 --%>
                    <dd><a href="${path}/easUser/basicInformationIndex" target="middle">基本资料</a></dd>
                    <dd><a href="${path}/easUser/passwordRestIndex" target="middle">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:void(0)" onclick="logout();">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <c:forEach items="${sessionScope.user_pers}" var="parent">
                    <c:if test="${parent.parentid == null}">
                        <li class="layui-nav-item layui-nav-itemed">
                            <a class="" href="javascript:;">${parent.text}</a>
                            <dl class="layui-nav-child">
                                <c:forEach items="${sessionScope.user_pers}" var="child">
                                    <c:if test="${child.parentid != null && child.parentid == parent.id}">
                                    <dd><a href="${path}/${child.url}" target="middle">${child.text}</a></dd>
                                    </c:if>
                                </c:forEach>
                            </dl>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="${path}/main/home" name="middle" width="100%" height="100%" frameborder="0"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
       欢迎来到校园通教务管理系统，如有疑问请联系系统维护人员 - qq - 3060529292 - 底部固定区域
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
