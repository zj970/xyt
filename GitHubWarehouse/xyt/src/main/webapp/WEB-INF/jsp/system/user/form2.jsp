<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%--    基本资料修改表单--%>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form class="layui-form" lay-filter="basicForm" id="edit_basic_form" action="" style="padding:15px 10px;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="username" readonly="readonly"  lay-verify="required"  class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生日</label>
        <div class="layui-input-inline">
            <input type="date" name="birthday" required  lay-verify="required" placeholder="请输入生日" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-inline">
            <input type="text" name="phone" required  lay-verify="required" placeholder="请输入电话" autocomplete="off" class="layui-input">
        </div>
    </div>
    <shiro:hasPermission name="student:update">
    <div class="layui-form-item">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-inline" style="width: 300px;">
           <select id="allclass" name="classes">
               <option selected="selected" disabled="disabled" style='display: none' value=''></option>
           </select>
        </div>
    </div>
    </shiro:hasPermission>
    <shiro:hasPermission name="teacher:update">
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-inline">
                <select id="education" name="education">
                    <option selected="selected" disabled="disabled"  style='display: none' value=''></option>
                    <option  value='本科'>本科</option>
                    <option  value='硕士'>硕士</option>
                    <option  value='博士'>博士</option>
                </select>
            </div>
        </div>
    </shiro:hasPermission>
    <div class="layui-form-item">
        <label class="layui-form-label">座右铭</label>
        <div class="layui-input-block">
            <textarea name="motto" placeholder="请输入座右铭" class="layui-textarea"></textarea>
        </div>
    </div>

    <script>
        layui.form.render();//渲染表格加载 radio 以及下拉框select
        $.get("${pageContext.request.contextPath}/easClass/search",function (data) {
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#allclass");
                opt.text(this.classes).val(this.id);
            });
            //获取数据后再进行渲染，显示未显示的option
            layui.form.render();
        });

    </script>
</form>

</body>
</html>
