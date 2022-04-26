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
<%--<h2>学生成绩页面</h2>--%>
<%--<hr>--%>
<form class="layui-form" style="margin-left: 5%;margin-top: 30px;">
    <div class="layui-inline">
        <a id="all" class="layui-btn layui-btn-primary search_btn ">全部</a>
    </div>
    <div class="layui-inline">
        <a id="notpass" class="layui-btn layui-btn-primary search_btn">未通过课程</a>
    </div>
    <div class="layui-inline">
        <a  id="pass" class="layui-btn layui-btn-primary search_btn">已通过课程</a>
    </div>
<%--    <div class="layui-inline">--%>
<%--        <a class="layui-btn layui-btn-primary" onclick="doPrint()">打印</a>--%>
<%--    </div>--%>
</form>

<table id="MyScoreListTable" lay-filter="MyScoreListTable" ></table>

<script>
    layui.use(['form','layer','table','laytpl','upload'],function() {
        var form = layui.form,
            table = layui.table;

        var colsArray =[[
            {field: 'startDate', title: '开始时间',width:60, align:'center'},
            {field: 'courseName', title: '课程名',width:100, align:'left'},
            {field: 'teacherName', title: '任课教师',minWidth:120, align:'left'},
            {field: 'testMode', title: '考核方式', minWidth:100, align:'center'},
            {field: 'score', title: '成绩', minWidth:100, align:'center'},
            {field: 'result', title: '结果', minWidth:100, align:'center'}
        ]];

        //MyScoreListTable列表
        var tableIns = null;
        loadCourse();
        function loadCourse() {
            tableIns = table.render({
                id : "MyScoreListTable",
                elem: "#MyScoreListTable",
                url : "${path}/easScore/myScore",
                cellMinWidth : 95,
                page : true,
                toolbar: true,
                defaultToolbar: ['filter', 'exports', 'print'],
                cols : colsArray
            });
        }


        //搜索按钮
        $(".search_btn").on("click",function(){
            var id = $(this).attr("id");
            var result;
            if (id === "all") {
                result = 2;
            } else if (id === "pass") {
                result = 1;
            } else {
                result = 0;
            }
            tableIns.reload({
                page: {curr: 1},
                where: {
                    result: result
                }
            });
        });

    });
</script>
</body>
</html>
