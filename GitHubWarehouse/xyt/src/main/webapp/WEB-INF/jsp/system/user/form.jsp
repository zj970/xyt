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
    <style>
        /*解决LayUI数据表格复选框不居中显示的问题*/
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
            top: 50%;
            transform: translateY(-50%);
        }
    </style>
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
<table id="userTable" lay-filter="userTable"></table>
<script>
    layui.use(['form','layer','table','laytpl','upload'],function (){
        var form = layui.form,
            laytpl = layui.laytpl,
            table = layui.table;
        var studentArray = [[
            {field:"snu",title:"学号",edit: "text",sort:true,align:"center"},
            {field:"sname",title:"姓名",edit: "text",align:"center"},
            {field:"ssex",title:"性别",edit: "text",align:"center"},
            {field:"scredit",title:"学分",edit: "text",align:"center"},
            {field:"sette",title:"入学时间",edit: "date",align:"center"},
            {field:"spd",title:"密码",edit: "text",align:"center"},
            {field:'cnu', title:'班级', templet: function (obj) {
                if (obj.cnu)
                    var str = '<select lay-filter = "getAllClass" name="getCname">';
                    $.ajax({
                        url:"${path}/class/search",
                        type:"get",
                        async:false,
                        success: function (data) {
                            for (k in data){
                                str+='<option value = "'+data[k].cnu+'"'+'>'+data[k].cname+'</option>';
                                //console.log(str);
                                str+='</select>';
                                console.log("请求后"+str);
                            }
                        }
                    });
                    return str;
                },align:"center"},
            {title: "更新", width:120, templet:"#updateStudent",fixed:"right",align:"center"}
        ]];
        var teacherArray = [[
            {field:"tnu",title:"工作证号",edit: "text",align:"center"},
            {field:"tname",title:"姓名",edit: "text",align:"center"},
            {field:"tsex",title:"性别",edit: "text",align:"center"},
            {field:"teyte",title:"入职时间",edit: "date",align:"center"},
            {field:"tsnte",title:"离职时间",edit: "date",align:"center"},
            {field:"tpd",title:"密码",edit: "text",align:"center"},
            {field:"dname",title:"院系",edit: "text",align:"center"},
            {title: "更新", width:120, templet:"#updateTeacher",fixed:"right",align:"center"}
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
                done: function (res, curr, count) {
                    var tableElem = this.elem.next('.layui-table-view');
                    count || tableElem.find('.layui-table-header').css('overflow', 'auto');
                    layui.each(tableElem.find('select[name="getCname"]'), function (index, item) {
                        var elem = $(item);
                        elem.val(elem.data('cnu')).parents('div.layui-table-cell').css('overflow', 'visible');
                    });
                    form.render();//刷新表单
                },
                cols : studentArray
            });
        };

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
        };

        //状态改变
        table.on('select(getAllClass)', function (data) {//修改类型
            let cnu = data.elem.dataset.cnu;
            let cname = data.elem.value();
        });

        //搜索
        $(".search_btn").on("click",function(){
            console.log($(this).attr("id")+"-----------------");
            if ($(this).attr("id") == "student") {
                loadStudent();
            } else {//搜索和all
                loadTeacher();
            }
        });
        //根据班级Cnu显示班级信息
        /* 设置下拉框的高度与表格单元相同 */
        //监听行工具事件
        table.on("tool(userTable)",function (obj){
            var data = obj.data;
            //更新用户信息
            if (obj.event == 'updateStudent'){
                $.ajax({
                    type:"get",
                    data:{
                        snu:data.snu,
                        sname:data.sname,
                        ssex:data.ssex,
                        scredit: data.scredit,
                        sette:data.sette,
                        spd:data.spd,
                        cnu:data.cnu
                    },
                    url: "${path}/admin/updateStudent",
                    success:function(res) {
                        if (res.result === true) {
                            //提交成绩成功
                            layer.msg(res.msg, {icon: 1,time:1000},function () {
                                tableIns.reload();
                            });
                        }else {
                            layer.msg(res.msg, {icon: 5,time:1000});
                        }
                    }
                })
            }

            if (obj.event == 'updateTeacher'){
                $.ajax({
                    type:"get",
                    data:{
                        tnu:data.tnu,
                        tname:data.tname,
                        tsex:data.tsex,
                        teyte:data.teyte,
                        tsnte:data.tsnte,
                        tpd:data.tpd,
                        dnu:data.dnu
                    },
                    url: "${path}/admin/updateTeacher",
                    success:function(res) {
                        if (res.result === true) {
                            //提交成绩成功
                            layer.msg(res.msg, {icon: 1,time:1000},function () {
                                tableIns.reload();
                            });
                        }else {
                            layer.msg(res.msg, {icon: 5,time:1000});
                        }
                    }
                })
            }
        });
    });
</script>
<!--操作-->
<script type="text/html" id="updateStudent">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="updateUser">更新</a>
</script>
<script type="text/html" id="updateTeacher">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="updateUser">更新</a>
</script>
</body>
</html>
