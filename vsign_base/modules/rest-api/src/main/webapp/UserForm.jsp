<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table border="0" width="90%">
		<form:form action="login" commandName="userForm">
			<tr>
				<td align="left" width="20%">Email:</td>
				<td align="left" width="40%"><form:input path="email" size="30" /></td>
				<td align="left"><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><form:password path="con_password" size="30" /></td>
				<td><form:errors path="con_password" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="center"><input type="submit" value="Sign Up" /></td>
				<td></td>
			</tr>
		</form:form>
	</table>

</body>
</html>