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
<%--<form class="layui-form" action="">
    <div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input id="name" type="text" name="price_min" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">学号</label>
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
</form>--%>
<table class="layui-table" lay-data="{id:'studentTable',url:'${path}/student/list', page:true,toolbar:'#toolbarDemo',defaultToolbar: ['filter', 'print', 'exports'],even: true}"
       lay-filter="studentTable">
    <thead>
    <tr>
        <th lay-data="{field:'snu',align:'center'}">学号</th>
        <th lay-data="{field:'sname',align:'center'}">姓名</th>
        <th lay-data="{field:'ssex',align:'center'}">性别</th>
        <th lay-data="{field:'sbirth',align:'center'}">生日</th>
        <th lay-data="{field:'scredit',align:'center'}">学分</th>
        <th lay-data="{field:'tname',align:'center'}">班主任</th>
        <th lay-data="{field:'cname',sort: true,templet:function(data){return data.cname;}}">班级</th>
        <th lay-data="{field:'dname',sort: true,templet:function(data){return data.dname;}}">系名</th>
        <th lay-data="{field:'sette',align:'center'}">入学时间</th>
    </tr>
    </thead>
</table>


<script>
    $.get("${path}/class/search",function (data) {
        $.each(data,function () {
            console.log(data+"--------");
            var opt = $("<option></option>").appendTo("#allclass");
            opt.text(this.classes).val(this.cnu);
        });
        layui.form.render();
    });
    function searchData(){
        layui.table.reload("studentTable",{
            page:{
                curr : 1
            },
            where:{
                "sname":$("#sname").val(),
                "scredit":$("#snu").val(),
                "cnu":$("#allclass").val()
            }
        });
    }
    layui.use(["table","form"],function () {
        var table = layui.table;
    });
</script>



</body>
</html>
