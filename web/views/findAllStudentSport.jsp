<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/7/6
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>找到所有运动信息</title>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css"/>
    <style type="text/css">
        body{
            font-family: "Microsoft Himalaya";
        }

        p{
            color: white;
            /*background-color: #0092E7;*/
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#dg').datagrid({
                //需要抓取数据的地址，返回一个json的格式（俩对象，total，rows）
                url:'${pageContext.request.contextPath}/findAllStudentSport',
                columns:[[
                    {field:'studentNumber',title:'学生学号',width:170,align:"center"},
                    {field:'studentName',title:'学生姓名',width:170,align:'center'},
                    {field:'studentAge',title:'学生年龄',width:170,align:'center'},
                    {field:'studentClassNumber',title:'学生班级',width:170,align:'center'},
                    {field:'sportId',title:'运动编号',width:170,align:'center'},
                    {field:'sportName',title:'运动名字',width:170,align:'center'}
                ]],
                pagination:true,//显示分页，total
                singleSelect:true,
                pageSize: 10,				//每页显示的记录条数，默认为10
                pageList: [5, 10, 15],	//可以设置每页记录条数的列表
                beforePageText: '第',		//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            });
        });
    </script>
</head>
<body>
<table id="dg"></table>
</body>
</html>
