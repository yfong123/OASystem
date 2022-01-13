<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试类型转换</title>
</head>
<body>
	<h1>获取用户</h1>
	<form action="${ctxPath}/Converter/getUser" method="post">
		(用户名，密码，昵称，性别):
		<input type="text" name="user" /><br/>
		<input type="submit" name="submit" value="提交"/>
	</form>
</body>
</html>