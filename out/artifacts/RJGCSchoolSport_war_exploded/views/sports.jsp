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
    <title>学生查找运动 报名运动</title>
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
                url:'${pageContext.request.contextPath}/studentGetSports?studentNumber=0',
                columns:[[
                    {field:'sportId',title:'运动编号',width:200,align:"center"},
                    {field:'sportName',title:'运动名称',width:200,align:'center'},
                    {field:'sportNumberNeeded',title:'运动需要人数',width:200,align:'center'},
                    {field:'sportNumberNow',title:'已经报名人数',width:200,align:'center'}
                ]],
                pagination:true,//显示分页，total
                singleSelect:true,
                pageSize: 10,				//每页显示的记录条数，默认为10
                pageList: [5, 10, 15],	//可以设置每页记录条数的列表
                beforePageText: '第',		//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                toolbar:"#toolbar"
            });
        });
    </script>
</head>
<body>
<table id="dg"></table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="signSport()">报名</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">运动管理</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <input name="sportId" type="hidden">
        </div>
        <div class="fitem">
            <input name="studentNumber" type="hidden" value="${sessionScope.student.studentNumber}">
        </div>
        <div class="fitem">
            <label>运动名称: &nbsp</label>
            <input name="sportName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>需要人数: &nbsp</label>
            <input name="sportNumberNeeded" class="easyui-textbox">
        </div>
        <div class="fitem">
            <label>已有人数: &nbsp</label>
            <input name="sportNumberNow" class="easyui-textbox">
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSport()" style="width:90px">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>
<script type="text/javascript">
    var url;

    function signSport(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('setTitle','编辑运动信息');
            $('#fm').form('load',row);
            url = '${pageContext.request.contextPath}/signSport?sportId='+row.sportId;
        }
    }

    function saveSport(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result.errorMsg){
                    $.messager.show({
                        title: '错误',
                        msg: result.errorMsg
                    });
                } else {
                    $.messager.show({
                        title: '成功',
                        msg: "操作成功！"
                    });
                    $('#dlg').dialog('close');		// close the dialog
                    $('#dg').datagrid('reload');	// reload the user data
                }
            }
        });
    }
</script>
</body>
</html>
