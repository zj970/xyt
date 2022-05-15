<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
    <link rel="stylesheet" href="${path}/layui/css/formSelects-v4.css" media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
        <div class="layui-inline">
            <div class="layui-inline">
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="text" class="layui-input searchVal" placeholder="学号/工作证号" />
                </div>
                <button type="reset" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-refresh"></i>搜索</button>
            </div>
            <div class="layui-inline" style="width: 100px;">
                <a class="layui-btn layui-btn-primary search_btn" id = "student">学生用户</a>
            </div>
            <div class="layui-inline" style="width: 100px;">
                <a class="layui-btn layui-btn-primary search_btn" id = "teacher">教师用户</a>
            </div>
        </div>

    </div>
</form>
<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
<script>
    layui.use(['table','from']),function (){
        var table = layui.table;
        var studentArray = [[
            {field:"snu",title:"学号",edit: "text",sort:true,align:"center"},
            {field:"sname",title:"姓名",edit: "text",sort:true,align:"center"},
            {field:"ssex",title:"性别",edit: "text",sort:true,align:"center"},
            {field:"scredit",title:"学分",edit: "text",sort:true,align:"center"},
            {field:"sette",title:"入学时间",edit: "date",sort:true,align:"center"},
            {field:"spd",title:"密码",edit: "text",sort:true,align:"center"},
            {field:"cname",title:"班级",edit: "text",sort:true,align:"center"},
            {title: "更新", width:120, templet:"#studentScoreListBar",fixed:"right",align:"center"}
        ]];
        var teacherArray = [[
            {field:"tnu",title:"工作证号",edit: "text",align:"center"},
            {field:"tname",title:"姓名",edit: "text",align:"center"},
            {field:"tsex",title:"性别",edit: "text",align:"center"},
            {field:"tette",title:"入职时间",edit: "date",align:"center"},
            {field:"tsnte",title:"离职时间",edit: "date",align:"center"},
            {field:"tpd",title:"密码",edit: "text",align:"center"},
            {field:"dname",title:"院系",edit: "text",align:"center"},
            {title: "更新", width:120, templet:"#studentScoreListBar",fixed:"right",align:"center"}
        ]];
        //学生用户列表
        loadStudent();
        function loadStudent() {
            var tablesStu = table.render({
                id:"userTable",
                elem:"#userTable",
                url:"${path}/admin/studentList",
                page : true,
                toolbar: true,
                defaultToolbar: ['filter', 'exports', 'print'],
                height : "full-125",  //高度将始终铺满//
                title: '学生用户表',
                cols : studentArray
            });
        }
        //教师用户列表
        function loadTeacher() {
            var tablesStu = table.render({
                id:"userTable",
                elem:"#userTable",
                url:"${path}/admin/teacherList",
                page : true,
                toolbar: true,
                defaultToolbar: ['filter', 'exports', 'print'],
                height : "full-125",  //高度将始终铺满//
                title: '教师用户表',
                cols : teacherArray
            });
        }

        //搜索
        $(".search_btn").on("click",function(){
            console.log($(this).attr("id")+"-----------------");
            if ($(this).attr("id") == "student") {
                loadStudent();
            } else {//搜索和all
                loadTeacher();
            }
        });
    }
</script>
<!--操作-->
<script type="text/html" id="update">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="choice">更新</a>
</script>
</body>
</html>
