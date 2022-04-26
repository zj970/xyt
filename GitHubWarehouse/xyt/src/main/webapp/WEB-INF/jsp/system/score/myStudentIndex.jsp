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
                    <select id="baseCourseId" class="baseCourseId">
<%--                        <option selected="selected" disabled="disabled" style='display: none' value=''></option>--%>
                        <option value="">全部</option>

                    </select>
                </div>

                <div class="layui-input-inline" >
                    <select id="classId" class="classId">
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
            // {type: "checkbox", fixed:"left", width:50},
            {field: "id", title: "ID", sort:true, width:60, align:"center"},
            {field: "name", title: "姓名",width:100, align:"center"},
            {field: "sex", title: "性别", width:70, align:"center"},
            {field: "phone", title: "电话", width:150, align:"center"},
            {field: "className", title: "班级", minWidth:180, align:"center"},
            {field: "courseName", title: "课程名", minWidth:150, align:"center"},
            // {field: "score", title: "成绩", edit: "text", width:140, align:"center"},
            // {field: "result", title: "结果", edit: "text", width:130, align:"center"},
            // // {field: "result", title: "结果", edit: "text",sort: true, width:130, align:"center",templet: '#resultSelect'},
            // {title: "操作", width:120, templet:"#studentScoreListBar",fixed:"right",align:"center"}
        ]];

        //学生成绩列表
        var tableIns = table.render({
            id : "myStudentSelectCoureTable",
            elem: "#myStudentSelectCoureTable",
            url : "${path}/easScore/stuSelectCourseList",
            // where: {
            //     courseId: $("#searchType").val() //课程id
            // },
            // height : "full-125",
            page : true,
            toolbar: true,
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '我的学生选课表',
            cols : colsArray
        });

        //查询所有基本课程名称
        layui.form.render('select','scoreSearchForm');//渲染表格加载 下拉框select
        $.get("${path}/easBaseCourse/search",function (data) {
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#baseCourseId");
                opt.text(this.coursename).val(this.id);
            });
            //获取数据后再进行渲染，显示未显示的option
            layui.form.render('select','scoreSearchForm'); //获取内容重新渲染表格 下拉框select
        });

        //查询所有班级名称 返回班级id和班级名称
        layui.form.render('select','scoreSearchForm');//渲染表格加载 下拉框select
        $.get("${path}/easClass/search",function (data) {
            $.each(data,function () {
                var opt = $("<option></option>").appendTo("#classId");
                opt.text(this.classes).val(this.id);
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
                    baseCourseId: $("#baseCourseId").val(),
                    classId: $("#classId").val()
                }
            });
        });

        <%--//监听行工具事件--%>
        <%--table.on("tool(studentScoreListTable)",function (obj) {--%>
        <%--    var data = obj.data;--%>
        <%--    //教师提交成绩--%>
        <%--    if (obj.event == 'submitResult'){--%>
        <%--        $.ajax({--%>
        <%--            type: "get",--%>
        <%--            data: {--%>
        <%--                id: data.id,--%>
        <%--                score: data.score,--%>
        <%--                result: data.result--%>
        <%--            },--%>
        <%--            url: "${path}/easScore/updateScore",--%>
        <%--            success:function(res) {--%>
        <%--                if (res.result === true) {--%>
        <%--                    //提交成绩成功--%>
        <%--                    layer.msg(res.msg, {icon: 1,time:1000},function () {--%>
        <%--                        tableIns.reload();--%>
        <%--                    });--%>
        <%--                }else {--%>
        <%--                    layer.msg(res.msg, {icon: 5,time:1000});--%>
        <%--                }--%>
        <%--            }--%>
        <%--        });--%>

        <%--    }--%>

        <%--});--%>


    });

</script>

<%--操作--%>
<%--<script type="text/html" id="studentScoreListBar">--%>
<%--    <a class="layui-btn layui-btn-xs" lay-event="submitResult">提交成绩</a>--%>
<%--</script>--%>
</body>
</html>
