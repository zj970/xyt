<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
    <style>
        /*解决LayUI数据表格复选框不居中显示的问题*/
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
            top: 50%;
            transform: translateY(-50%);
        }
    </style>
</head>
<body>
<%--<h2>我的学生选课信息</h2>--%>
<%--<hr>--%>
<form class="layui-form" action=""  lay-filter="scoreSearchForm" style="margin-left: 15%;">
    <div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
        <div class="layui-inline">

            <div class="layui-inline">
                <div class="layui-input-inline" >
                    <select id="lnu" class="lnu">
                        <option value="">全部</option>
                    </select>
                </div>

                <div class="layui-input-inline" >
                    <select id="cnu" class="cnu">
                             <option value="">全部</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload">查询</a>
            </div>

        </div>

    </div>
</form>

<table id="myStudentSelectCoureTable" lay-filter="myStudentSelectCoureTable"></table>

<script>
    layui.use(['form','layer','table','laytpl'],function(){
        var form = layui.form,
            laytpl = layui.laytpl,
            table = layui.table;

        var colsArray =[[
            {field: "snu", title: "学号", sort:true, align:"center"},
            {field: "sname", title: "姓名", align:"center"},
            {field: "ssex", title: "性别",  align:"center"},
            {field: "cname", title: "班级",  align:"center"},
            {field: "dname", title: "院系",  align:"center"},
            {field: "lname", title: "课程名",  align:"center"},
        ]];

        //学生成绩列表
        var tableIns = table.render({
            id : "myStudentSelectCoureTable",
            elem: "#myStudentSelectCoureTable",
            url : "${path}/score/stuSelectCourseList",
            page : true,
            toolbar: true,
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '我的学生选课表',
            cols : colsArray
        });

        //查询所有基本课程名称
        layui.form.render('select','scoreSearchForm');//渲染表格加载 下拉框select
        $.get("${path}/baseCourse/search",function (data) {
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#lnu");
                opt.text(this.lname).val(this.lnu);
            });
            //获取数据后再进行渲染，显示未显示的option
            layui.form.render('select','scoreSearchForm'); //获取内容重新渲染表格 下拉框select
        });

        //查询所有班级名称 返回班级id和班级名称
        layui.form.render('select','scoreSearchForm');//渲染表格加载 下拉框select
        $.get("${path}/class/search",function (data) {
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#cnu");
                opt.text(this.cname).val(this.cnu);
            });
            //获取数据后再进行渲染，显示未显示的option
            layui.form.render('select','scoreSearchForm'); //获取内容重新渲染表格 下拉框select
        });

        //根据课程id搜索本课的学生成绩信息
        $(".search_btn").on("click",function(){
            tableIns.reload({
                page: {
                    curr: 1
                },
                where: {
                    lnu: $("#lnu").val(),
                    cnu: $("#cnu").val()
                }
            });
        });

    });

</script>

</body>
</html>
