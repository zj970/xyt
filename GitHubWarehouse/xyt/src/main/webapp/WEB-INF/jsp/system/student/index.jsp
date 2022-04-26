<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<%--    学生信息--%>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
    <link rel="stylesheet" href="${path}/layui/css/formSelects-v4.css" media="all">
</head>
<body>
<%--<h2>学生信息表</h2>--%>
<%--<hr>--%>
<form class="layui-form" action="">
    <div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input id="name" type="text" name="price_min" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">电话</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input id="phone" type="text" name="price_min" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">班级</label>
            <div class="layui-input-inline" style="width: 300px;">
                <select id="allclass">
                    <option selected="selected" disabled="disabled"  style='display: none' value=''></option>
                </select>
            </div>
            <div class="layui-input-inline" style="width: 100px;">
                <button type="button" class="layui-btn layui-btn-normal" onclick="searchData();"><i class="layui-icon layui-icon-search"></i>
                    查询</button>
            </div>
        </div>

    </div>
</form>
<table class="layui-table" lay-data="{id:'studentTable',url:'${path}/easStudent/list', page:true,toolbar:'#toolbarDemo',defaultToolbar: ['filter', 'print', 'exports'],even: true}"
       lay-filter="studentTable">
    <thead>
    <tr>
<%--        <th lay-data="{type:'checkbox'}">student_msg</th>--%>
        <th lay-data="{field:'id', width:80}">ID</th>
        <th lay-data="{field:'username', width:180,align:'center',sort: true,templet:function(res){return res.user.username;}}">用户名</th>
        <th lay-data="{field:'name',align:'center'}">姓名</th>
        <th lay-data="{field:'sex',}">性别</th>
        <th lay-data="{field:'birthday',align:'center'}">生日</th>
        <th lay-data="{field:'phone',align:'center'}">电话</th>
        <th lay-data="{field:'classes',sort: true,templet:function(data){return data.easClass.classes;}}">班级</th>
        <th lay-data="{field:'motto',}">座右铭</th>
<%--        <th lay-data="{toolbar:'#barDemo',align:'center'}">操作</th>--%>
    </tr>
    </thead>
</table>
<script>

    $.get("${path}/easClass/search",function (data) {
        $.each(data,function () {
            var opt = $("<option></option>").appendTo("#allclass");
            opt.text(this.classes).val(this.id);

        });
        layui.form.render();
    });

    function searchData(){
        layui.table.reload("studentTable",{
            page:{
                curr : 1
            },
            where:{
                "name":$("#name").val(),
                "phone":$("#phone").val(),
                "class_id":$("#allclass").val()
            }
        });
        // console.log($("#name").val());
    }

    layui.use(["table","form"],function () {
        var table = layui.table;


    });

</script>



</body>
</html>
