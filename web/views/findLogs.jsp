<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019/7/6
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
本界面打印日志信息:${logMessage}<br>
<a href="${pageContext.request.contextPath}/findLogs">获取日志</a><br>

<c:forEach items="${requestScope.logs}" var="log">
    ${log.logDate}:${log.logContent}<br>
</c:forEach>
</body>
</html>
