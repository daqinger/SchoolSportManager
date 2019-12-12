<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/4/16
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>运动会报名系统登陆界面</title>
    <style>

        body{
            height: 470px;
            background: url(images/backpic.jpg) no-repeat;
            background-size: cover;
        }

        .biaoti{
            margin-top: 150px;
        }

        h1{

            text-align: center;
            font-size: 40px;
            font-family: "宋体";
            color: rgb(54, 53, 53);
        }

        .biaoti .goodwords{
            text-align: center;
            font-size: 18px;
            font-family: "宋体";
            color: rgb(54, 53, 53);
        }

        .content{
            background-color: rgba(63, 62, 59, 0.295);
            width: 350px;
            height: 200px;
            margin: 0 auto;
            border-radius: 20px;
            margin-top: 30px;
            padding-top: 30px;
        }

        .content .line{
            margin: 0 auto;
            width: 230px;
            height: 35px;
            margin-top: 10px;
        }

        .content .line span{
            float: left;
            color: rgba(255, 255, 255, 0.877);
            height: 25px;
        }

        .content .line .wenben{
            float: right;
            background-color: rgba(255, 255, 255, 0.534);
            border-radius: 10px;
            height: 25px;
        }
        .submit{
            margin-top: 20px;
            margin-left: 140px;

        }
        .submit input{
            width: 70px;
            height: 35px;
            border-radius: 5px;
        }

        .content .youxia{
            margin-left:150px;
        }

    </style>
</head>
<body>
<div class="biaoti">
    <h1>运 动 会 报 名 系 统</h1>
    <p class="goodwords">生命在于运动。—————— 伏尔泰</p>
</div>

<div class="content">
    <form action="login" method="POST">
        <div class="line">
            <span>用户名:</span>
            <input type="text" name="name" class="wenben">
        </div>
        <div class="line">
            <span>密码:</span>
            <input type="password" name="password" class="wenben">
        </div>
        <div class="line" style="margin-left: 150px">
            <input type="radio" name="control" value="teacher" checked>老师
            <input type="radio" name="control" value="student">学生
        </div>
        <div class="submit"style="margin-top: -10px">
            <input type="submit" value="登陆">
        </div>
    </form>

    <div class="youxia">
        <p>还没有学生账户？<a href="register.jsp">注册</a></p>
    </div>
</div>
</body>
</html>
