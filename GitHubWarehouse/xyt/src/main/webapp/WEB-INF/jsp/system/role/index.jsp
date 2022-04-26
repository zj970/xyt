<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
    <link rel="stylesheet" href="${path}/css/metroStyle/metroStyle.css">
    <script src="${path}/js/jquery.ztree.all.min.js"></script>
    <style>
        .layui-fluid{
            padding:0px;
        }
        .ztree *{
            font-size: inherit;
        }

    </style>

</head>
<body>
<%--<h2>角色列表</h2>--%>
<%--<hr>--%>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md8">
            <table id="roleTable" lay-filter="roleTable"></table>
        </div>
        <div class="layui-col-md4" >
            <h3>角色权限</h3>
            <hr>
            <ul id="treeDemo" class="ztree"></ul>
            <hr>
            <button onclick="assignPers();" style="width:100%;" class="layui-btn layui-btn-success">授权</button>

        </div>
    </div>
</div>

<script>
    var roleId;
    function assignPers(){
        //先获取所有选中的节点，其实就是要一个编号
        var nodes = zTreeObj.getCheckedNodes(true);
        var str = "";
        $.each(nodes,function () {
            str += this.id + ",";
        });
        str = str.substring(0,str.length - 1);
        //发送异步请求，参数：角色编号、一组权限编号
        $.post("${path}/easRole/assignPers",{
            roleId : roleId,
            persIds : str
        },function () {
            layer.msg('授权成功！',{icon:1});
        });
    }

    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        check : {
            enable: true,
            chkStyle: "checkbox"
        },
        data: {
            key: {
                name: "text"
            }
        }
    };

    $.get('${path}/easPermission/parentList',function (zNodes) {

        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    })


    layui.use('table',function () {
        var table = layui.table;

        //渲染表格
        table.render({
            elem : '#roleTable',
            url : '${path}/easRole/list',
            page : true,
            height : "full-125",//高度将始终铺满
            toolbar:'#toolbarDemo',
            cols : [[
                {type:'radio'},
                {field:'id', title:'编号',width:80},
                {field:'name', title:'角色名称'},
                {field:'available', title:'是否启用',templet:function (d) {
                    var str = d.available == 1 ? "checked" : "";
                    let a = '<input name="available" type="checkbox" title="启用" '+str+'/>';
                    return a;
                }},
                {title:'操作',toolbar:'#barDemo',align:'center'}
            ]]
        });

        //监听单选框事件
        table.on("radio(roleTable)",function (obj) {
            roleId = obj.data.id;
            //发送一个异步请求，查询选中的角色对应的权限，只需要编号
            $.post('${path}/easRole/rolePers',{id:obj.data.id},function (data) {
                //先获取所有的根节点
                var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
                $.each(nodes,function () {
                    //判断当前节点编号是否在  角色对应的权限编号数组中，说明该选中，否则不选中
                    if($.inArray(this.id,data) != -1){
                        //第二个 checked = true 表示勾选节点
                        //第三个 checkTypeFlag = true 表示按照 setting.check.chkboxType 属性进行父子节点的勾选联动操作
                        zTreeObj.checkNode(this,true,true);
                    }else{
                        zTreeObj.checkNode(this,false,true);
                    }
                });
            });
        });

        //监听工具栏的事件
        table.on('toolbar(roleTable)',function (obj) {
            var e = obj.event;
            switch (e){
                case "add":
                    $.get('${path}/easRole/roleForm',function (str) {
                        layer.open({
                            type:1,
                            title :'添加角色',
                            area : '700px',
                            content : str,
                            skin:'layui-layer-molv',
                            btn : ['确定','取消'],
                            yes : function (index) {
                                var params = $("#roleForm").serialize();
                                $.post('${path}/easRole/addRole',params,function (data) {

                                    if(data.result == false){
                                        layui.layer.msg(data.msg,{icon:5});
                                    }else{
                                        layui.layer.msg("添加成功",{icon:1,time:1000},function () {
                                            layer.close(index);
                                            table.reload('roleTable');
                                        });

                                    }

                                });
                            }
                        });
                    });
                    break;
                case "batchDelete":
                    var rows = table.checkStatus('roleTable');
                    var data = rows.data;
                    if(data.length == 0){
                        layer.msg("请选择要删除的数据",function(){});
                        return;
                    }
                    layer.confirm('真的干掉这么多基本课程吗？',{icon:5,title:'友情提示'},function (index) {
                        var params = "";
                        for(let role of data){
                            params += "ids="+role.id+"&";
                        }
                        $.post('${path}/easRole/batchDeleteRole',params,function (data) {

                            layui.layer.msg(data.msg,{icon:5,time:1000},function () {
                                layer.close(index);
                                table.reload('roleTable');
                            });

                        });
                    });
                    break;

            }
        });

        //监听行工具时间
        table.on("tool(roleTable)",function (obj) {
            var data = obj.data;
            if (obj.event == 'edit'){ //edit
                $.get("${path}/easRole/roleForm",function (str) {
                    layer.open({
                        type:1,
                        title : '修改角色',
                        content : str,
                        area:'700px',
                        skin:'layui-layer-molv',
                        btn:['确定','取消'],
                        success:function(){
                            $.get('${path}/easRole/getRoleView',{id:data.id},function (data) {
                                //显示数据
                                layui.form.val('roleForm',data);
                            });
                        },
                        yes : function (index) {
                            var params2 = $("#roleForm").serialize();
                            $.post('${path}/easRole/editRole',params2,function () {
                                layer.close(index);
                                layer.msg('修改成功',{icon:5,time:1000})
                                table.reload('roleTable');
                            });
                        }


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
                        $.post("${path}/easRole/batchDeleteRole",{"ids":data.id},function () {
                            layer.msg('删除成功',{icon:1,time:1000});
                            table.reload('roleTable');
                        });
                    }
                })
            }
        })

    });
</script>

<script type="text/html" id="barDemo">
    <a href="javascript:void(0)" lay-event="edit"><i class="layui-icon layui-icon-edit"></i></a>
    <a href="javascript:void(0)" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <shiro:hasPermission name="role:add">
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i> 添加</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="role:delete">
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete"><i class="layui-icon layui-icon-delete"></i> 批量删除</button>
        </shiro:hasPermission>
    </div>
</script>
</body>
</html>
