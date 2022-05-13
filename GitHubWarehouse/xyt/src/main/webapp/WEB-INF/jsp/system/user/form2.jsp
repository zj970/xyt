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
    <shiro:hasPermission name="student:update">
    <div class="layui-form-item">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-inline">
            <input type="text" name="snu" readonly="readonly"  value="${data.snu}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="sname" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="ssex" value="男" title="男" checked="">
            <input type="radio" name="ssex" value="女" title="女">
        </div>
    </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级</label>
            <div class="layui-input-inline" style="width: 300px;">
                <select id="allclass" name="cnu">
                    <option selected="selected" disabled="disabled" style='display: none' value=''></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-inline">
                <input type="date" name="sbirth" required  lay-verify="required" placeholder="请输入生日" autocomplete="off" class="layui-input"  pattern="yyyy-MM-dd">
            </div>
        </div>

    </shiro:hasPermission>
    <shiro:hasPermission name="teacher:update">
        <div class="layui-form-item">
            <label class="layui-form-label">工作证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="tnu" readonly="readonly"  value="${data.tnu}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="tname" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <input type="radio" name="tsex" value="男" title="男" checked="">
                <input type="radio" name="tsex" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-inline">
                <select id="title" name="title">
                    <option selected="selected" disabled="disabled"  style='display: none' value=''></option>
                    <option  value='本科'>本科</option>
                    <option  value='硕士'>硕士</option>
                    <option  value='博士'>博士</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-inline">
                <input type="text" name="tphone" required lay-verify="required" placeholder="请输入电话" autocomplete="off" class="layui-input">
            </div>
        </div>

    </shiro:hasPermission>
    <script>
        layui.form.render();//渲染表格加载 radio 以及下拉框select
        $.get("${pageContext.request.contextPath}/class/search",function (data) {
            console.log(data+"4444")
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#allclass");
                opt.text(this.cname).val(this.cnu);
            });
            //获取数据后再进行渲染，显示未显示的option
            layui.form.render();
        });
    </script>
</form>
</body>
</html>
