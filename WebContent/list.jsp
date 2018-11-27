<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>list page!!!</h4>
	
	Welcome:<shiro:principal></shiro:principal>

	<shiro:hasRole name="admin">
		<br><br>
		<a href="admin.jsp">Admin Page</a>
	</shiro:hasRole>
	
	<shiro:hasRole name="user">
		<br><br>
		<a href="user.jsp">User Page</a>
	</shiro:hasRole>
	
	<br><br>
	<a href="shiro/testShiroAnnotation">Test ShiroAnnontation</a>
	
	<br><br>
	<a href="shiro/logout">Logout</a>
</body>
</html>