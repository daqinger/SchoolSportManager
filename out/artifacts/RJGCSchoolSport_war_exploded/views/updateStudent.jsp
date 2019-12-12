<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/6/8
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <link id="themeLink" rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css"/>
</head>
<body>

本界面更新学生信息
<div class="easyui-window" data-options="title:'您的名片',width:300,height:300,closed:false">
    <form  method="post">
        <table>
            <tr>
                <td>学生学号</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" value="${sessionScope.student.studentNumber}"></td>
            </tr>
            <tr>
                <td>您的名字：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" value="${sessionScope.student.studentName}"></td>
            </tr>
            <tr>
                <td>您的年龄：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" value="${sessionScope.student.studentAge}"></td>
            </tr>
            <tr>
                <td>您的密码：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" value="${sessionScope.student.studentPassword}"></td>
            </tr>
            <tr>
                <td>您的班级：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" value="${sessionScope.student.studentClassNumber}"></td>
            </tr>
            <td><a id="open" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> </td>
        </table>
    </form>
</div>

<div id="win" class="easyui-window" data-options="title:'您的名片',width:300,height:300,closed:true">
    <form id="editFrom" method="post">
        <table>

            <tr>
                <td>学生学号</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" name="studentNumber" value="${sessionScope.student.studentNumber}"></td>
            </tr>
            <tr>
                <td>更新名字：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" name="studentName"></td>
            </tr>
            <tr>
                <td>更新年龄：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" name="studentAge" ></td>
            </tr>
            <tr>
                <td>更新密码：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" name="studentPassword"></td>
            </tr>
            <tr>
                <td>更新班级：</td><td> <input class="easyui-validatebox" data-options="required:true" type="text" name="studentClassNumber"></td>
            </tr>

            <tr>
                <td><a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改</a> </td>
                <td><a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">重置</a></td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //清空表单
        $("#reset").click(function () {
            $("#editFrom").form("clear");
        });

        $("#open").click(function () {
            $("#win").window("open");
        });

        //编辑逻辑
        $("#edit").click(function () {
            $.messager.confirm('提示', '确定修改？', function(r){
                if (r){//点了确定
                    // exit action;
                    $("#editFrom").form("submit",{
                        url:"${pageContext.request.contextPath}/updateStudent"
                    });
                    $.messager.show({
                        title:"提示",
                        msg:"修改成功，下次登陆即可生效"
                    });

                    $("#win").window({
                        closed:true
                    });
                    $("#win").window({
                        closed:false
                    });
                }
            });
        });
    });
</script>

</body>
</html>
