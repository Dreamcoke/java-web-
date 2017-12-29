<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

body {
      background-color: #FFF5EE
}
input {
	color: #98bf21;
}
.button-primary {
	border: 1px solid;
	border-radius: 11px 11px 11px 11px;
	cursor: pointer;
	font-family: "Lucida Grande", Verdana, Arial, "Bitstream Vera Sans",
		sans-serif;
	font-size: 16px;
	margin-top: -3px;
	padding: 3px 10px;
	text-decoration: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center" style="color:#98bf21 ">添加客户</h3>
<form action="<c:url value='/CustomerServlet'/>" method="post">
    <input type="hidden" name="method" value="add">
    <table  border="0"  align="center" style="margin: 0 auto;width: 350px;" >
        <tr>
            <td  width="100px">客户名称</td>
            <td width="40%">
                <input type="text" name="name" title="不能为空。"/>
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <input type="radio" name="gender" value="male" id="male"/>
                <label for="male">男</label>
                <input type="radio" name="gender" value="female" id="female"/>
                <label for="female">女</label>
            </td>
            <td>
                <label id="genderError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone" id="phone" title="不能为空。"/>
            </td>
            <td>
                <label id="phoneError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" id="email"/>
            </td>
            <td>
                <label id="emailError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <textarea rows="5" cols="30" name="description"></textarea>
            </td>
            <td>
                <label id="descriptionError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" class="button-primary" name="submit"/>
                <input type="reset" class="button-primary"  name="reset"/>
            </td>
        </tr>
    </table>
</form>
<div style="text-align: center;margin-top: 20px"><p style="font-size: 8px">&copy; Huanwei Qi</p></div>
</div>
</body>
</html>