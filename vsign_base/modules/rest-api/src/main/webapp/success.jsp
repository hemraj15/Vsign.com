<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Confirmation Page</title>
</head>
<body>
	<h1>Registration has been completed with following details :</h1> <br>
	Name : ${Registration.name}.<br>
	Username : ${Registration.username}. <br>
	Email : ${Registration.email}.<br>
	Phone : ${Registration.phone}.<br>
	Password : ${Registration.password}.
</body>
</html>