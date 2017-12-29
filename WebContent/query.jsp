<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
 <h3 align="center" style="color:#98bf21 ">高级搜索</h3>
    <form action="<c:url value="/CustomerServlet"/>" method="get">
        <input type="hidden" name="method" value="query">
      <table  border="0"  align="center" style="margin: 0 auto;width: 350px;" >
              <tr>
            <td  width="100px">客户名称</td>
            <td width="40%">
                <input type="text" name="name"/>
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
            <tr>
                <td>客户性别</td>
                <td>
                    <select name="gender">
                        <option value="">==请选择性别==</option>
                        <option value="male">male</option>
                        <option value="female">female</option>
                    </select>
                </td>
            </tr>
            <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone"/>
            </td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td>
                    <input type="text" name="email"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>

                    <input type="submit" class="button-primary" value="搜索"/>
                    <input type="reset" class="button-primary" value="重置"/>
                </td>
            </tr>

        </table>
    </form>
    <div style="text-align: center;margin-top: 20px"><p style="font-size: 15px">&copy; Huanwei Qi</p></div>
    </div>
</body>
</html>