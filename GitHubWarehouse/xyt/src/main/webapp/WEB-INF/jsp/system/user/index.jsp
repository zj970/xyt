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
<%--<h2>用户列表</h2>--%>
<%--<hr>--%>
<form class="layui-form" action="">
<div class="layui-form-item" style="margin-left: 5%;margin-top: 30px;">
    <div class="layui-inline">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input id="username" type="text" name="price_min" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">角色</label>
        <div class="layui-input-inline" style="width: 300px;">
            <select xm-select="roles" name="" id="roles">
                <option value="-1">全部</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 100px;">
            <button type="button" class="layui-btn layui-btn-normal" onclick="searchData();"><i class="layui-icon layui-icon-search"></i>
                查询</button>
        </div>
    </div>
</div>
</form>

<table class="layui-table" lay-data="{id:'userTable',url:'${path}/easUser/list', page:true,toolbar:'#toolbarDemo',defaultToolbar: ['filter', 'print', 'exports'],even: true,height : 'full-125'}"
       lay-filter="userTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox'}">user_msg</th>
        <th lay-data="{field:'id', width:80}">ID</th>
        <th lay-data="{field:'username', width:180,align:'center'}">用户名</th>
        <th lay-data="{field:'salt'}">盐值</th>
        <th lay-data="{field:'locked',templet:'#lockedTem'}">是否锁定</th>
        <th lay-data="{field:'roleString'}">所属角色</th>
        <th lay-data="{field:'regDate', align:'center',sort: true}">注册日期</th>
        <th lay-data="{toolbar:'#barDemo',align:'center'}">操作</th>
    </tr>
    </thead>
</table>
<script type="">

    $.get("${path}/easRole/search",function (data) {
        $.each(data,function () {
            var opt = $("<option></option>").appendTo("#roles");
            opt.text(this.name).val(this.id);
        });
    })
    
    function searchData(){
        layui.table.reload("userTable",{
            page:{
                curr : 1
            },
            where:{
                "username":$("#username").val(),
                "roleIds" : layui.formSelects.value('roles','valStr')//value('xm-select的值','例如  2，3，4')例如  2，3，4
            }
        });
    }

    layui.config({
        base: '${path}/layui/lay/modules/'
    }).extend({
        formSelects: 'formSelects-v4'
    });

    layui.use(["table","form","formSelects","laytpl"],function(){
        var table = layui.table,
            laytpl = layui.laytpl;

        //监听工具栏的事件
        table.on('toolbar(userTable)',function (obj) {
            var e = obj.event;
            switch (e){
                case "add":
                    $.get('${path}/easUser/form',function (str) {
                        layer.open({
                            type:1,
                            title :'添加用户',
                            area : '700px',
                            content : str,
                            skin:'layui-layer-molv',
                            btn : ['确定','取消'],
                            yes : function (index) {
                                var params = $("#add_user_form").serialize();
                                $.post('${path}/easUser/add',params,function (data) {

                                    if(data.result === false){
                                        //两个等于会自动类型转换后比较；
                                        //三个等于必须是同样的类型同样的值进行比较。
                                        layui.layer.msg(data.msg,{icon:5});
                                    }else{
                                        layui.layer.msg("添加成功",{icon:1,time:1000},function () {
                                            layer.close(index);
                                            table.reload('userTable');
                                        });

                                    }

                                    // layer.close(index);
                                    // table.reload('userTable');
                                });
                            }
                        });
                    });
                    break;
                case "batchDelete":
                    var rows = table.checkStatus('userTable');
                    var data = rows.data;
                    if(data.length == 0){
                        layer.msg("请选择要删除的数据",function(){});
                        return;
                    }
                    layer.confirm('真的干掉这么多数码吗？',{icon:5,title:'友情提示'},function (index) {
                        var params = "";
                        for(let user of data){
                            params += "ids="+user.id+"&";
                        }
                        $.post('${path}/easUser/batchDelete',params,function () {
                            layer.close(index);
                            table.reload('userTable');
                        });
                    });
                    break;

            }
        });

        //监听行工具事件
        table.on("tool(userTable)",function (obj) {
            var data = obj.data;
            if (obj.event == 'edit'){ //edit
                $.get("${path}/easUser/form",function (str) {
                    layer.open({
                        type:1,
                        title : '修改用户',
                        content : str,
                        area:'700px',
                        skin:'layui-layer-molv',
                        btn:['确定','取消'],
                        success:function(){
                            $.get('${path}/easUser/view',{id:data.id},function (data) {
                                //显示数据
                                layui.form.val('userForm',data);
                                var aa = new Array();
                                for(let role of data.roles){
                                    aa.push(role.id);
                                }
                                layui.formSelects.value('roles2',aa);
                            });
                        },
                        yes : function (index) {
                            var params2 = $("#add_user_form").serialize();
                            $.post('${path}/easUser/edit',params2,function () {
                                // console.log(params2);
                                layer.close(index);
                                layer.msg('修改成功')
                                table.reload('userTable');
                            });
                        }

                        // yes : function () {
                        //
                        // }

                    });
                });
            }else if(obj.event == 'del'){//delete
                layer.open({
                    time:0,
                    title:'友情提示',
                    content:"确定要删除该行数据吗？",
                    shade: [0.3, '#000'],
                    btn:['确定','取消'],
                    yes : function () {
                        $.post("${path}/easUser/batchDelete",{"ids":data.id},function () {
                            layer.msg('删除成功');
                            table.reload('userTable');
                        });
                    }
                })
            }
        })
    });
</script>
<script type="text/html" id="lockedTem">
<%--    laytpl模块化 没有依赖也可以单独使用--%>
    <input type="checkbox" readonly  title="锁定" {{d.locked == 1 ? 'checked' : ''}}>
</script>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="user:update">
        <a href="javascript:void(0)" lay-event="edit"><i class="layui-icon layui-icon-edit"></i></a>
    </shiro:hasPermission>
<%--    <c:forEach items="${sessionScope.user_pers}" var="per">--%>
<%--        <c:if test="${per.type eq 'function' and per.percode eq 'user:delete'}">--%>
<%--            <a href="javascript:void(0)" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>
    <shiro:hasPermission name="user:delete">
        <a href="javascript:void(0)" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
    </shiro:hasPermission>


</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <shiro:hasPermission name="user:add">
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i> 添加</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:delete">
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete"><i class="layui-icon layui-icon-delete"></i> 批量删除</button>
        </shiro:hasPermission>

<%--        <button class="layui-btn layui-btn-sm" lay-event="export"><i class="layui-icon layui-icon-export"></i> 导出数据</button>--%>
    </div>
</script>
</body>
</html>
