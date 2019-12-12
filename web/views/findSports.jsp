<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>精确查询运动</title>
    <style type="text/css">
        body{
            background-color: #ffffff;
        }

        h1{
            font-size: 18px;
            color: #333;
        }
        a{
            text-decoration:none;
            color:#333;
            font-size:14px;
        }
        p{
            color:#333;
            font-size:14px;
        }
        span{
            color:#333;
            font-size:14px;
        }
        .top{
            width: 1000px;
            height: 100px;
            margin: 0 auto;
            background-color: rgb(224, 236, 255);
            position: relative;
            border-radius: 10px;
        }
        .top .value{
            top: 30px;
            left: 20px;
            width: 160px;
            height: 40px;
            border-radius: 10px;
            position: absolute;


        }
        .top .submit{
            width: 80px;
            height: 80px;
            border-radius: 10px;
            top: 10px;
            right: 20px;
            position: absolute;
            font-size: 22px;
            background-color: #ffffff;
        }
        .top .id{
            top: 30px;
            left: 200px;
            width: 160px;
            height: 40px;
            border-radius: 10px;
            position: absolute;
            background-color: #ffffff;
        }
        .top .writer{
            top: 30px;
            left: 380px;
            width: 160px;
            height: 40px;
            border-radius: 10px;
            position: absolute;
            background-color: #ffffff;
        }
        .top .name{
            top: 30px;
            left: 560px;
            width: 160px;
            height: 40px;
            border-radius: 10px;
            position: absolute;
            background-color: #ffffff;
        }
        div input{
            line-height: 40px;
        }

        .content ul{
            margin-left: 20px;
            width: 1000px;
            height: 40px;
        }
        .content ul li{
            width: 248px;
            height: 40px;
            list-style: none;
            text-align: center;
            float: left;
            border: 1px solid rgba(0, 0, 0, 0.49);
        }
        .content ul li a{
            line-height: 40px;
        }
        .content ul li:hover{
            background-color: #e0ecff;
        }
    </style>
</head>
<body>

<div class="top">
    <form action="${pageContext.request.contextPath}/findSports">
        <input class="value" type="text" value="输入要查询的字样" name="value"><br>
        <div class="id" ><input type="radio" name="control" value="sportId" checked = "checked"><span>运动编号:</span> </div>
        <div class="writer"><input  type="radio" name="control" value="sportName"><span>运动名字:</span></div>
        <div class="name"> <input  type="radio" name="control" value="studentNumber" ><span>学生学号：</span></div>
        <input class="submit" type="submit" value="查询">
    </form>
</div>
<div class="content">
    <ul>
        <li><a href="#">运动编号</a></li>
        <li><a href="#">运动名称</a></li>
        <li><a href="#">需要人数</a></li>
        <li><a href="#">报名人数</a></li>
    </ul>
    <c:forEach items="${requestScope.sportList}" var="sport">
        <ul>
            <li><a href="#">${sport.sportId}</a></li>
            <li><a href="#">${sport.sportName}</a></li>
            <li><a href="#">${sport.sportNumberNeeded}</a></li>
            <li><a href="#">${sport.sportNumberNow}</a></li>
        </ul>
    </c:forEach>
</div>

</body>
</html>