<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/7/6
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <style type="text/css">
        body{
            margin: 0 auto;
            height: 400px;
            background: url(images/ms.jpg) no-repeat;
            background-size: cover;
        }
        .content{
            background-color: rgba(63, 62, 59, 0.295);
            width: 350px;
            height: 360px;

            border-radius: 20px;
            margin-top: 200px;
            margin-left: 40px;
            padding-top: 30px;
        }

        .content .line{
            margin: 0 auto;
            width: 300px;
            height: 35px;
            /*margin-top: 10px;*/
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
    <div class="content">
        <form action="register" method="POST">
            <div class="line">
                <span>学生学号：</span>
                <input type="text" name="studentNumber" class="wenben">
            </div>
            <div class="line">
                <span>学生姓名：</span>
                <input type="text" name="studentName" class="wenben">
            </div>
            <div class="line">
                <span>学生年龄：</span>
                <input type="text" name="studentAge" class="wenben">
            </div>
            <div class="line">
                <span>学生密码：</span>
                <input type="password" name="studentPassword" class="wenben">
            </div>

            <div class="line">
                <span>学生班级：</span>
                <input type="text" name="studentClassNumber" class="wenben">
            </div>
            <div class="submit"style="margin-top: 30px;margin-bottom: 40px">
                <input type="submit" value="注册">
            </div>
        </form>

        <div class="youxia">
            <p>已有学生账户？<a href="login.jsp">登陆</a></p>
        </div>
    </div>
</body>
</html>
