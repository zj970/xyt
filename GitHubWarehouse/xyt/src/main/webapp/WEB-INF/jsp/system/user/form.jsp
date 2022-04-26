<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%--    用户信息表单--%>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form class="layui-form" lay-filter="userForm" id="add_user_form" action="" style="padding:15px 10px;">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加密盐值</label>
        <div class="layui-input-block">
            <input type="text" name="salt" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否锁定</label>
        <div class="layui-input-block">
            <input type="checkbox" name="locked" value="1" lay-skin="switch">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-block" >
            <select name="roleIds" id="roles_add" xm-select="roles2" xm-select-direction="down">
                <option value="-1">请选择</option>
            </select>
        </div>
    </div>
    <script>
        layui.form.render();
        $.get("${pageContext.request.contextPath}/easRole/search",function(data){
            $.each(data,function(){
                var opt = $("<option></option>").appendTo("#roles_add");
                opt.text(this.name).val(this.id);
            });
            layui.formSelects.render();
        });

    </script>
</form>
</body>
</html>
