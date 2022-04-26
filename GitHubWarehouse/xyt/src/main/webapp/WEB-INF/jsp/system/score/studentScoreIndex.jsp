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
<%--<h2>教师授课的成绩页面</h2>--%>
<%--<hr>--%>
<form class="layui-form" action=""  lay-filter="scoreSearchForm">
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

            <div class="layui-inline" style="margin-left:40px">
                <a id="update_list_btn" class="layui-btn ">批量提交成绩</a>
            </div>

        </div>
    </div>
</form>

<table id="studentScoreListTable" lay-filter="studentScoreListTable"></table>

<script>
    layui.use(['form','layer','table','laytpl'],function(){
        var form = layui.form,
            laytpl = layui.laytpl,
            table = layui.table;

        var colsArray =[[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title: "ID", sort:true, width:60, align:"center"},
            {field: "name", title: "姓名",width:100, align:"center"},
            {field: "sex", title: "性别", width:70, align:"center"},
            {field: "phone", title: "电话", width:150, align:"center"},
            {field: "className", title: "班级", minWidth:180, align:"center"},
            {field: "courseName", title: "课程名", minWidth:150, align:"center"},
            {field: "score", title: "成绩", edit: "text", width:140, align:"center"},
            {field: "result", title: "结果", edit: "text", width:130, align:"center"},
            // {field: "result", title: "结果", edit: "text",sort: true, width:130, align:"center",templet: '#resultSelect'},
            {title: "操作", width:120, templet:"#studentScoreListBar",fixed:"right",align:"center"}
        ]];

        //学生成绩列表
        var tableIns = table.render({
            id : "studentScoreListTable",
            elem: "#studentScoreListTable",
            url : "${path}/easScore/stuScoreList",
            // where: {
            //     courseId: $("#searchType").val() //课程id
            // },
            // height : "full-125",
            page : true,
            toolbar: true,
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '学生成绩表',
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

        //监听行工具事件
        table.on("tool(studentScoreListTable)",function (obj) {
            var data = obj.data;
            //教师提交成绩
            if (obj.event == 'submitResult'){
                $.ajax({
                    type: "get",
                    data: {
                        id: data.id,
                        score: data.score,
                        result: data.result
                    },
                    url: "${path}/easScore/updateScore",
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
                });

            }

        });

        //批量评分
        $("#update_list_btn").click(function(){
            var checkStatus = table.checkStatus('studentScoreListTable'),
                data = checkStatus.data,
                scoreList = new Array();
            if(data.length > 0) {
                for (var i in data) {
                    var score = new Object();
                    score.id = data[i].id;
                    score.score = data[i].score;
                    score.result = data[i].result;
                    scoreList[i] = score;
                }
                // console.log("我是成绩列表:"+scoreList);//打印成绩列表信息
                layer.confirm("确定提交？", {icon: 3, title: "提示信息"}, function (index) {
                    $.ajax({
                        type: "post",
                        data: {
                            scoreListStr : JSON.stringify(scoreList)
                        },
                        url: "${path}/easScore/updateScoreList",
                        timeout:2000,
                        success:function(res) {
                            if (res.result === true) {
                                tableIns.reload({
                                    page: { curr: 1 },
                                    where: { courseId: $("#searchType").val() }
                                });
                            } else {
                                layer.msg(res.data, {icon: 5,time:1000});
                            }
                            layer.close(index);
                        },
                        error:function() {
                            layer.msg("操作失败！", {icon: 5,time:1000});
                            layer.close(index);
                        }
                    });
                });
            }else{
                layer.msg("请选择要选择提交的行");
            }
        });

    });

</script>


<%--<script type="text/html" id="resultSelect">--%>
<%--    <select id="result">--%>
<%--        <option value="">请选择成绩</option>--%>
<%--        <option value="不及格">不及格</option>--%>
<%--        <option value="及格">及格</option>--%>
<%--        <option value="中等">女</option>--%>
<%--        <option value="良好">良好</option>--%>
<%--        <option value="优秀">优秀</option>--%>
<%--        </select>--%>
<%--</script>--%>

<%--<script type="text/javascript" id="resultSelect">--%>

<%--    // 遍历select--%>
<%--    $("#result").each(function() {--%>
<%--        // this代表的是<option></option>，对option再进行遍历--%>
<%--        $(this).children("option").each(function() {--%>
<%--            // 判断需要对那个选项进行回显--%>
<%--            if (this.value === 2 ) {--%>
<%--                console.log($(this).text());--%>
<%--                // 进行回显--%>
<%--                $(this).attr("selected","selected");--%>
<%--            }--%>
<%--        });--%>
<%--    })--%>
<%--</script>--%>

<%--操作--%>
<script type="text/html" id="studentScoreListBar">
    <a class="layui-btn layui-btn-xs" lay-event="submitResult">提交成绩</a>
</script>
</body>
</html>
