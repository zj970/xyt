<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<%--    教师信息--%>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
</head>
<body>
<%--<h2>教师信息表</h2>--%>
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

            <label class="layui-form-label">学历</label>
            <div class="layui-input-inline" style="width: 300px;">
                <select id="education">
                    <option selected="selected" disabled="disabled"  style='display: none' value=''></option>
                    <option  value='本科'>本科</option>
                    <option  value='硕士'>硕士</option>
                    <option  value='博士'>博士</option>
                </select>
            </div>

            <div class="layui-input-inline" style="width: 100px;">
                <button type="button" class="layui-btn layui-btn-normal" onclick="searchData();"><i class="layui-icon layui-icon-search"></i>
                    查询</button>
            </div>
        </div>

    </div>
</form>
<table class="layui-table" lay-data="{id:'teacherTable',url:'${path}/teacher/list', page:true,toolbar:'#toolbarDemo',defaultToolbar: ['filter', 'print', 'exports'],even: true}"
       lay-filter="teacherTable">
    <thead>
    <tr>
        <th lay-data="{field:'tnu', width:80}">工作证号</th>
        <th lay-data="{field:'tname',align:'center'}">姓名</th>
        <th lay-data="{field:'tsex',align:'center'}">性别</th>
        <th lay-data="{field:'tphone',align:'center'}">电话</th>
        <th lay-data="{field:'teyte',align:'center'}">入职时间</th>
        <th lay-data="{field:'tsnte',align:'center'}">离职时间</th>
        <th lay-data="{field:'title',align:'center'}">学历</th>
        <th lay-data="{field:'dname',}">系名</th>
    </tr>
    </thead>
</table>
<script>
    function searchData(){
        layui.table.reload("teacherTable",{
            page:{
                curr : 1
            },
            where:{
                "cname":$("#cname").val(),
                "tphone":$("#tphone").val(),
                "title":$("#title").val()
            }
        });
    }
    layui.use(["table","form"],function () {
        var table = layui.table;
    });
</script>
</body>
</html>
