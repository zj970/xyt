<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<%--    学生课程页面--%>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
</head>
<body>
<%--<h2>学生选课页面</h2>--%>
<%--<hr>--%>
<form class="layui-form" action="">
    <div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
        <div class="layui-inline">
            <div class="layui-inline">
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="text" class="layui-input searchVal" placeholder="搜索课程名/教师名" />
                </div>
<%--                <a class="layui-btn search_btn" data-type="reload">搜索</a>--%>
                <button type="reset" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-refresh"></i>重置</button>
            </div>
            <div class="layui-inline" style="width: 100px;">
                <a class="layui-btn layui-btn-primary search_btn" id = "allCourse">可选课程</a>
            </div>
            <div class="layui-inline" style="width: 100px;">
                <a class="layui-btn layui-btn-primary search_btn" id = "myCourse">已选课程</a>
            </div>
        </div>
    </div>
</form>

<table class="layui-hide" id="studentCourseTable" lay-filter="studentCourseTable"></table>

<script>
    layui.use(['table','form'],function() {
        var table = layui.table;

        var colsArray = [[
            {field: "lnu", title: "课程编号", sort:true, width:100, align:"left"},
            {field: "lname", title: "课程名",minWidth:120, align:"left"},
            {field: "tname", title: "任课教师",minWidth:120, align:"center"},
            {field: "lnum", title: "最大人数", minWidth:100, align:"center"},
            {field: "choiceNum", title: "已选（人）", minWidth:100, align:"center"},
            {title: "操作", width:90, templet:"#courseListBar",fixed:"right",align:"center"}
        ]];

        //studentCourseList列表
        var tableIns = null;
        loadChoiceList(1);
        //isAll =1 可选列表  =0 已选列表
        function loadChoiceList(isAll) {

            if (isAll === 1) {
                colsArray[0][9] = {title: "操作", width:90, templet:"#courseListBar",fixed:"right",align:"center"};
            } else {
                colsArray[0][9] = {title: "操作", width:90, templet:"#courseListBar2",fixed:"right",align:"center"};
            }

            tableIns = table.render({
                id : 'studentCourseTable',
                elem: '#studentCourseTable',
                url : '${path}/course/choiceList',
                even:true,
                where: {
                    isAll: isAll,
                    searchKey: $(".searchVal").val()  //搜索的关键字
                },
                height : "full-125",  //高度将始终铺满//
                cellMinWidth : 95,//最小宽度
                toolbar:true,
                defaultToolbar: ['filter', 'exports', 'print'],
                title: '选课表',
                page : true,
                cols : colsArray
            });
        }

        //搜索
        $(".search_btn").on("click",function(){
            if ($(this).attr("id") == "myCourse") {
                loadChoiceList(0);
            } else {//搜索和all
                loadChoiceList(1);
            }
        });

        $(".reset_btn").on("click",function(){

        });


        //表格操作列表操作
        table.on("tool(studentCourseTable)", function(obj){
            var data = obj.data,
                layEvent = obj.event;
            var url;
            if (layEvent == "choice") {
                //选课操作
                url = "${path}/score/choiceCourse";
            } else {
                //退课操作
                url = "${path}/score/outCourse";
            }
            $.ajax({
                type: "get",
                data: {
                    courseId: data.id
                }, //将课程id传回前台进行处理
                url: url,
                success:function(data) {
                    if (data.result === true) {
                        layer.msg(data.msg, {icon: 1,time:1000},function () {
                            tableIns.reload();
                        });

                        // layer.close(index);
                    }else {
                        layer.msg(data.msg, {icon: 5,time:1000});
                    }
                }
            });
        });


    });
</script>

<!--操作-->
<script type="text/html" id="courseListBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="choice">选课</a>
</script>
<script type="text/html" id="courseListBar2">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancelChoice">取消</a>
</script>
</body>
</html>
