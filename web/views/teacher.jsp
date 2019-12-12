<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/7/6
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>运动会报名系统--老师界面</title>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <link id="themeLink" rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../easyui/demo/demo.css">
    <style type="text/css">
        ul li{
            list-style: none;
            line-height: 40px;
            margin-right: 40px;
            text-align: center;
            border-radius: 10px;
            margin-top: 10px;
            border: 1px solid black;
        }
        ul li a{
            text-decoration:none;
            color:#333;
            font-size: 16px;
        }
        ul li:hover{
            background-color: #e0ecff;
            border: 0px solid #333333;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:80px;">
    <%--logo图片--%>
    <div id="logo">
        <img src="../images/suke.jpg" height="75px" style="position: absolute;left: 40px;">
    </div>
    <%--登陆用户信息--%>
    <div id="login" style="position: absolute;right: 20px;top: 50px;color: #3f3f3f">
        欢迎老师：${sessionScope.teacher.teacherName}
    </div>
    <div id="themes" style="position: absolute; right: 210px;top: 45px">
        <a href="javascript:void(0)" id="mb" class="easyui-menubutton"
           data-options="menu:'#Themesmenu',iconCls:'icon-lock'">切换页面风格</a>
        <div id="Themesmenu" style="width:150px;">
            <div>default</div>
            <div>gray</div>
            <div>black</div>
            <div>bootstrap</div>
            <div>material</div>
            <div>metro</div>
        </div>
    </div>
    <div id="text" style="position: absolute; right: 470px;top: 20px;font-size: 30px">
        苏州科技大学运动会报名系统
    </div>
</div>
<div data-options="region:'south'" style="height:30px">
    <div id="copyRight" style="text-align: center;color: #5f5f5f";>
        苏州科技大学&copy运动会报名系统 作者：刘彭青
    </div>
</div>
<div data-options="region:'west',title:'系统菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" style="width:100%;height:100%">
        <div title="日志管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" pageUrl = "findLogs.jsp">查看日志</a> </li>
                <li><a href="#" pageUrl = "deleteLogs.jsp">删除日志</a> </li>
            </ul>
        </div>

        <div title="学生管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" pageUrl = "studentCRUD.jsp">学生信息</a> </li>
                <li><a href="#" pageUrl = "findStudents.jsp">学生信息<br>精确查询</a> </li>
            </ul>
        </div>

        <div title="运动项目管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" pageUrl = "sportCRUD.jsp">运动项目CRUD</a> </li>
                <li><a href="#" pageUrl = "findSports.jsp">运动信息<br>精确查询</a> </li>
            </ul>
        </div>

        <div title="报名管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" pageUrl = "findAllStudentSport.jsp">报名信息</a> </li>
                <li><a href="#" pageUrl = "findStudentSport.jsp">报名信息<br>精确查询</a> </li>
            </ul>
        </div>

        <div title="文件导出" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="${pageContext.request.contextPath}/getStudentExcel">学生信息<br>导出</a> </li>
                <li><a href="${pageContext.request.contextPath}/getSportExcel">运动信息<br>导出</a> </li>
                <li><a href="${pageContext.request.contextPath}/getApplicationExcel">参赛表信息<br>导出</a> </li>
            </ul>
        </div>
        <div title="老师信息管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" pageUrl = "updateTeacher.jsp">修改信息</a> </li>
            </ul>
        </div>

        <div title="退出登陆" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="${pageContext.request.contextPath}/teacherUnload">退出登录</a> </li>
            </ul>
        </div>

    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="tt" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">
        <div title="起始页" style="padding:20px;display:none;">
            老师：${sessionScope.teacher.teacherName}<br>
            您好，欢迎登陆苏州科技大学运动会报名系统
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        //给a标签绑定事件
        $("a[pageUrl]").click(function () {
            //1.获取pageUrl的属性值
            var pageUrl = $(this).attr("pageUrl");
            //获取a标签内容
            var title = $(this).text();
            //2.新建选项卡（先判断）
            if ($("#tt").tabs("exists",title)) {
                $("#tt").tabs("select",title);
            } else {
                $("#tt").tabs("add",{
                    title:title,
                    content:"<iframe src='"+pageUrl+"' width='100%' height='100%' frameborder='0'></iframe>",
                    closable:true,//是否显示关闭按钮
                    iconCls:"icon-tip"
                });

            }
        });

        //点击切换模板
        $("#Themesmenu").menu({
            onClick:function (item) {
                // 获取改变模板的名称
                var themeName = item.text;

                //获取link标签
                var href = $("#themeLink").attr("href");

                //更改属性值
                // easyui/themes/default/easyui.css
                href = href.substring(0,href.indexOf("themes"))+"themes/"+themeName+"/easyui.css";

                //更新link
                $("#themeLink").attr("href",href);
            }
        });

        $.messager.show({
            title:'登陆提示',
            msg:'登陆成功',
            showType:'show',
            showType:'slide'
        });
    })
</script>
</body>
</html>
