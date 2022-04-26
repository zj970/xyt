<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>修改密码</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
</head>
<body>
<form id="passwordRest" class="layui-form" action="" lay-filter="pwdForm"  style="padding:100px 150px;">
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-inline">
            <input type="password" name="oldPassword" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input type="password" name="newPassword1" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" name="newPassword2" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="align-content: center;">
            <button type="submit" class="layui-btn layui-btn-normal"  onclick="subForm();return false;">提 交</button>
            <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
        </div>
    </div>
</form>

<script>

    layui.use(["table","form","layer"]);

    function subForm() {
        var params = $("#passwordRest").serialize();
        $.post("${path}/easUser/passwordRest",params,function(data){
            if(data.code === 4){
                layui.layer.msg(data.msg,{icon:5});
            }else if(data.code === 3){
                layui.layer.msg(data.msg,{icon:5});
            }else if(data.code === 2){
                layui.layer.msg(data.msg,{icon:5});
            }else if(data.code === 1){
                layui.layer.msg(data.msg,{icon:5});
            }else if(data.code === 0){
                layui.layer.msg(data.msg,{icon:1});
            }else {
                layui.layer.msg('密码修改失败',{icon:1});
            }

        });
    }


</script>
</body>
</html>
