<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Sign Up!</title>
</head>
<center>
<form action="/signup" method="POST">
<body>
	<table>
		<tr><th colspan="2">Sign Up Page</th></tr>
		<tr><td>Email *</td><td><input type="text" id="email" name="email" /></td></tr>
		<tr><td>Password *</td><td><input type="password" id="password" name="password" /></td></tr>
		<tr><td>Confirm Password *</td><td><input type="password" id="cpassword" name="cpassword" /></td></tr>
		<tr><td colspan="2"><input type="submit" value="Submit"></input></td></tr>
	</table>
</body>
</form>
</center>
</html>