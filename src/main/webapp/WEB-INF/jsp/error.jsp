<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--开启el表达式--%>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>错误提示</title>
</head>
<body>
<h1>${message}</h1>
<a href="javascript:history.back(-1)">返回上一页</a>
</body>
</html>
