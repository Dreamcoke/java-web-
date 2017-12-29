<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 他的作用是为本页面所有的表单和超链接指定显示内容的框架-->
    <base target="main">
    <title>My JSP 'top.jsp' starting page</title>
    <style>
body
{
	background-color:#FAEBD7;
}
h1
{
	text-shadow: 5px 5px 5px #98bf21;
}
</style>
</head>
<body style="text-align: center;">
    <h1>客户关系管理系统</h1>
    <a href="<c:url value='/add.jsp'/>">添加客户</a>
    <a href="<c:url value='/CustomerServlet?method=findAll'/>">查询客户</a>
    <a href="<c:url value='/query.jsp'/>">高级搜索</a>

</body>
</html>