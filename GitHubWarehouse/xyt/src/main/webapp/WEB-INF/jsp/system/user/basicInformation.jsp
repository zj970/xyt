<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>基本资料</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
</head>
<body>
<form class="layui-form" action="${path}/easUser/basicInformationIndex"  style="padding-left: 100px;padding-top: 50px;">
    <c:if test="${code != 2 && code != 3 && code != 4}">
        <button type="submit"  class="layui-btn layui-btn layui-btn-normal" style="margin: 200px 0 0 200px;" onclick="noRoleMsg();return false;">点我查看提示</button>
    </c:if>
    <c:if test="${code == 4}">
        <button type="submit"  class="layui-btn layui-btn layui-btn-normal" style="margin: 200px 0 0 200px;" onclick="errorMsg();return false;">点我查看提示</button>
    </c:if>

    <c:forEach items="${data}" var="data">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-inline">
               <input type="text" name="username" readonly="readonly"  value="${data.username}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名:</label>
        <div class="layui-input-inline">
            <input type="text" name="name" readonly="readonly"  value="${data.name}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别:</label>
        <div class="layui-input-inline">
            <input type="text" name="sex" readonly="readonly"  value="${data.sex}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">出生日期:</label>
        <div class="layui-input-inline">
            <input type="text" name="birthday" readonly="readonly"  value="${data.birthday}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话号码:</label>
        <div class="layui-input-inline">
            <input type="text" name="phone" readonly="readonly"  value="${data.phone}" class="layui-input">
        </div>
    </div>
<%--    <shiro:hasPermission name="student:update">--%>
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">班级:</label>--%>
<%--        <div class="layui-input-inline">--%>
<%--            <input type="text" name="classes" readonly="readonly"  value="${data.easClass.classes}" class="layui-input">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    </shiro:hasPermission>--%>
<%--    <shiro:hasPermission name="teacher:update">--%>
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">学历:</label>--%>
<%--        <div class="layui-input-inline">--%>
<%--            <input type="text" name="education" readonly="readonly"  value="${data.education}" class="layui-input">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    </shiro:hasPermission>--%>
    <c:choose>
<%--        <c:when test="${code == 1}">--%>
<%--            <div class="layui-input-inline">--%>
<%--                <button type="submit"  class="layui-btn layui-btn layui-btn-normal"  onclick="noRoleMsg();return false;">点我查看提示</button>--%>
<%--            </div>--%>
<%--        </c:when>--%>
        <c:when test="${code == 2}">
            <div class="layui-form-item">
                <label class="layui-form-label">班级:</label>
                <div class="layui-input-inline">
                    <input type="text" name="classes" readonly="readonly" value="${data.easClass.classes}" class="layui-input">
                </div>
            </div>
        </c:when>
        <c:when test="${code == 3}">
            <div class="layui-form-item">
                <label class="layui-form-label">学历:</label>
                <div class="layui-input-inline">
                    <input type="text" name="education" readonly="readonly"  value="${data.education}" class="layui-input">
                </div>
            </div>
        </c:when>
    </c:choose>
    
    <div class="layui-form-item">
        <label class="layui-form-label">座右铭:</label>
        <div class="layui-input-inline">
            <input type="text" name="motto" readonly="readonly"  value="${data.motto}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button  class="layui-btn layui-btn layui-btn-normal" onclick="editForm();return false;">修 改</button>
        </div>
    </div>
</c:forEach>
</form>

<script>

    layui.use(["table","form","layer"]);

    function editForm() {
        $.get("${path}/easUser/basicForm",function (str) {
            layer.open({
                type:1,
                title :'修改基本资料',
                area : '700px',
                content : str,
                skin:'layui-layer-molv',
                btn : ['修改','取消'],
                success : function () {
                    $.get('${path}/easUser/getBasicInformation',function (data) {
                        //显示数据
                        //修改基本资料时 班级无法获取
                        layui.form.val('basicForm', data);
                    });
                },
                yes:function (index){
                    var params2 = $("#edit_basic_form").serialize();
                    $.post('${path}/easUser/modifyInformation',params2,function (data) {
                        // console.log(params2);
                        if(data.result === true){
                            layer.close(index);
                            layer.msg('修改成功',{icon:1,time:1000})
                            // layui.form.render(null,'basicForm2');
                            location.reload();//重载页面
                        }else{
                            layui.layer.msg('修改失败',{icon:5});
                        }
                    });
                }
            });
        });
    }

    function noRoleMsg(){
        //示范一个公告层
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,shade: [0.8,'#534e4e']
            ,id: 'noRoleMsg' //设定一个id，防止重复弹出
            ,resize: false
            ,btn: ['返回主页', '关闭弹窗']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content:  '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">亲！<br>我想告诉你<br>${msg}<br><br><br>我们此后的征途是星辰大海 ^_^</div>'
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    ,target: '_blank'
                });
            }
        });
    }

    function errorMsg(){
        //示范一个公告层
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,shade: [0.8,'#534e4e']
            ,id: 'noRoleMsg' //设定一个id，防止重复弹出
            ,resize: false
            ,btn: ['返回主页', '关闭弹窗']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">亲！<br>我想告诉你<br>${msg}<br><br><br>我们此后的征途是星辰大海 ^_^</div>'
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    ,target: '_blank'
                });
            }
        });
    }

</script>

</body>
</html>
